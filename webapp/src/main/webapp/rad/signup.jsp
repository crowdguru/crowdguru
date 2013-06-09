<html>
	<head>
		<title>Authorization page</title>
		<script type="text/javascript" src="/crowdguru/static/lib/jquery/jquery-1.9.1.js"></script>
		<script type="text/javascript" src="/crowdguru/static/lib/crowdguru/authorization.js"></script>
		<link href="/crowdguru/static/css/crowdguru.css" rel="stylesheet" type="text/css">
		<style type="text/css">
			* {
				font-family: sans-serif;
			}
		</style>
<!-- 		<script>
  			$(document).ready(crowdguru.init);
  		</script>
 -->		
	</head>
	<body>
		<h1>Authorization page</h1>
		
		<div id="fetchinfo" style="display: none"></div>
		<div id="fetchedinfo" style="display: none"></div>
		<div id="userinfo" style="display: none"></div>
		<div id="crowdguruInfo" style="display: none"></div>
		
		<span id="signinButton">
		  <span
		    class="g-signin"
		    data-callback="signupCallback"
		    data-clientid="337126735638.apps.googleusercontent.com"
		    data-cookiepolicy="single_host_origin"
		    data-requestvisibleactions="http://schemas.google.com/AddActivity"
		    data-scope="https://www.googleapis.com/auth/plus.login https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile">
		  </span>
		</span>		

 		<script type="text/javascript">
		      (function() {
			       var po = document.createElement('script'); 
			       po.type = 'text/javascript'; 
			       po.async = true;
			       po.src = 'https://apis.google.com/js/client:plusone.js';
			       var s = document.getElementsByTagName('script')[0]; 
			       s.parentNode.insertBefore(po, s);
		     })();
    	</script>
	</body>
</html>
