<%@page import="java.util.*"%>
<%@page import="model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Aggiorna pizza</title>
</head>
<%
Utente utente = (Utente) session.getAttribute("utente");
List<Ingrediente> ingredienti = (List<Ingrediente>) request.getAttribute("ingredienti");
List<Impasto> impasti = (List<Impasto>) request.getAttribute("impasti");
Pizza pizza = (Pizza) request.getAttribute("pizza");
%>
<body>
	<h1>
		Aggiorna la pizza
	</h1>

	<form action="./UpdateServlet" method="post">
		<input type="hidden" name="aggiornaPizza" value="<%=pizza.getId() %>">
		<div style="display: flex; padding: 10px; ">
			<table border="1" style="margin-right: 20px;">
				<tr>
					<th>Seleziona</th>
					<th>Ingredienti</th>
				</tr>

				<%
					for (Ingrediente ingr : ingredienti) {
						boolean selected = false;
						for(Ingrediente ingrpizza : pizza.getIngredienti()){
							if(ingr.getId() == ingrpizza.getId()){ 
								selected = true;
								break;
						}
					}
				%>

				<tr>
					<td><input type="checkbox" name="newIngredienteSelezionato" value="<%=ingr.getId()%>" <%=selected ? "checked" : ""%>></td>
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
						boolean selected = false;
						if(imp.getId() == pizza.getImpasto().getId()) selected = true;
				%>

				<tr>
					<td><input type="radio" name="newImpastoSelezionato" value="<%=imp.getId()%>" <%=selected ? "checked" : ""%>></td>
					<td><%=imp.getNome()%></td>


				</tr>

				<%
					}
				%>
			</table><br /><br />
			</div>

			<div style="margin-top: 20px;">
			<h2>Inserisci nuovo nome</h2>
			<input type="text" name="nuovoNomePizza" required="required" value="<%=pizza.getNome() %>"><br /><br />
			<input type="submit" value="Modifica">
			</div>
	</form>
	
	<form action="./DashboardServlet" method="post">
		<div style="margin-top: 20px;">
			<input type="submit" value="Indietro">
		</div>
	</form>
</body>
</html>