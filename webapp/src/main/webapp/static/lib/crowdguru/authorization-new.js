var crowdguru = {
	
	init: function() {
		$("#crowdguruInfo").html("<h2>Crowd guru object information</h2>");
		for (var key in crowdguru) {
			$("#crowdguruInfo").html($("#crowdguruInfo").html() + "'" + key + "': '" + crowdguru[key] + "'" + "<br/>");
		}
		$("#crowdguruInfo").fadeIn();
	},
	
	authorizationToken: null,

	signupCallback: function(authResult) {

		if (authResult['access_token']) {
			$("#fetchinfo").html("<h2>Got authorization token</h2>");

			for (var key in authResult) {
				$("#fetchinfo").html($("#fetchinfo").html() + "'" + key + "': '" + authResult[key] + "'" + "<br/>");
			}
			
			$("#fetchinfo").fadeIn();
			$("#signinButton").fadeOut();
			
			authorizationToken = authResult['access_token'];
			var validationUri = "https://www.googleapis.com/oauth2/v1/tokeninfo?access_token=" 
				+ encodeURIComponent(authorizationToken);
			$("#fetchinfo").html($("#fetchinfo").html() + "'validation_uri': '" + validationUri + "'" + "<br/>" );
			$("#fetchinfo").fadeIn();
			
			$.getJSON(validationUri, validateAccessToken);
		} 
		else if (authResult['error']) {
			console.log('There was an error: ' + authResult['error']);
		}
	},

	validateAccessToken: function(data, status) {
				
		$("#fetchedinfo").html("<h2>Validated authorization token: status: '" + status + "'</h2>");
		
		if (status == 'success') {
			for (var key in data) {
				$("#fetchedinfo").html($("#fetchedinfo").html() + "'" + key + "': '" + data[key] + "'" + "<br/>");
			}
			
			userInfoUri = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=" + encodeURIComponent(authorizationToken);
			$("#fetchedinfo").html($("#fetchedinfo").html() + "<br/>" + "'user_info_uri': '" + userInfoUri + "'");
			$("#fetchedinfo").fadeIn();
					
			$.getJSON(userInfoUri, userInfoCallback);
		}
	},

	userInfoCallback: function(response, status) {
		if (status == 'success') {
			$("#userinfo").html("<h2>Got user info: status: '" + status + "'</h2>");
			
			for (var key in response) {
				$("#userinfo").html($("#userinfo").html() + "'" + key + "': '" + response[key] + "'" + "<br/>");
			}
			
			$("#userinfo").fadeIn();
			$("#signin").fadeOut();
		}
	},

	signinCallback: function() {
					
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
	}
};



