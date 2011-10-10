<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

 
<html>
 

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="/js/menu.js"></script>
<script type="text/javascript" src="/js/album.js"></script>
<link rel="stylesheet" href="/css/css2.css" type="text/css" media="screen" />

<title>Insert title here</title>
</head>
<body onLoad="menu1.escribeacordeon('menu',22,5);" class="my_body">

 <div align="center"><h1>Galer&iacute;a de Fotos - UK - 2011</h1></div>
 <hr color="blue"></hr>
 <div id="menu" style="position:absolute;top:15%;left:1%;"></div>
 <form name="form" action="/logout_beta" method="post">
 
 </form>
 
 
 
 
  
</body>

<script type="text/javascript">

CreaEstilo();

var form = document.getElementsByName("form")[0];

function salir(){
	form.submit();
}

function editar(){
	form.action = "jsp/editarCuentaUsuario.jsp";
	form.submit();
}


//Creo una instancia de la clase Menu
var menu1= new Desplegable();
//Creo una propiedad "items", la cual es el array de ítems que tendrá nuestro menu, la creo fuera de la clase ya que nos permite personalizar el menu sin tener que editar la clase.
menu1.items = new Array(["item-0","javascript:;","${usuarioLogeado.nombreUsr}"],["item-1","javascript:;","Album de Fotos"]);

//Creo una propiedad "suitems", la cual es el array de sub ítems que apareceran dentro de cada ítem.
menu1.subitems = new Array([["subitem-0","javascript:editar();","Editar Cuenta","_self"],["subitem-1","javascript:salir();","Cerrar Sesion","_self"]],[["subitem-0","javascript:;","Subir Fotos","_self"],["subitem-1","javascript:;","Editar Fotos","_self"],["subitem-2","javascript:;","Mis Fotos","_self"]]);





</script>
</html>