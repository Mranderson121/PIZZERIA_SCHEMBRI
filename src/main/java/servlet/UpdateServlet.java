package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAO;
import model.Impasto;
import model.Ingrediente;
import model.Pizza;
import model.Utente;

@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String newNome = request.getParameter("nuovoNomePizza");
		String newImpastoID = request.getParameter("newImpastoSelezionato");
		String[] newIngredienti = request.getParameterValues("newIngredienteSelezionato");
		String pizzaID = request.getParameter("aggiornaPizza");
		Pizza pizza = DAO.getPizzaById(Long.parseLong(pizzaID));

		List<Ingrediente> ingredienti = DAO.getIngredienti();
		request.setAttribute("ingredienti", ingredienti);

		List<Impasto> impasti = DAO.getImpasti();
		request.setAttribute("impasti", impasti);

		if (newNome != null && newImpastoID != null && newIngredienti != null) {
			DAO.aggiornaPizza(pizza.getId(), newIngredienti, Long.parseLong(newImpastoID), newNome);
			Utente utente = (Utente) request.getSession().getAttribute("utente");
			request.getSession().setAttribute("utente", DAO.getUtenteById(utente.getId()));
			request.getRequestDispatcher("dashboard.jsp").forward(request, response);
		} else {
			request.setAttribute("pizza", pizza);
			request.getRequestDispatcher("update.jsp").forward(request, response);
		}
	}
}