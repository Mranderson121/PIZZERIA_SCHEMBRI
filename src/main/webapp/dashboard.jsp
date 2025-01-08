<%@page import="model.*"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pizza Order Dashboard</title>
</head>
	<%
		Utente utente = (Utente) session.getAttribute("utente");
		List<Ingrediente> ingredienti = (List<Ingrediente>) request.getAttribute("ingredienti");
		List<Impasto> impasti = (List<Impasto>) request.getAttribute("impasti");
	%>
<body>
	<h1>
		Benvenuto
		<%=utente.getUsername() %>
	</h1>

	<form action="./DashboardServlet" method="post">
		<div style="display: flex; padding: 10px; ">
			<table border="1" style="margin-right: 20px;">
				<tr>
					<th>Seleziona</th>
					<th>Ingredienti</th>
				</tr>

				<%
					for (Ingrediente ingr : ingredienti) {
				%>

				<tr>
					<td><input type="checkbox" name="ingredienteSelezionato" value="<%=ingr.getId()%>"></td>
					<td><%=ingr.getNome()%></td>
				</tr>

				<%
					}
				%>
			</table>

			<table border="1">
				<tr>
					<th>Seleziona</th>
					<th>Impasti</th>
				</tr>

				<%
					for (Impasto imp : impasti) {
				%>

				<tr>
					<td><input type="radio" name="impastoSelezionato" value="<%=imp.getId()%>"></td>
					<td><%=imp.getNome()%></td>


				</tr>

				<%
					}
				%>
			</table><br /><br />
			</div>

			<div style="margin-top: 20px;">
			<h2>Inserisci nome pizza da realizzare</h2>
			<input type="text" name="nomePizza" required="required"><br /><br />
			<input type="submit" value="Crea">
			</div>
	</form>
	

	<div style="margin-top: 40px;">


		<table border="1" style="margin-top: 20px">
			<tr>
				<th>Nome Pizza</th>
				<th>Impasto</th>
				<th>Ingredienti</th>
				<th>Azioni</th>
			</tr>
				<%
					for (Pizza pizza : utente.getPizze()) {
				%>

			<tr>
				<td><%=pizza.getNome() %></td>
				<td><%=pizza.getImpasto().getNome() %></td>
				<td><%=pizza.stampaIngredienti() %></td>
				<td></td>
			</tr>
			
			<%
					}
			%>
		</table>
	</div>
</body>
</html>