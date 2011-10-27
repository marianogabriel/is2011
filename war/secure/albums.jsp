<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
<%!
	public PictureEy getPicture(Iterator<PictureEy> iterator) {
		if(iterator.hasNext()) {
			return iterator.next();
		
		} else {
			return null;
		}
	}
%>
<%
	Session userSession = SessionManager.get(request, WebUtils.getSessionIdentificator(request));
	Map<String, Object> errors = userSession.contains("errors") ? ((Map<String, Object>) userSession.getElement("errors")).containsKey("form_errors") ? (Map<String, Object>) ((Map<String, Object>) userSession.getElement("errors")).get("form_errors") : new HashMap<String, Object>() : new HashMap<String, Object>();
	SearchPicturesModel searchPicturesModel = new SearchPicturesModel();
	Usuario user = (Usuario) userSession.getElement("user");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Albums</title>
</head>
<body>
	<form id="album_form" name="album_form" method="post" action="/secure/albums.jsp">
		Seleccion del album:
		<select id="album_id" name="album_id" onchange="javascript:document.album_form.submit();">
				<option value="Elegir">Elegir</option>
				<%
					Set<AlbumEy> albums = searchPicturesModel.getAlbumsToBeDisplayedByUser(user.getNombreUsr());	
					
					for(AlbumEy album : albums) {
				%>
						<option value="<%= album.getAlbumId() %>"><%= album.getAlbumId() %></option>
				<%
					}
				%>
		</select>
	</form>
	<%
		if(StringUtils.isNotBlank(WebUtils.getParameter(request, "album_id"))) {
			List<PictureEy> pictures = searchPicturesModel.getPictureByAlbum(WebUtils.getParameter(request, "album_id"));
			Iterator<PictureEy> iterator = pictures.iterator();
 	%>
	<table>
		<%
			for(PictureEy picture : pictures) {
		%>
		<tr>
			<td><a href="/secure/pictureView.jsp?id=<%= picture.getPictureId() %>"><img class="thumbnail" src="/image?id=<%= picture.getPictureId() %>&version=H" alt="" width="90" height="90"> </a></td>
		</tr>
		<%
			}
		%>
	</table>
	<%
		}
	%>
</body>
</html>