<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<%!public PictureEy getPicture(Iterator<PictureEy> iterator) {
		if (iterator.hasNext()) {
			return iterator.next();

		} else {
			return null;
		}
	}%>
<%
	Session userSession = SessionManager.get(request,
			WebUtils.getSessionIdentificator(request));
	Map<String, Object> errors = userSession.contains("errors") ? ((Map<String, Object>) userSession
			.getElement("errors")).containsKey("form_errors") ? (Map<String, Object>) ((Map<String, Object>) userSession
			.getElement("errors")).get("form_errors")
			: new HashMap<String, Object>()
			: new HashMap<String, Object>();
	SearchPicturesModel searchPicturesModel = new SearchPicturesModel();
	Usuario user = (Usuario) userSession.getElement("user");
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Albums</title>
<meta name="GUI para aplicación is2011" content="">
<meta name="Grupo 4 - ¿nombre?" content="">
<!--[if lt IE 9]>
		<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js"></script>
<script src="assets/js/google-code-prettify/prettify.js"></script>
<script src="/js/bootstrap-modal.js"></script>
<link href="/css/bootstrap.css" rel="stylesheet">
<link href="/css/docs.css" rel="stylesheet">
<style type="text/css">
body {
	padding-top: 60px;
}

.show-grid [class *="span"] {
	text-align: left;
}
</style>
</head>
<body>
	<div class="topbar">
		<div class="topbar-inner">
			<div class="container">
				<a class="brand" href="#">Albums</a>
				<ul class="nav">
					<li class="active"><a href="/secure/main.jsp">Inicio</a></li>
					<li><a href="#buscar">Buscar</a></li>
				</ul>
				<p class="pull-right">
					Logueado como <a href="/secure/editarCuentaUsuario.jsp"><%=user.getNombreUsr()%></a><a
						href="/logout"> Cerrar sesion</a>
				</p>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="content">
			<div class="row">
				<form id="album_form" name="album_form" method="post"
					action="/secure/albums.jsp">
					<label for="mediumSelect">Seleccion del album: </label>
					<div class="input">
						<select class="medium" id="album_id" name="album_id"
							onchange="javascript:document.album_form.submit();">
							<option value="Elegir">Elegir</option>
							<%
								Set<AlbumEy> albums = searchPicturesModel
										.getAlbumsToBeDisplayedByUser(user.getNombreUsr());

								for (AlbumEy album : albums) {
							%>
							<option value="<%=album.getAlbumId()%>"><%=album.getAlbumId()%></option>
							<%
								}
							%>
						</select>
					</div>
				</form>
			</div>
			<%
				if (StringUtils.isNotBlank(WebUtils.getParameter(request,
						"album_id"))) {
					List<PictureEy> pictures = searchPicturesModel
							.getPictureByAlbum(WebUtils.getParameter(request,
									"album_id"));
					Iterator<PictureEy> iterator = pictures.iterator();
			%>
			<table>
				<%
					for (PictureEy picture : pictures) {
				%>
				<tr>
					<td>
						<ul class="media-grid">
							<li><a
								href="/secure/pictureView.jsp?id=<%=picture.getPictureId()%>"><img
									class="thumbnail"
									src="/image?id=<%=picture.getPictureId()%>&version=H" alt=""
									width="90" height="90"> </a></li>
						</ul>

					</td>
				</tr>
				<%
					}
				%>
			</table>
			<%
				}
			%>
		</div>
	</div>
</body>
</html>