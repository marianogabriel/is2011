<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cargar imagen</title>
</head>
<body>
<form method="post" action="/upload" enctype="multipart/form-data">
	<h4>Sube tu imagen</h4>
	<table>
		<tr>
			<td>Archivo de imagen:</td>
			<td><input id="picture_file" name="picture_file" type="file" /></td>
		</tr>
		<tr>
			<td>Nombre de la imagen:</td>
			<td><input id="picture_name" name="picture_name" type="text" /></td>
		</tr>
		<tr>
			<td>Nombre del album:</td>
			<td><input id="album_name" name="album_name" type="text" /></td>
		</tr>
		<tr>
			<td>Tags:</td>
			<td><input id="tags" name="tags" type="text" /></td>
		</tr>
		<tr>
			<td>Url:</td>
			<td><input id="url" name="url" type="text" /></td>
		</tr>
		<tr>
			<td><input type="submit" value="Enviar" /></td>
		</tr>
	</table>
</form>
</body>
</html>