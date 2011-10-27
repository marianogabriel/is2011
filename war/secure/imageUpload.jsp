<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="ar.kennedy.is2011.session.Session"%>
<%@page import="ar.kennedy.is2011.session.SessionManager"%>
<%@page import="ar.kennedy.is2011.db.dao.AbstractDao"%>
<%@page import="ar.kennedy.is2011.db.entities.PictureEy"%>
<%@page import="ar.kennedy.is2011.utils.WebUtils"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="ar.kennedy.is2011.db.entities.AlbumEy"%>
<%@page import="java.util.List"%>
<%@page import="ar.kennedy.is2011.constants.Constants"%>
<%@page import="ar.kennedy.is2011.db.entities.Usuario"%>
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
	SearchPicturesModel searchPicturesModel = new SearchPicturesModel();
	Usuario user = (Usuario) userSession.getElement("user");
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

<%@page import="ar.kennedy.is2011.models.SearchPicturesModel"%>
<%@page import="java.util.Set"%><html>
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
			<td>Seleccion del album:</td>
			<td>
				<select id="album_id" name="album_id">
					<%
						if(StringUtils.isNotBlank(getValue(picture.getAlbumId()))) {
					%>
						<option value="<%= getValue(picture.getAlbumId()) %>"><%= getValue(picture.getAlbumId()) %></option>
						<option value="Elegir">Elegir</option>
					<%
						} else {
					%>
						<option value="Elegir">Elegir</option>
					<%
						}
					
						Set<AlbumEy> albums = searchPicturesModel.getAlbumsToBeDisplayedByUser(user.getNombreUsr());	
						
						for(AlbumEy album : albums) {
					%>
							<option value="<%= album.getAlbumId() %>"><%= album.getAlbumId() %></option>
					<%
						}
					%>
				</select>
			</td>
			<td><span class="validator" style="display: <%= errors.containsKey("album_id") ? "block" : "none" %>"><p class="required"><%= errors.containsKey("album_id") ? errors.get("album_id") : "" %></p></span></td>
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