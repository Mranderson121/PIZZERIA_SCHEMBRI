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
import model.Utente;

@Path("/utenti")
public class UtenteService {

	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Utente> getUtentiJSON(){
		List<Utente> utentiList = DAO.getAllUtenti();
		return utentiList;
	}
	
	@GET
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Utente getUtente(@PathParam("id") long id) {
		return DAO.getUtenteById(id);
	}
	
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public void aggiungiUtente(Utente utente) {
		DAO.aggiungiUtente(utente);
	}
	
	@PUT
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public void aggiornaUtente(Utente utente) {
		DAO.aggiornaUtente(utente);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public void deleteEmployee(@PathParam("id") long id) {
		DAO.rimuoviUtente(id);
	}
}
