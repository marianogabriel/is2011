<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="ar.kennedy.is2011.session.Session"%>
<%@page import="ar.kennedy.is2011.session.SessionManager"%>
<%@page import="ar.kennedy.is2011.db.dao.AbstractDao"%>
<%@page import="ar.kennedy.is2011.db.entities.PictureEy"%>
<%@page import="ar.kennedy.is2011.utils.WebUtils"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="ar.kennedy.is2011.picture.UploadedFile"%>
<%@page import="ar.kennedy.is2011.models.SearchAlbumModel"%>
<%@page import="ar.kennedy.is2011.db.entities.AlbumEy"%>
<%@page import="ar.kennedy.is2011.db.entities.PictureAlbumEy"%>
<%@page import="ar.kennedy.is2011.db.entities.Usuario"%>
<%@page import="java.util.List"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<%
// 	Object albums; 
//     session=request.getSession(true); 
//     albums = UploadedFile.getAlbumns();

	SearchAlbumModel searchAlbumModel = new SearchAlbumModel();
	Usuario user = (Usuario) SessionManager.get(request, WebUtils.getSessionIdentificator(request)).getElement("user");

	List<AlbumEy> albums = searchAlbumModel.getAllAlbums();

%> 
 
</head>
<body onload="cargarAlbums()">
<form id="vv">

<table>
		<tr>
		<td>Nombre del album:</td>
		<td>
			<select id="comboAlbum" onchange="llamarPopUp()">
			  
			</select>
		</td>
		</tr>
</table>

<script language="JavaScript"> 

	function cargarAlbums()
	{

		var list = (<%=albums%>);
		var combo = document.getElementById('comboAlbum');
		combo.options.length = 0;
		//Agrero la opcion nuevo album
		var option = document.createElement('option');
    	combo.options.add(option);
		option.value = 0;
		option.innerText = '...';
		
		var option = document.createElement('option');
    	combo.options.add(option);
		option.value = 0;
		option.innerText = 'Nuevo';
		
		for (i=0;i<list.length;i++)
		{
	        var option = document.createElement('option');
		 // añadir el elemento option y sus valores
      		combo.options.add(option);
			option.value = i + 1;
			option.innerText = list[i];
		}
       
	}
	function llamarPopUp()
	{
		 var valor = document.getElementById('comboAlbum').options[document.getElementById('comboAlbum').selectedIndex].innerText;
		 
		 if (valor == 'Nuevo' )
		 {
			 var name=prompt("Nombre del album","");
			 if (name!=null && name!="")
				 if (!existeAlbum(name))
				  {
					//Vuelvo a cargar combo con el contenido de la base de datos
					cargarAlbums();
					//agrego al combo mi nuevo nombre de album
					var option = document.createElement('option');
					var combo = document.getElementById('comboAlbum');
     		      	combo.options.add(option);
					option.value = name;
					option.innerText = name;
					combo.options[combo.length - 1].selected =  "1";
					//document.getElementById('album_name').innerText = name;
				  }
				 else
				 {
					alert('Ya existe el album ' + name);
					llamarPopUp();
				 }
		 
		 }
	}

	function existeAlbum(name)
	{
		//recorro combo en busca de este valor
		var combo = document.getElementById('comboAlbum');
		for (i=0;i<combo.length;i++)
		{
			if (name==combo.options[i].innerText) 
				return true;
		}
		return false;
	}
	

function Abrir_ventana (pagina) {
		var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, width=250, height=250, top=85, left=140"; modal=yes;
		
		if (window.showModalDialog) {
			window.showModalDialog(pagina,"","dialogWidth:255px;dialogHeight:250px");
		} 
		else 
		{
			window.open(pagina,"",opciones);
		}
	}
</script>
</form>
</body>

</html>