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
import model.Impasto;
import model.Ingrediente;


@Path("/impasti")
public class ImpastoService {

	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Impasto> getImpastiJSON(){
		List<Impasto> impastiList = DAO.getImpasti();
		return impastiList;
	}
	
	@GET
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Impasto getIngrediente(@PathParam("id") long id) {
		return DAO.getImpastoById(id);
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public void aggiungiImpasto(Impasto impasto) {
		DAO.aggiungiImpasto(impasto);
	}
	
	@PUT
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public void aggiornaImpasto(Impasto impasto) {
		DAO.aggiornaImpasto(impasto);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public void rimuoviImpasto(@PathParam("id") long id) {
		DAO.rimuoviImpasto(id);
	}
}
