<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome Page</title>
</head>
<body>
	<h1>Congratulations! You've Successfully Logged In</h1>
	<h1>Welcome <s:property value="name"/>!!!</h1>
	<h1>Your Mobile Numbers is <s:property value="mobNo"/></h1>
	<h1>Your Email ID is <s:property value="email"/></h1>
</body>
</html>