<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
</head>
<body>
	<s:form action="login">
		<s:textfield label="Enter Name" name="name" />
		<s:textfield label="Enter Mob. No" name="mobNo" />
		<s:textfield label="Enter Email ID" name="email" />
		<s:submit value="login"></s:submit>
	</s:form>
</body>
</html>