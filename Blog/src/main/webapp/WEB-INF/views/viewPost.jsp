<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href="/blog/resources/css/style.css" />
<title>${post.getTitle()}</title>
</head>
<body>
	<div class="menu-centered">
		<ul>
			<li><a href="../">Main page</a></li>
			<li><a href="../admin/">Admin page</a></li>

		</ul>
	</div>
	<div class="content">
		<div>
			<h1>${post.getTitle()}</h1>
		</div>
		<div>${post.getDate()}</div>
		<br />
		<div>${post.getText()}</div>

		<h2>Comments:</h2>
		<c:forEach var="comment" items="${post.getComments()}">
			<div>
				<h3>${comment.getAdderName()}</h3>
			</div>
			<div>${comment.getDate()}</div>
			<br />
			<div>${comment.getCommentText()}</div>
		</c:forEach>

		<h3>Add comment</h1>


		<form:form modelAttribute="comment" method="POST"
			action="${post.getId()}">
			<table class="center-table">

				<tr>
					<td><form:label path="adderName">
							<h3>Name:</h3>
						</form:label> <form:errors path="adderName" cssClass="error" /></td>
				</tr>
				<tr>
					<td><form:input path="adderName" size="105" /></td>
				</tr>

				<tr>
					<td><form:label path="commentText">
							<h3>Text:</h3>
						</form:label> <form:errors path="commentText" cssClass="error" /></td>
				</tr>
				<tr>
					<td><form:textarea path="commentText" cols="80" rows="10" /></td>
				</tr>

			</table>

			<input type="submit" value="Add" />
		</form:form>
	</div>

</body>
</html>