<html>
	<head>
		<title>Authorization page</title>
		<script type="text/javascript" src="static/lib/jquery/jquery-1.9.1.js"></script>
		<script type="text/javascript" src="static/lib/crowdguru/authorization.js"></script>
		<link href="static/css/crowdguru.css" rel="stylesheet" type="text/css">
		
		<script>
  			$(document).ready(signinCallback);
  		</script>
	</head>
	<body>
		<h1>Authorization page</h1>
		
		<div id="fetchinfo" style="display: none">
		</div>
		
		<div id="fetchedinfo" style="display: none">
		</div>

		<div id="userinfo" style="display: none">
		</div>

		<a id="signin" href="https://accounts.google.com/o/oauth2/auth?response_type=token&client_id=337126735638.apps.googleusercontent.com&scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.profile&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Fcrowdguru%2Fsignin.jsp">
			Sign in with Google+
		</a>
		
<!-- 		<script type="text/javascript">
		      (function() {
		       var po = document.createElement('script'); po.type = 'text/javascript'; po.async = true;
		       po.src = 'https://apis.google.com/js/client:plusone.js';
		       var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(po, s);
		     })();
    	</script>
 -->	</body>
</html>
