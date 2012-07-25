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
<title>Home</title>
</head>
<body>
	<div class="menu-centered">
		<ul>
			<li><a href="">Main page</a></li>
			<li><a href="admin/">Admin page</a></li>

		</ul>
	</div>
	<h1>My Blog</h1>



	<ol>
		<div class="content">
		<c:forEach var="post" items="${posts}">
			
			
				<div>
					<h2>
						<a href="view/${post.getId()}">${post.getTitle()}</a>
					</h2>
				</div>
				<div>${post.getDate()}</div>

				<div>
					<c:choose>
						<c:when test="${max_lenght < post.getTextLength()}">
							${post.getTextBeginning(max_lenght)} ... <a
								href="view/${post.getId()}">Read more</a>
						</c:when>
						<c:otherwise>
							${post.getText()}
						</c:otherwise>

					</c:choose>

				</div>
				Tags:
				${post.getTags().size()}
				<c:forEach var="tag" items="${post.getTags()}">
					<a href="tag/${tag.getName()}">${tag.getName()}</a>
					
				</c:forEach>
			





			<br />
		</c:forEach>
		</div>

	</ol>

</body>
</html>
