<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registrarse</title>
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

label {
	width: 270px;
}

form .input {
	margin-left: 290px;
}
</style>
</head>
<body>
	<div class="topbar">
		<div class="fill">
			<div class="container">
				<a class="brand" href="#">Registrarse</a>
				<ul class="nav">
					<li class="active"><a href="/index.jsp">Inicio</a></li>
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

	<form name="form" action="/Registracion" method="post">
		<div class="container">
			<div class="hero-unit">
				<div class="row">
					<div class="clearfix">
						<h3>Nuevo Usuario, por favor ingrese sus datos:</h3>
					</div>
				</div>
				<div class="row">
					<div class="clearfix">
						<div class="span16">
							<label for="mediumSelect">Direcci&oacute;n de e-mail*:</label>
							<div class="input">
								<input type="text" name="email" id="mail"
									value="${usuarioNoRegistrado.mail }">
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="clearfix">
						<div class="span16">
							<label for="mediumSelect">Nick-Name*:</label>
							<div class="input">
								<input type="text" name="nombreUsuario" id="usr"
									value="${usuarioNoRegistrado.nombreUsr }"> <span
									class="alert-message error">${errors}</span>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="clearfix">
						<div class="span16">
							<label for="mediumSelect">Contrase&ntilde;a*:</label>
							<div class="input">
								<input type="password" name="clave" id="pass">
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="clearfix">
						<div class="span16">
							<label for="mediumSelect">Repita contrase&ntilde;a*:</label>
							<div class="input">
								<input type="password" name="confirmarClave" id="pass2">
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="clearfix">
						<div class="span16">
							<label for="mediumSelect">Palabra clave para recuperar su
								contrase&ntilde;a*:</label>
							<div class="input">
								<select name="idPreguntaSecreta" id="psecr">
									<option value="">-Seleccione una pregunta-</option>
									<option value="1">Cu&aacute;l es el nombre de su
										mascota?</option>
									<option value="2">Nombre de tu abuela materna?</option>
									<option value="3">Apellido de soltera de su madre?</option>
									<option value="4">Cu&aacute;l es tu comida favorita?</option>
									<option value="5">Fecha de casamiento?</option>
									<option value="6">Nombre del primer colegio?</option>
								</select> <span class="help-block">(Nombre de mascota, película
									favorita, deporte favorito, etc)</span> <input type="text"
									name="respuestaSecreta" id="psec" value="${usuarioNoRegistrado.respuestaPregunta}">
							</div>
						</div>
					</div>
				</div>
				<span class="help-block">*Campos obligatorios.</span> <br /> <span
					class="label notice">Nota</span> Hacer clic en ENVIAR implica
				aceptar el Contrato de Servicios de Kennedy y la declaración de
				privacidad.
				<div class="actions">
					<input class="btn primary" type="button" name="registar"
						value="Enviar" onclick="altaUsuario()">
					<div class="row"></div>
				</div>
			</div>
		</div>
	</form>
</body>


<script type="text/javascript">
	var form = document.getElementsByName("form")[0];
	var usr = document.getElementById("usr");
	var mail = document.getElementById("mail");
	var pass = document.getElementById("pass");
	var pass2 = document.getElementById("pass2");
	var psec = document.getElementById("psec");

	msjError.style.display = 'none';

	//loginError();

	function altaUsuario() {
		if (validarCampos()) {
			if (validarClave())
				form.submit();
		}
	}

	function validarCampos() {
		if (usr.value == "" || mail.value == "" || pass.value == ""
				|| pass2.value == "" || psec.value == "") {
			alert("Todos los campos son obligatorios");
			return false;
		}
		return true;
	}

	function limpiarCampos() {
		usr.value = "";
		mail.value = "";
		pass.value = "";
		pass2.value = "";
		psec.value = "";
	}

	function validarClave() {
		if (pass.value == pass2.value) {
			return true;
		}
		alert("Las claves ingresadas no coinciden");
		return false;
	}
</script>

</html>