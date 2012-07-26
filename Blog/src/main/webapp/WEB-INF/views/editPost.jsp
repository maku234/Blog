<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href="/blog/resources/css/style.css" />
<link rel="stylesheet" type="text/css"
	href="/blog/resources/css/ui-lightness/jquery-ui-1.8.22.custom.css">
<script type="text/javascript"
	src="/blog/resources/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="/blog/resources/js/jquery-ui-1.8.22.custom.min.js"></script>
<script type="text/javascript"
	src="/blog/resources/js/tags_autocompletion.js"></script>
<title>Edit post</title>
</head>
<body>
	<div class="menu-centered">
		<ul>
			<li><a href="../../">Main page</a></li>
			<li><a href="../">Admin page</a></li>

		</ul>
	</div>
	<div class="menu-centered">
		<ul>
			<li><a href="../add">Add new post</a></li>
			<li><a href="../../j_spring_security_logout"> Logout</a></li>


		</ul>
	</div>
	<h1>Edit post</h1>


	<form:form modelAttribute="post" method="POST" action="${post.getId()}">
		<table class="center-table">

			<tr>
				<td><form:label path="title">
						<h3>Title:</h3>
					</form:label> <form:errors path="title" cssClass="error" /></td>
			</tr>
			<tr>
				<td><form:input path="title" size="105" /></td>
			</tr>

			<tr>
				<td><form:label path="text">
						<h3>Text:</h3>
					</form:label> <form:errors path="text" cssClass="error" /></td>
			</tr>
			<tr>
				<td><form:textarea path="text" cols="80" rows="20" /></td>
			</tr>

			<tr>
				<td><form:label path="">
						<h3>Tags:</h3>
					</form:label> <form:errors cssClass="error" /></td>
			</tr>
		<div class="ui-widget">
				<tr>
					<td><form:input name="tags" path="" id="tags" size="105" value="${taglist}" /></td>
				</tr>
			</div>
		</table>

		<input type="submit" value="Save" />
	</form:form>
	<div class="content">
		<h2>Comments:</h2>
		<c:forEach var="comment" items="${post.getComments()}">
			<div>
				<h4>${comment.getAdderName()}</h4>
			</div>
			<div>${comment.getDate()}</div>
			<br />
			<div>${comment.getCommentText()}</div>
			<a href="${post.getId()}/delete/${comment.getId()}">Delete</a>
		</c:forEach>
	</div>

</body>
</html>