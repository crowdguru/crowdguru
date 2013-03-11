var authorizationToken = null;

var signupCallback = function(authResult) {

	if (authResult['access_token']) {
		var html = "<h2>Authorization token</h2>";
			
		html += "<table><thead style='text-align: left'><tr><th>Key</th><th>Value</th></tr></thead><tbody>";
		for (var key in authResult) {
			html += "<tr><td>" + key + "</td><td>" + authResult[key] + "</td></tr>";
		}
		authorizationToken = authResult['access_token'];
		var validationUri = "https://www.googleapis.com/oauth2/v1/tokeninfo?access_token=" 
			+ encodeURIComponent(authorizationToken);
		html += "<tr><td>validation_uri</td><td>" + validationUri + "</td</tr>";
		$("#fetchinfo").html(html + "</tbody></table>");
		$("#fetchinfo").fadeIn();
		$.getJSON(validationUri, validateAccessToken);
	} 
	else if (authResult['error']) {
		console.log('There was an error: ' + authResult['error']);
	}
};

var validateAccessToken = function(data, status) {
	var html = "<h2>Validated authorization token: status: '" + status + "'</h2>";
	html += "<table><thead style='text-align: left'><tr><th>Key</th><th>Value</th></tr></thead><tbody>";
	if (status == 'success') {
		for (var key in data) {
			html += "<tr><td>" + key + "</td><td>" + data[key] + "</td></tr>";
		}
		
		userInfoUri = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=" + encodeURIComponent(authorizationToken);
		html += "<tr><td>user_info_uri</td><td>" + userInfoUri + "</td><tr></tbody></table>";
		$.getJSON(userInfoUri, userInfoCallback);
	}
	$("#fetchedinfo").html(html);
	$("#fetchedinfo").fadeIn();
};

var userInfoCallback = function(response, status) {
	if (status == 'success') {
		var html = "<h2>Got user info: status: '" + status + "'</h2><table><thead style='text-align: left'><tr><th>Key</th><th>Value</th></tr></thead><tbody>";
		
		for (var key in response) {
			html += "<tr><td>" + key + "</td><td>" + response[key] + "</td></tr>";
		}
		
		$("#userinfo").html(html + "</tbody>	</table>");
		$("#userinfo").fadeIn();
		$("#signinButton").fadeOut();
	}
};

var signinCallback = function() {
			
	var params = {};
	var queryString = location.hash.substring(1);
	var regex = /([^&=]+)=([^&]*)/g; 
	var m;
	while (m = regex.exec(queryString)) {
	  params[decodeURIComponent(m[1])] = decodeURIComponent(m[2]);
	}
	
	$("#fetchinfo").html("<h2>Got authorization token</h2>");
	
	for (var key in params) {
		$("#fetchinfo").html($("#fetchinfo").html() + "'" + key + "': '" + params[key] + "'" + "<br/>");
	}
	
	var validationUri = "https://www.googleapis.com/oauth2/v1/tokeninfo?access_token=" 
		+ encodeURIComponent(params['access_token']);
	$("#fetchinfo").html($("#fetchinfo").html() + "'validation_uri': '" + validationUri + "'" + "<br/>" );
	$("#fetchinfo").fadeIn();
	
	$.getJSON(validationUri, function(data, status) {
		
		$("#fetchedinfo").html("<h2>Validated authorization token: status: '" + status + "'</h2>");
		
		if (status == 'success') {
			for (var key in data) {
				$("#fetchedinfo").html($("#fetchedinfo").html() + "'" + key + "': '" + data[key] + "'" + "<br/>");
			}
			
			userInfoUri = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=" + encodeURIComponent(params['access_token']);
			$("#fetchedinfo").html($("#fetchedinfo").html() + "<br/>" + "'user_info_uri': '" + userInfoUri + "'");
			$("#fetchedinfo").fadeIn();
			
			$.getJSON(userInfoUri, function(response, status) {
				if (status == 'success') {
					$("#userinfo").html("<h2>Got user info: status: '" + status + "'</h2>");
					
					for (var key in response) {
						$("#userinfo").html($("#userinfo").html() + "'" + key + "': '" + response[key] + "'" + "<br/>");
					}
					
					$("#userinfo").fadeIn();
					$("#signin").fadeOut();
					}
				});
			}
		});
};


