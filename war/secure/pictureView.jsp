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
<%
	WebUtils.validateMandatoryParameters(request, new String[] { "id" });
	Session userSession = SessionManager.get(request,
			WebUtils.getSessionIdentificator(request));
	Usuario user = (Usuario) userSession.getElement("user");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="ar.kennedy.is2011.utils.WebUtils"%>
<%@page import="ar.kennedy.is2011.social.Social"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Picture</title>
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
				<a class="brand" href="#">Picture</a>
				<ul class="nav">
					<li class="active"><a href="/secure/main.jsp">Inicio</a></li>
				</ul>
				<p class="pull-right">
					Logueado como <a href="/secure/editarCuentaUsuario.jsp"><%=user.getNombreUsr()%></a>
					<a href="/logout"> Cerrar sesion </a>
				</p>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<ul class="media-grid">
				<li>
					<img class="span16" src="/image?id=<%=WebUtils.getParameter(request, "id")%>&version=O">
				</li>
				<li><%=Social.addLinks(WebUtils.getCompleteUrlForPicture(request,WebUtils.getParameter(request, "id")), "Picture")%></li>
			</ul>
		</div>
	</div>
</body>
</html>