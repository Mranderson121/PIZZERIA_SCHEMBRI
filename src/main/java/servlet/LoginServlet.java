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

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		Utente utente = DAO.getUtente(username, password);

		if (utente != null) {
			HttpSession session = request.getSession();
			session.setAttribute("utente", utente);
			List<Ingrediente> ingredienti = DAO.getIngredienti();
			request.setAttribute("ingredienti", ingredienti);

			List<Impasto> impasti = DAO.getImpasti();
			request.setAttribute("impasti", impasti);

			request.getRequestDispatcher("dashboard.jsp").forward(request, response);
		} else {
			request.setAttribute("errorMessage", "Dati Errati");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
}