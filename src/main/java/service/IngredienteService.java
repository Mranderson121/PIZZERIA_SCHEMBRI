package service;

import java.util.List;

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
import model.Utente;

@Path("/ingredienti")
public class IngredienteService {

	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Ingrediente> getIngredientiJSON(){
		List<Ingrediente> ingredientiList = DAO.getIngredienti();
		return ingredientiList;
	}
	
	@GET
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Ingrediente getIngrediente(@PathParam("id") long id) {
		return DAO.getIngredienteById(id);
	}
	
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public void aggiungiIngrediente(Ingrediente ingrediente) {
		DAO.aggiungiIngrediente(ingrediente);
	}
	
	@PUT
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public void aggiornaIngrediente(Ingrediente ingrediente) {
		DAO.aggiornaIngrediente(ingrediente);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public void rimuoviIngrediente(@PathParam("id") long id) {
		DAO.rimuoviIngrediente(id);
	}
}
