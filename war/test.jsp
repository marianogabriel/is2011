<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" extends="ar.kennedy.is2011.controllers.AbstractController"%>
<%@page import="ar.kennedy.is2011.social.Social"%>
<%!
	public boolean validateLogin() {
		return false;
	}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Test</title>
  </head>

  <body>
    <h1>Test</h1>
		<%= Social.addLinks("http://second-platform.appspot.com/test", "test") %>
		<img src="test" />
  </body>
</html>