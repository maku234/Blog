<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href="/blog/resources/css/style.css" />
<title>Admin page</title>
</head>
<body>
	<div class="menu-centered">
		<ul>
			<li><a href="../">Main page</a></li>
			<li><a href="../admin/">Admin page</a></li>

		</ul>
	</div>
	<div class="menu-centered">
		<ul>
			<li><a href="../admin/add">Add new post</a></li>
			<li><a href="../j_spring_security_logout"> Logout</a></li>
			

		</ul>
	</div>
	<ol>
		<c:forEach var="post" items="${posts}">

			<div class="content">
				<div>
					<h2>${post.getTitle()}</h2> <a href="edit/${post.getId()}">Edit</a> <a href="delete/${post.getId()}">Delete</a>
				</div>
				<div>${post.getDate()}</div>

				<div>
					<c:choose>
						<c:when test="${max_lenght < post.getTextLength()}">
							${post.getTextBeginning(max_lenght)} ... 
						</c:when>
						<c:otherwise>
							${post.getText()}
						</c:otherwise>

					</c:choose>

				</div>

			</div>
			



		</c:forEach>


	</ol>

</body>
</html>
