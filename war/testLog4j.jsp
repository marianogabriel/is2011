<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="ar.kennedy.is2011.utils.WebUtils"%>
<%@page import="ar.kennedy.is2011.utils.TestLog4j"%>
<%
	TestLog4j test= new TestLog4j();
%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Testeo de Log4j</title>
	<style type="text/css">
		/* Override some defaults */
		html,body {
			background-color: #eee;
		}

		body {
			padding-top: 40px;
			/* 40px to make the container go all the way to the bottom of the topbar */
		}
		
		.container>footer p {
			text-align: center; /* center align it with the container */
		}
		
		.container {
			/* downsize our container to make the content feel a bit tighter and more cohesive.
						 * NOTE: this removes two full columns from the grid, meaning you only go to 14
						 * columns and not 16.
						 */
			width: 820px;
		}
		/* The white background content wrapper */
		.content {
			background-color: #fff;
			padding: 20px;
			margin: 0 -20px;
			/* negative indent the amount of the padding to maintain the grid system */
			-webkit-border-radius: 0 0 6px 6px;
			-moz-border-radius: 0 0 6px 6px;
			border-radius: 0 0 6px 6px;
			-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .15);
			-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .15);
			box-shadow: 0 1px 2px rgba(0, 0, 0, .15);
		}
		/* Page header tweaks */
		.page-header {
			background-color: #f5f5f5;
			padding: 20px 20px 10px;
			margin: -20px -20px 20px;
		}
		/* Give a quick and non-cross-browser friendly divider */
		.content .span10 {
			margin-left: 0;
			padding-left: 19px;
			border-right: 1px solid #eee;
		}
		
		.topbar .btn {
			border: 0;
		}
	</style>
</head>
<body>
	<div class="container">
		<div class="content">
			<h1>Jsp al solo efecto de probar TestLog4j</h1>
		</div>
	</div>
</body>
</html>