<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href="/blog/resources/css/style.css" />
<title>Login</title>
</head>
<body>
	<div class="menu-centered">
		<ul>
			<li><a href="/blog">Main page</a></li>
			<li><a href="/blog/admin/">Admin page</a></li>

		</ul>
	</div>
	<c:if test="${not empty error}">
		<div class="error">
			Your login attempt was not successful, try again.<br /> Caused :
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</c:if>
	<form name='f' action="<c:url value='j_spring_security_check' />"
		method='POST'>

		<table class="center-table">
			<tr>
				<td><h3>User:</h3></td>
				<td><input type='text' name='j_username' value=''></td>
			</tr>
			<tr>
				<td><h3>Password:</h3></td>
				<td><input type='password' name='j_password' /></td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit"
					value="submit" /></td>
			</tr>

		</table>

	</form>

</body>
</html>