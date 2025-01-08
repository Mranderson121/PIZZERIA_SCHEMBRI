package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAO;
import model.Impasto;
import model.Ingrediente;
import model.Utente;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public DashboardServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utente utente = (Utente) session.getAttribute("utente");

		String nomePizza = request.getParameter("nomePizza");

		String[] ingredientiSelezionati = request.getParameterValues("ingredienteSelezionato");
		String impastoSelezionato = request.getParameter("impastoSelezionato");

		if (ingredientiSelezionati != null && impastoSelezionato != null) {
			DAO.creaPizza(utente.getId(), nomePizza, Long.parseLong(impastoSelezionato), ingredientiSelezionati);
		}

		utente = DAO.getUtenteById(utente.getId());
		session.setAttribute("utente", utente);

		List<Ingrediente> ingredienti = DAO.getIngredienti();
		request.setAttribute("ingredienti", ingredienti);

		List<Impasto> impasti = DAO.getImpasti();
		request.setAttribute("impasti", impasti);

		request.getRequestDispatcher("dashboard.jsp").forward(request, response);
	}
}