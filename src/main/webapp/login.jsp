<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Login</title>
	</head>

	<body>
		
		<% String errorMessage = (String) request.getAttribute("errorMessage"); 
				if(errorMessage != null){
					
			%>
			
			<p style="color: red"> <%= errorMessage %> </p>
			
			<%
				}
			%>
			
		<h1>Accedi</h1><br/>
		<form action="./LoginServlet" method="post">
			<input type="text" name="username"><br/>
			<input type="password" name="password"><br/>
			<input type="submit" value="Accedi">
		
			
		</form>
	</body>
</html>