<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<link rel="stylesheet" href="/css/css2.css" type="text/css" media="screen" />

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inicio de sesion</title>
</head>
<body class="my_body">

	<form name="form" action="/login_beta" method="post">
    <div align="center"><h1>Galer&iacute;a de Fotos - UK - 2011</h1></div>
    <hr color="blue"></hr>
    
      <table align="center" style="position:absolute;top:28%;left:27%;"> 
      <tr><td align="center"><b>${iniciarSesion}</b></td></tr>
      <tr>
         <td>   
		      <table align="center" rules="none" class="borde_2">
		        <tr><td></td><td><b>${loginFailure}</b></td></tr>
		      	<tr height="60px">
		      		<td align="right">Nombre de usuario:&nbsp;</td>
		      		<td><input type="text" name="username" maxlength="25" size="40" id="username"></td>
		       	</tr>
		       	<tr>
		      		<td align="right">Contraseña:&nbsp;</td>
		      		<td><input type="password" name="password" maxlength="25" size="40" id="password"></td>
		       	</tr>
		        <tr>
		            <td align="right" colspan="2">
			            <input type="button" value="Iniciar sesión" onclick="loginUsuario()" class="form_btn">&nbsp;
			            <input type="button" value="Cancelar" onclick="limpiarCampos()" class="form_btn">
		            </td>
		        </tr>
		      </table>
   		</td>
   	  </tr>	
   	  <tr><td align="right"><a href="registracionRapida.jsp" >Nuevo Usuario?</a></td></tr> 
   	  <tr><td align="right"><a href="restablecerClave.jsp" >Olvidó su contraseña?</a></td></tr> 	
   	  
    </table>
   
    <div style="position:absolute;top:70%;left:10%;">
     <img alt="" src="/images/publicidad.JPG">
    </div> 

	</form>
</body>

<script type="text/javascript">

var form = document.getElementsByName("form")[0];
var nom = document.getElementById("username");
var pass = document.getElementById("password");


  function loginUsuario(){
	if(validarCampos()){
	   form.submit();
	}else{
         alert("El nombre de usuario y el password son obligatorios!");
		}
  }


  function validarCampos(){
   if(nom.value=="" || pass.value=="" ){
      return false; 
   }
   return true;	
  }

  function limpiarCampos(){
    nom.value = "";
    pass.value = "";
  }

  
</script>

</html>