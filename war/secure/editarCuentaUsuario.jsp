<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="ar.kennedy.is2011.session.Session"%>
<%@page import="ar.kennedy.is2011.session.SessionManager"%>
<%@page import="ar.kennedy.is2011.utils.WebUtils"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="ar.kennedy.is2011.db.entities.Usuario"%>
<%@page import="ar.kennedy.is2011.models.SearchPicturesModel"%>
<%@page import="ar.kennedy.is2011.db.entities.AlbumEy"%>
<%@page import="java.util.Set"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="java.util.List"%>
<%@page import="ar.kennedy.is2011.db.entities.PictureEy"%>
<%@page import="java.util.Iterator"%>
<%
	Session userSession = SessionManager.get(request,
			WebUtils.getSessionIdentificator(request));
	Usuario user = (Usuario) userSession.getElement("user");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Editar Cuenta</title>
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
body {
	padding-top: 60px;
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
</style>
</head>

<body onLoad="menu1.escribeacordeon('menu',22,5);">
	<form name="form" action="/editarCuenta" method="post">
		<input type="hidden" name="misFotos" value="misFotos">

		<div class="topbar">
			<div class="topbar-inner">
				<div class="container">
					<a class="brand" href="#">Editar cuenta de Usuario</a>
					<ul class="nav">
						<li class="active"><a href="/secure/main.jsp">Inicio</a></li>
					</ul>
					<p class="pull-right">
						Logueado como <a href="/secure/editarCuentaUsuario.jsp"><%=user.getNombreUsr()%></a>
						<a href="/logout"> Cerrar sesion </a>
					</p>
				</div>
			</div>
		</div>

		<div class="container">
			<div class="content">
				<div class="row">
					<div class="clearfix">
						<div class="span16">
							<label for="mediumSelect">Direcci&oacute;n de mail:</label>
							<div class="input">
								<input id="mail" name="mailPrimario" type="text"
									value="${usuarioLogeado.mail}" />
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="clearfix">
						<div class="span5">
							<label>Direcci&oacute;n de mail alternativo:</label>
							<div class="input">
								<input id="mail2" name="mailSecundaro" type="text"
									value="${usuarioLogeado.mailSecundario}" />
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="clearfix">
						<div class="span16">
							<label for="mediumSelect">Nombre:</label>
							<div class="input">
								<input id="nomUsr" name="nombreUsr" type="text"
									value="${usuarioLogeado.nombre}" />
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="clearfix">
						<div class="span16">
							<label for="mediumSelect">Apellido:</label>
							<div class="input">
								<input id="apeUsr" name="apellidoUsr" type="text"
									value="${usuarioLogeado.apellido}" />
							</div>
						</div>
					</div>
				</div>

    <div class="alert-message warning" data-alert="alert">
	<p>Hello, world!</p>
    </div>
    <a href="#" onclick="$('.alert-message').alert('close')" style="float:right;">Close Alert</a>

				<table border="0" cellpadding="10%" align="center" rules="none">
					<tr>
						<td align="right">
							<table cellpadding="5" border="1" rules="none" align="center">
								<tr>
									<td align="right">Fecha de nacimiento:&nbsp;</td>
									<td><input type="text" name="fechaNacimiento"
										maxlength="12" size="30" id="fechaNac"
										value="${usuarioLogeado.fechaNacimiento}"></td>
								</tr>
								<tr>
									<td align="right">Sexo:&nbsp;</td>
									<td><input type="radio" name="tipoSexo" id="masculino"
										value="M">&nbsp;Hombre&nbsp; <input type="radio"
										name="tipoSexo" id="femenino" value="F">&nbsp;Mujer</td>
								</tr>
								<tr>
									<td align="right">País o región:&nbsp;</td>
									<td><select name="pais" id="idPais">
											<option value="">-- Seleccionar un pais --</option>
											<option value="1">Argentina</option>
											<option value="2">Brasil</option>
											<option value="3">Chile</option>
									</select></td>
								</tr>
								<tr>
									<td align="right">Provincia:&nbsp;</td>
									<td><select name="provincia" id="prov">
											<optionvalue"">-- Seleccionar una provincia --
											</option>
											<option value="1">Buenos Aires</option>
											<option value="2">Cordoba</option>
											<option value="3">Santa Fe</option>
											<option value="4">Salta</option>
									</select></td>
								</tr>
								<tr>
									<td align="right">Cambiar Contraseña:&nbsp;</td>
									<td><input type="button" name="botonCambiar"
										value="Cambiar"></td>
								</tr>

								<tr>
									<td align="right">Cambiar pregunta secreta:&nbsp;</td>
									<td><select name="preguntaSecreta" id="pSecreta">
											<option value="">-- Seleccionar una pregunta --</option>
											<option value="1">Cuál es el nombre de su mascota?</option>
											<option value="2">Nombre de tu abuela materna?</option>
											<option value="3">Apellido de soltera de su madre?</option>
											<option value="4">Cuál es tu comida favorita?</option>
											<option value="5">Fecha de casamiento?</option>
											<option value="6">Nombre del primer colegio?</option>
									</select></td>
								</tr>
								<tr>
									<td align="right">Ingresar respuesta:&nbsp;</td>
									<td><input type="text" name="respuestaUsr" size="40"
										maxlength="42" id="respuesta"
										value="${usuarioLogeado.respuestaPregunta}"></td>
								</tr>

								<tr>
									<td></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td colspan="2"></td>
					</tr>
					<tr>
						<td align="center"><input type="button" class="btn" name="limpiar"
							value="Limpiar" onclick="limpiarCampos();">&nbsp;&nbsp;&nbsp;&nbsp;
							<input class="btn primary" type="button" name="save" value="Guardar"
							onclick="guardar();"></td>
					</tr>
				</table>
			</div>
		</div>
	</form>
<script type="text/javascript">
	var form = document.getElementsByName("form")[0];
	var mail = document.getElementById("mail");
	var mail2 = document.getElementById("mail2");
	var nombre = document.getElementById("nomUsr");
	var apellido = document.getElementById("apeUsr");
	var fechaNac = document.getElementById("fechaNac");
	var masculino = document.getElementById("masculino");
	var femenino = document.getElementById("femenino");
	var pais = document.getElementById("idPais");
	var prov = document.getElementById("prov");
	var pregunta = document.getElementById("pSecreta");
	var resp = document.getElementById("respuesta");

	cargarCombos();

	function limpiarCampos() {
		mail.value = "";
		mail2.value = "";
		nombre.value = "";
		apellido.value = "";
		fechaNac.value = "";
		//maculino.checked = "false";
		//femenino.checked = "false";
		pais.value = "";
		prov.value = "";
		pregunta.value = "";
		resp.value = "";
	}

	function guardar() {
		if (validarCamposMails()) {
			form.submit();
		}
	}

	function validarCamposMails() {
		if (mail.value == "" && mail2.value == "") {
			$('.alert-message').alert('close')
			alert("Debe de ingresar al menos una direccion de email");
			return false;
		}

		return true;
	}

	function cargarCombos() {
		pais.value = "${usuarioLogeado.pais}";
		prov.value = "${usuarioLogeado.idProvicia}";
		pregunta.value = "${usuarioLogeado.idPreguntaSecreta}";

		if ("${usuarioLogeado.sexo}" != "") {
			if ("${usuarioLogeado.sexo}" == "M") {
				masculino.checked = true;
			} else {
				femenino.checked = true;
			}
		}
	}

	function volverMisFotos() {
		form.action = "/forward";
		form.submit();
	}

	function salir() {
		form.action = "/logout_beta";
		form.submit();
	}

	/*
	 </servlet-mapping>
	 <session-config>
	 <session-timeout>
	 30
	 </session-timeout>
	 </session-config>
	 */

	//Creo una instancia de la clase Menu
	var menu1 = new Desplegable();
	//Creo una propiedad "items", la cual es el array de ítems que tendrá nuestro menu, la creo fuera de la clase ya que nos permite personalizar el menu sin tener que editar la clase.
	menu1.items = new Array([ "item-0", "javascript:;",
			"${usuarioLogeado.nombreUsr}" ], [ "item-1", "javascript:;",
			"Album de Fotos" ]);

	//Creo una propiedad "suitems", la cual es el array de sub ítems que apareceran dentro de cada ítem.
	menu1.subitems = new Array([
			[ "subitem-0", "javascript:editar();", "Editar Cuenta", "_self" ],
			[ "subitem-1", "javascript:salir();", "Cerrar Sesion", "_self" ] ],
			[
					[ "subitem-0", "javascript:;", "Subir Fotos", "_self" ],
					[ "subitem-1", "javascript:;", "Editar Fotos", "_self" ],
					[ "subitem-2", "javascript:volverMisFotos();", "Mis Fotos",
							"_self" ] ]);
</script>
</body>
</html>