<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	WebUtils.validateMandatoryParameters(request, new String[] {"id"});
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="ar.kennedy.is2011.utils.WebUtils"%>
<%@page import="ar.kennedy.is2011.social.Social"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Picture</title>
</head>
<body>
	<%= Social.addLinks(WebUtils.getCompleteUrlForPicture(request, WebUtils.getParameter(request, "id")), "Picture") %>
	<img src="/image?id=<%= WebUtils.getParameter(request, "id") %>">
</body>
</html>