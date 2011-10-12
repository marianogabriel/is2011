<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="ar.kennedy.is2011.session.Session"%>
<%@page import="ar.kennedy.is2011.session.SessionManager"%>
<%@page import="ar.kennedy.is2011.db.dao.AbstractDao"%>
<%@page import="ar.kennedy.is2011.db.entities.PictureEy"%>
<%@page import="ar.kennedy.is2011.utils.WebUtils"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%!
	private String getValue(Object object) {
		return object != null ? object.toString() : "";
	}
%>
<%
	Session userSession = SessionManager.get(request, WebUtils.getSessionIdentificator(request));
	Map<String, Object> errors = userSession.contains("errors") ? ((Map<String, Object>) userSession.getElement("errors")).containsKey("form_errors") ? (Map<String, Object>) ((Map<String, Object>) userSession.getElement("errors")).get("form_errors") : new HashMap<String, Object>() : new HashMap<String, Object>();
	PictureEy picture = null;
	String pictureId = WebUtils.getParameter(request, "id");
	
	if(pictureId != null) {
		AbstractDao<PictureEy> pictureDao = new AbstractDao<PictureEy>();
		
		picture = pictureDao.findById(PictureEy.class, pictureId);
	
	} else if(userSession.contains("picture")) {
		picture = (PictureEy) userSession.getElement("picture");
	
	} else {
		picture = new PictureEy();
	}
	
	if(!"t".equals(WebUtils.getParameter(request, "e"))) {
		errors = new HashMap<String, Object>();
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cargar imagen</title>
</head>
<body>
<form method="post" action="/upload?action=<%= pictureId == null ? "add" : "update&id=" + pictureId %>" enctype="multipart/form-data">
	<h4>Sube tu imagen</h4>
	<table>
		<tr>
			<td>Archivo de imagen:</td>
			<td><input id="picture_file" name="picture_file" type="file" /></td>
			<td>o Url:</td>
			<td><input id="url" name="url" type="text" size="80" value="<%= getValue(picture.getUrl()) %>" /></td>
			<td><span class="validator" style="display: <%= errors.containsKey("add_url_or_file") ? "block" : "none" %>"><p class="required"><%= errors.containsKey("add_url_or_file") ? errors.get("add_url_or_file") : "" %></p></span></td>
		</tr>
		<tr>
			<td>Nombre de la imagen:</td>
			<td><input id="picture_name" name="picture_name" type="text" value="<%= getValue(picture.getPictureName()) %>" /></td>
			<td><span class="validator" style="display: <%= errors.containsKey("picture_name") ? "block" : "none" %>"><p class="required"><%= errors.containsKey("picture_name") ? errors.get("picture_name") : "" %></p></span></td>
		</tr>
		<tr>
			<td>Nombre del album:</td>
			<td><input id="album_name" name="album_name" type="text" value="<%= getValue(picture.getAlbumName()) %>" /></td>
			<td><span class="validator" style="display: <%= errors.containsKey("album_name") ? "block" : "none" %>"><p class="required"><%= errors.containsKey("album_name") ? errors.get("album_name") : "" %></p></span></td>
		</tr>
		<tr>
			<td>Tags:</td>
			<td><input id="tags" name="tags" type="text" value="<%= getValue(picture.getTags()) %>" /></td>
			<td><span class="validator" style="display: <%= errors.containsKey("tags") ? "block" : "none" %>"><p class="required"><%= errors.containsKey("tags") ? errors.get("tags") : "" %></p></span></td>
		</tr>
		<tr>
			<td></br><input type="submit" value="Enviar" /></td>			
		</tr>
		<tr>
			<td><span class="validator" style="display: <%= errors.containsKey("mandatory_parameters") ? "block" : "none" %>"><p class="required"><%= errors.containsKey("mandatory_parameters") ? errors.get("mandatory_parameters") : "" %></p></span></td>
		</tr>
	</table>
</form>
</body>
</html>