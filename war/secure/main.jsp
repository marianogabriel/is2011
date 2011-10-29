<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="ar.kennedy.is2011.db.entities.Usuario"%>
<%@page import="ar.kennedy.is2011.session.SessionManager"%>
<%@page import="ar.kennedy.is2011.utils.WebUtils"%>
<%@page import="ar.kennedy.is2011.models.SearchPicturesModel"%>
<%@page import="ar.kennedy.is2011.db.entities.PictureEy"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="java.util.List"%>
<%!
	private static final Integer DEFAULT_FECTH = 2;
%>
<%
	SearchPicturesModel searchPicturesModel = new SearchPicturesModel();
	Usuario user = (Usuario) SessionManager.get(request, WebUtils.getSessionIdentificator(request)).getElement("user");
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>Principal</title>
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
			.show-grid [class*="span"] {
				text-align: left;
			}
		</style>
	</head>
	<body>
		<div class="topbar">
			<div class="topbar-inner">
				<div class="container">
					<a class="brand" href="#">Trabajo Pr&aacute;ctico</a>
					<ul class="nav">
						<li class="active">
							<a href="/secure/main.jsp">Inicio</a>
						</li>
						<li>
							<a href="/secure/albums.jsp">Albums</a>
						</li>
						<li>
							<a href="#buscar">Buscar</a>
						</li>
	
						<li>
							<a href="/secure/imageUpload.jsp">Cargar imagen</a>
						</li>
					</ul>
					<p class="pull-right">
						Logueado como <a href="/secure/editarCuentaUsuario.jsp"><%= user.getNombreUsr() %></a><a href="/logout"> Cerrar sesion</a>
					</p>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="content">
				<!-- Main hero unit for a primary marketing message or call to action -->
				<div class="hero-unit">
					<ul class="media-grid">
						<li>
							<div class="span4">
								<%
									PictureEy lastImageUpload = searchPicturesModel.getLastPictureUploadByUser(user.getNombreUsr());
									
									if(lastImageUpload != null) {
								%>
									<a href="/secure/pictureView.jsp?id=<%= lastImageUpload.getPictureId() %>"><img class="thumbnail" src="/image?id=<%= lastImageUpload.getPictureId() %>&version=I" width="150" height="150" alt=""></a>
								<%
									} else {
								%>
									<a href="#"> <img class="thumbnail" src="http://placehold.it/150x150" alt=""> </a>
								<%
									}
								%>
							</div>
							<div class="span10">
								<h1>
									<% 
										if(StringUtils.isNotBlank(user.getNombre()) && StringUtils.isNotBlank(user.getApellido())) {
											out.print((new StringBuilder()).append(user.getNombre()).append(" ").append(user.getApellido()).toString());
										
										} else {
											out.print(user.getNombreUsr());
										}
									%>
								</h1>
							</div>
						</li>
					</ul>
				</div>
				<h2>&Uacute;ltimas fotos</h2>
				<%
					List<PictureEy> pictures = searchPicturesModel.getPicturesToBeDisplayedByUser(user.getNombreUsr());
					Integer totalPictures = pictures.size();
					Integer startFetch = WebUtils.getParameter(request, "sf") != null ? new Integer(WebUtils.getParameter(request, "sf")) : 0;
					Integer endFetch = WebUtils.getParameter(request, "ef") != null ? pictures.size() < new Integer(WebUtils.getParameter(request, "ef")) ? pictures.size() : new Integer(WebUtils.getParameter(request, "ef")) : pictures.size() < DEFAULT_FECTH ? pictures.size() : DEFAULT_FECTH;
					
					for(int i = startFetch; i < endFetch; i++) {
				%>
				<div class="well">
					<ul class="media-grid">
						<li>
							<div class="row">
								<div class="span3">
									<a href="/secure/pictureView.jsp?id=<%= pictures.get(i).getPictureId() %>"><img class="thumbnail" src="/image?id=<%= pictures.get(i).getPictureId() %>&version=H" alt="" width="90" height="90"> </a>
								</div>
								<p>
									Acciones
								</p>
								<div class="span12">
									<a href="/secure/pictureView.jsp?id=<%= pictures.get(i).getPictureId() %>"><button class="btn info">
										Ver
									</button></a>
									<a href="/secure/imageUpload.jsp?id=<%= pictures.get(i).getPictureId() %>"><button class="btn primary">
										Editar
									</button></a>
									<a href="/upload?action=delete&id=<%= pictures.get(i).getPictureId() %>"><button class="btn danger">
										Eliminar
									</button></a>
								</div>
							</div>
						</li>
					</ul>
				</div>
				<%
					}
				%>
				<%
					if(pictures.size() > 0) {
				%>
				<div class="pagination">
					<ul>
						<%
							if(StringUtils.isNotBlank(WebUtils.getParameter(request, "sf")) && (new Integer(WebUtils.getParameter(request, "sf"))) > 0) {
						%>
							<li class="prev disabled">
								<a href="main.jsp?sf=<%= StringUtils.isNotBlank(WebUtils.getParameter(request, "sf")) ? (new Integer(WebUtils.getParameter(request, "sf")) - DEFAULT_FECTH) >= 0 ? (new Integer(WebUtils.getParameter(request, "sf")) - DEFAULT_FECTH) : 0 : 0 %>&ef=<%= StringUtils.isNotBlank(WebUtils.getParameter(request, "sf")) ? (new Integer(WebUtils.getParameter(request, "ef")) - DEFAULT_FECTH) > 0 ? (new Integer(WebUtils.getParameter(request, "ef")) - DEFAULT_FECTH) : DEFAULT_FECTH : DEFAULT_FECTH %>">&larr; Previous</a>
							</li>
						<%
							}
						%>
						<%
							for(int i = 0; i < Math.ceil((float) pictures.size() / DEFAULT_FECTH); i++) {
						%>
						<li class="active">
							<a href="main.jsp?sf=<%= i * DEFAULT_FECTH %>&ef=<%= (i * DEFAULT_FECTH) + DEFAULT_FECTH %>"><%= i %></a>
						</li>
						<%
							}
						%>
						<%
							if(StringUtils.isNotBlank(WebUtils.getParameter(request, "ef")) && (new Integer(WebUtils.getParameter(request, "ef"))) < pictures.size() ) {
						%>
						<li class="next">
							<a href="main.jsp?sf=<%= StringUtils.isNotBlank(WebUtils.getParameter(request, "ef")) ? new Integer(WebUtils.getParameter(request, "ef")) : 0 %>&ef=<%= StringUtils.isNotBlank(WebUtils.getParameter(request, "ef")) ? (new Integer(WebUtils.getParameter(request, "ef")) + DEFAULT_FECTH) : DEFAULT_FECTH %>">Next &raquo;</a>
						</li>
						<%
							} else if(StringUtils.isBlank(WebUtils.getParameter(request, "ef")) && (DEFAULT_FECTH * 2) <= pictures.size()) {
						%>
						<li class="next">
							<a href="main.jsp?sf=<%= DEFAULT_FECTH %>&ef=<%=DEFAULT_FECTH * 2 %>">Next &raquo;</a>
						</li>
						<% 
							}
						%>
					</ul>
				</div>
				<%
					}
				%>
				<footer>
					<div id="modal-from-dom" class="modal hide fade">
						<div class="modal-header">
							<a href="#" class="close">&times;</a>
							<h3>Condiciones Legales</h3>
						</div>
						<div class="modal-body">
							<p>
								Etiam porta sem malesuada magna mollis euismod. Integer posuere erat a ante venenatis dapibus posuere velit aliquet. Aenean eu leo quam. Pellentesque ornare sem lacinia quam venenatis vestibulum. Duis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia odio sem nec elit.
							</p>
						</div>
					</div>
					<p>
						<a href="#" data-controls-modal="modal-from-dom"
						data-backdrop="true" data-keyboard="true"> Condiciones Legales </a>
					</p>
				</footer>
			</div>
		</div>
	</body>
</html>