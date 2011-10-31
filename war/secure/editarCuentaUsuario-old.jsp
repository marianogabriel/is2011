<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="/css/css2.css" type="text/css"
	media="screen" />
<script type="text/javascript" src="/js/menu.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Editar Cuenta</title>
</head>

<body onLoad="menu1.escribeacordeon('menu',22,5);" class="my_body">

	<form name="form" action="/editarCuenta" method="post">
		<input type="hidden" name="misFotos" value="misFotos">

		<div align="center">
			<h1>Galer&iacute;a de Fotos - UK - 2011</h1>
		</div>
		<hr color="blue"></hr>
		<div id="menu" style="position: absolute; top: 15%; left: 1%;"></div>

		<table border="0" cellpadding="10%" align="center" rules="none">
			<tr>
				<td width="25%" align="center">Editar cuenta de Usuario</td>
			</tr>
			<tr></tr>
			<tr>
				<td align="right">
					<table cellpadding="5" border="1" rules="none" align="center">
						<tr>
							<td align="right">Dirección de mail:&nbsp;</td>
							<td><input type="text" name="mailPrimario" size="40"
								maxlength="42" id="mail" value="${usuarioLogeado.mail}"></td>
						</tr>
						<tr>
							<td align="right">Dirección de mail alternativo:&nbsp;</td>
							<td><input type="text" name="mailSecundaro" size="40"
								maxlength="42" id="mail2"
								value="${usuarioLogeado.mailSecundario}"></td>
						</tr>
						<tr>
							<td align="right">Nombre:&nbsp;</td>
							<td><input type="text" name="nombreUsr" size="40"
								maxlength="42" id="nomUsr" value="${usuarioLogeado.nombre}"></td>
						</tr>
						<tr>
							<td align="right">Apellido:&nbsp</td>
							<td><input type="text" name="apellidoUsr" size="40"
								maxlength="42" id="apeUsr" value="${usuarioLogeado.apellido}"></td>
						</tr>
						<tr>
							<td align="right">Fecha de nacimiento:&nbsp;</td>
							<td><input type="text" name="fechaNacimiento" maxlength="12"
								size="30" id="fechaNac"
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
									<optionvalue"">-- Seleccionar una provincia --</option>
						    	<option value="1">Buenos Aires</option>
						    	<option value="2">Cordoba</option>
						    	<option value="3">Santa Fe</option>
						    	<option value="4">Salta</option>
						    </select></td>
						</tr>
						<tr>
							<td align="right">Cambiar Contraseña:&nbsp;</td>
							<td><input type="button" name="botonCambiar" value="Cambiar"></td>
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
				<td align="center"><input type="button" name="limpiar"
					value="Limpiar" onclick="limpiarCampos();">&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" name="save" value="Guardar"
					onclick="guardar();"></td>
			</tr>
		</table>

	</form>

</body>

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


</html>