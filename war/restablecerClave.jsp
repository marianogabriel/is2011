<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Recuperar Contrase&ntilde;a</title>
<meta name="GUI para aplicación is2011" content="">
<meta name="Grupo 4 - ¿nombre?" content="">
<!--[if lt IE 9]>
		<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js"></script>
<script src="assets/js/google-code-prettify/prettify.js"></script>
<script src="/js/bootstrap-modal.js"></script>
<script src="/js/menu.js" type="text/javascript"></script>

<link href="/css/bootstrap.css" rel="stylesheet">
<link href="/css/docs.css" rel="stylesheet">
<style type="text/css">

/* Override some defaults */
html,body {
	background-color: #eee;
}

body {
	padding-top: 40px;
	/* 40px to make the container go all the way to the bottom of the topbar */
}

.show-grid [class *="span"] {
	text-align: left;
}

label {
	width: 200px;
}

form .input {
	margin-left: 220px;
}

.container>footer p {
	text-align: center; /* center align it with the container */
}

.container {
	/* downsize our container to make the content feel a bit tighter and more cohesive.
				 * NOTE: this removes two full columns from the grid, meaning you only go to 14
				 * columns and not 16.
				 */
	width: 820px;
}
/* The white background content wrapper */
.content {
	background-color: #fff;
	padding: 20px;
	margin: 0 -20px;
	/* negative indent the amount of the padding to maintain the grid system */
	-webkit-border-radius: 0 0 6px 6px;
	-moz-border-radius: 0 0 6px 6px;
	border-radius: 0 0 6px 6px;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .15);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .15);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .15);
}
/* Page header tweaks */
.page-header {
	background-color: #f5f5f5;
	padding: 20px 20px 10px;
	margin: -20px -20px 20px;
}
/* Give a quick and non-cross-browser friendly divider */
.content .span10 {
	margin-left: 0;
	padding-left: 19px;
	border-right: 1px solid #eee;
}

.topbar .btn {
	border: 0;
}
</style>
</head>
<body>
	<div class="topbar">
		<div class="fill">
			<div class="container">
				<a class="brand" href="#">Recuperar Contrase&ntilde;a</a>
				<ul class="nav">
					<li class="active"><a href="/secure/main.jsp">Inicio</a></li>
				</ul>
				<!--
					<form method="post" action="login" class="pull-right">
						<input class="input-small" type="text" name="username"
							placeholder="Usuario"> <input class="input-small"
							type="password" name="password" placeholder="Contrase&ntilde;a">
						<button class="btn" type="submit">Entrar</button>
					</form>
					-->
			</div>
		</div>
	</div>

	<form name="form" action="" method="post">
		<div class="container">
			<div class="hero-unit">
				<div class="row">
					<div class="clearfix">
						<h3>Olvid&oacute; su contrase&ntilde;a, ingrese su email o nick-name:</h3>
					</div>
				</div>
				<div class="row">
					<div class="clearfix">
						<div class="span16">
							<label for="mediumSelect">Direcci&oacute;n de e-mail*:</label>
							<div class="input">
								<input type="text" name="email" size="42" maxlength="40"
									id="mail" value="${usuarioNoRegistrado.mail }" /> <span
									class="help-block">(Un correo le ser&aacute; enviado con
									su nueva contrase&ntilde;a)</span>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="clearfix">
						<div class="span16">
							<label for="mediumSelect">Nick-Name*:</label>
							<div class="input">
								<input type="text" name="nombreUsuario" size="42" maxlength="30"
									id="usr" value="${usuarioNoRegistrado.nombreUsr }" /> <span
									class="alert-message error">${errors}</span>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="actions">
						<input class="btn primary" type="button"
							value="Recuperar Contraseña" onclick="restablecerClave()">
					</div>
				</div>
			</div>
		</div>
	</form>
</body>


<script type="text/javascript">
	var form = document.getElementsByName("form")[0];
	var mail = document.getElementById("mail");
	var usr = document.getElementById("usr");

	function validarCampos() {
		if (mail.value == "" || usr.value == "") {
			return false;
		}
		return true;
	}

	function restablecerClave() {
		if (validarCampos()) {
			//form.submit();
		} else
			alert("El mail y el nick son obligatorios");
	}
</script>

</html>