<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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

	</div>

</body>
</html>