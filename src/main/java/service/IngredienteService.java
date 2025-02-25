package service;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.DAO;
import model.Ingrediente;

@Path("/ingredienti")
public class IngredienteService {
	@EJB
	private DAO DAO;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Ingrediente> getIngredientiJSON() {
		List<Ingrediente> ingredientiList = DAO.getIngredienti();
		return ingredientiList;
	}

	@GET
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_JSON})
	public Ingrediente getIngrediente(@PathParam("id") long id) {
		return DAO.getIngredienteById(id);
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON})
	@Produces({ MediaType.APPLICATION_JSON})
	public void aggiungiIngrediente(Ingrediente ingrediente) {
		DAO.aggiungiIngrediente(ingrediente);
	}

	@PUT
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_JSON})
	public void aggiornaIngrediente(Ingrediente ingrediente) {
		DAO.aggiornaIngrediente(ingrediente);
	}

	@DELETE
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_JSON})
	public void rimuoviIngrediente(@PathParam("id") long id) {
		DAO.rimuoviIngrediente(id);
	}
}
