<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<link rel="stylesheet" href="/css/css2.css" type="text/css" media="screen" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registrarse</title>
</head>
<body class="my_body">
	<form name="form" action="/Registracion" method="post">
	<div align="center"><h1>Galer&iacute;a de Fotos - UK - 2011</h1></div>
    <hr color="blue"></hr>
	
		<table border="1" cellpadding="10%" align="center" rules="none" class="borde_2">
			<tr><td width="25%">Nuevo Usuario, por favor ingrese sus datos:</td></tr>
			<tr></tr>
			<tr>
			  <td align="right"  width="70%">
				<table cellpadding="5" border="0" rules="none" align="center">
					<tr>
					   <td align="right">Dirección e-mail*:&nbsp;</td>
					   <td><input type="text" name="email" size="42" maxlength="40" id="mail" value="${usuarioNoRegistrado.mail }"></td>
					</tr>
					<tr>
						<td align="right">Nick-Name*:&nbsp;</td>
						<td><input type="text" name="nombreUsuario" size="42" maxlength="30" id="usr" value="${usuarioNoRegistrado.nombreUsr }"> ${errors}</td>
					</tr>
					<tr>
						<td align="right">Contraseña*:&nbsp;</td>
						<td><input type="password" name="clave" size="42" maxlength="36" id="pass"></td>
					</tr>
					<tr>
						<td align="right">Repita Contraseña*:&nbsp;</td>
						<td><input type="password" name="confirmarClave" size="42" maxlength="36" id="pass2"></td>
					</tr>
					<tr>
						<td align="right" width="30%" >Palabra clave para recuperar su contraseña*:
						(Nombre de mascota, película favorita,
						deporte favorito, etc)&nbsp;</td>
						<td valign="top">
						   <select name="idPreguntaSecreta" id="psecr">
						      <option value="">-Seleccione una pregunta-</option>
						      <option value="1">Cuál es el nombre de su mascota?</option>
							  <option value="2">Nombre de tu abuela materna?</option>
							  <option value="3">Apellido de soltera de su madre?</option>
							  <option value="4">Cuál es tu comida favorita?</option>
							  <option value="5">Fecha de casamiento?</option>
							  <option value="6">Nombre del primer colegio?</option>
						  </select>
					  </td>
					</tr>	
					<tr><td colspan="2" align="center"><input type="text" name="respuestaSecreta" size="42" maxlength="40" id="psec" value="${usuarioNoRegistrado.respuestaSecreta }"></td></tr>						
				      <tr>
				      <td colspan="2" align="center" valign="top">*(Todos los campos son obligatorios)</td>
				      </tr>
				   </table>
			    </td>
			 </tr>
			<tr>
			   <td colspan="2">Hacer clic en Acepto significa que acepta el  Contrato de servicios de Kennedy y la declaración de privacidad. </td>
			</tr>
			<tr>
			   <td align="center"><input type="button" name="registar" value="Enviar" onclick="altaUsuario()"></td>
			</tr> 
		</table>
	
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



  function altaUsuario(){
	if(validarCampos()){
    if(validarClave())
	  form.submit();
	}
  }

  function validarCampos(){
   if(usr.value=="" || mail.value=="" || pass.value=="" || pass2.value=="" || psec.value==""){
      alert("Todos los campos son obligatorios");
	  return false; 
   }
   return true;	
  }

  function limpiarCampos(){
    usr.value = "";
    mail.value = "";
    pass.value = "";
    pass2.value = "";
    psec.value = "";
  }

  function validarClave(){
   if(pass.value == pass2.value){
      return true;
	  }
	alert("Las claves ingresadas no coinciden");  
    return false;	   
  }
  
</script>

</html>