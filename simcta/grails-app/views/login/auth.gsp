<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
	<title><g:message code='springSecurity.login.title'/></title>
  
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <asset:stylesheet src="application.css"/>
    <asset:stylesheet src="login.css"/>

    
	<nav class="navbar navbar-default">
    	<div class="container-fluid">
        	<div class="navbar-header">
	            <a class="navbar-brand" href="/#">
	                <i class="fa grails-icon">
	                <asset:image src="logo.png"/>
	                </i> SiMCTA
	            </a>
        	</div>
        </div>
    </nav>

</head>
<body>
<div id="login">
	<div class="inner">
		<div class="fheader"><g:message code='springSecurity.login.header'/></div>

		<g:if test='${flash.message}'>
			<div class="login_message">${flash.message}</div>
		</g:if>

		<form action="${postUrl ?: '/login/authenticate'}" method="POST" class="cssform" autocomplete="off">
			<p>
				<label for="username"><g:message code='springSecurity.login.username.label'/>:</label>
				<input type="text" class="text_" name="${usernameParameter ?: 'username'}" id="username"/>
			</p>
			<br>
			<p>
				<label for="password"><g:message code='springSecurity.login.password.label'/>:</label>
				<input type="password" class="text_" name="${passwordParameter ?: 'password'}" id="password"/>
			</p>
			<br>
			<p>
				<input type="submit" id="submit" value="${message(code: 'springSecurity.login.button')}" class="btn btn-primary"/>
			</p>
		</form>
		</div>
	</div>
</div>

<script>
(function() {
	document.forms['loginForm'].elements['${usernameParameter ?: 'username'}'].focus();
})();
</script>

</body>

</html>