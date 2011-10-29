<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="/css/css2.css" type="text/css" media="screen" />
<title>Insert title here</title>
</head>
<body class="my_body">

<form name="form" action="" method="post"> 

 <div align="center"><h1>Galer&iacute;a de Fotos - UK - 2011</h1></div>
 <hr color="blue"></hr>
   Olvidó su contraseña, ingrese su email o nick-name:
   
   <table cellpadding="5" border="1" rules="none" align="center" style="position:absolute;top:28%;left:27%;">
		 <tr>
		   <td align="right">Dirección e-mail*:&nbsp;</td>
		   <td><input type="text" name="email" size="42" maxlength="40" id="mail" value="${usuarioNoRegistrado.mail }"></td>
		</tr>
		<tr>
		  <td align="right">Nick-Name*:&nbsp;</td>
		  <td><input type="text" name="nombreUsuario" size="42" maxlength="30" id="usr" value="${usuarioNoRegistrado.nombreUsr }"> ${errors}</td>
		</tr>
	    <tr><td colspan="2" align="right"><input type="button" value="Recuperar Contraseña" onclick="restablecerClave()"></td></tr>
		<tr>
		  <td colspan="2" align="center" valign="top">(Un correo le será enviado con su nueva contraseña)</td>
		</tr>
  </table>

</form>
	   
</body>


<script type="text/javascript">

var form = document.getElementsByName("form")[0];
var mail = document.getElementById("mail");
var usr = document.getElementById("usr");

   function validarCampos(){
	   if(mail.value=="" || usr.value=="" ){
	      return false; 
	   }
	   return true;	
   }

   function restablecerClave(){	  
      if(validarCampos()){
       //form.submit();
      }else
           alert("El mail y el nick son obligatorios");
   }	   
</script>

</html>