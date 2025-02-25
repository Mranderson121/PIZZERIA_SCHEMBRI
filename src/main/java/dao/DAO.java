package dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.Impasto;
import model.Ingrediente;
import model.Pizza;
import model.Utente;

@Stateless
public class DAO {

	@PersistenceContext
	private EntityManager entityManager;

	public void creaPizza(long idUtente, String nomePizza, long idImpasto, String[] ingredientiSelezionati) {

		Utente utente = entityManager.find(Utente.class, idUtente);
		Impasto impasto = entityManager.find(Impasto.class, idImpasto);

		Pizza pizza = new Pizza();
		pizza.setNome(nomePizza);
		pizza.setImpasto(impasto);
		pizza.setUtente(utente);

		List<Ingrediente> listaIngredienti = new ArrayList<Ingrediente>();
		for (String ingr : ingredientiSelezionati) {
			Ingrediente ingrediente = entityManager.find(Ingrediente.class, Long.parseLong(ingr));

			listaIngredienti.add(ingrediente);
		}

		pizza.setIngredienti(listaIngredienti);
		entityManager.persist(pizza);

	}

	public void aggiornaPizza(long idPizza, String[] ingredientiNew, long idImpastoNew, String newNomePizza) {
		Pizza pizza = getPizzaById(idPizza);
		Utente utente = pizza.getUtente();
		Impasto newImpasto = entityManager.find(Impasto.class, idImpastoNew);
		
		pizza.setNome(newNomePizza);
		pizza.setImpasto(newImpasto);
		
		List<Ingrediente> newListaIngredienti = new ArrayList<Ingrediente>();
		for (String ingr : ingredientiNew) {
			Ingrediente ingrediente = entityManager.find(Ingrediente.class, Long.parseLong(ingr));

			newListaIngredienti.add(ingrediente);
		}
		
		pizza.setIngredienti(newListaIngredienti);
		
		entityManager.merge(pizza);
		
		//rimuoviPizza(pizza.getId());
		//creaPizza(utente.getId(), newNomePizza, idImpastoNew, ingredientiNew);
	}

	public void rimuoviPizza(long idPizza) {
		Pizza pizza = entityManager.find(Pizza.class, idPizza);
		entityManager.remove(pizza);
	}

	public Utente getUtente(String username, String password) {
		List<Utente> listaResult = new ArrayList<>();
		String sql = "select u from Utente u where u.username = :username and u.password = :password";
		TypedQuery<Utente> query = entityManager.createQuery(sql, Utente.class).setParameter("username", username)
				.setParameter("password", password);
		listaResult = query.getResultList();
		return listaResult.isEmpty() ? null : listaResult.get(0);
	}

	public void rimuoviUtente(long id) {
		Utente utente = entityManager.find(Utente.class, id);
		if (utente != null) {
			entityManager.remove(utente);
		}
	}

	public void rimuoviIngrediente(long id) {
		Ingrediente ingrediente = entityManager.find(Ingrediente.class, id);
		if (ingrediente != null) {
			entityManager.remove(ingrediente);
		}
	}

	public void rimuoviImpasto(long id) {
		Impasto impasto = entityManager.find(Impasto.class, id);
		if (impasto != null) {
			entityManager.remove(impasto);
		}
	}

	public List<Ingrediente> getIngredienti() {
		String query = "select i from Ingrediente i";
		List<Ingrediente> ingredienti = (List<Ingrediente>) entityManager.createQuery(query, Ingrediente.class)
				.getResultList();
		return ingredienti;
	}

	public List<Impasto> getImpasti() {
		String query = "select i from Impasto i";
		List<Impasto> impasti = (List<Impasto>) entityManager.createQuery(query, Impasto.class).getResultList();
		return impasti;
	}

	public List<Utente> getAllUtenti() {
		String query = "select u from Utente u";
		List<Utente> utenti = (List<Utente>) entityManager.createQuery(query, Utente.class).getResultList();
		return utenti;
	}

	public Utente getUtenteById(long key) {
		return entityManager.find(Utente.class, key);
	}

	public Pizza getPizzaById(long id) {
		return entityManager.find(Pizza.class, id);
	}

	public Ingrediente getIngredienteById(long id) {
		return entityManager.find(Ingrediente.class, id);
	}

	public Impasto getImpastoById(long id) {
		return entityManager.find(Impasto.class, id);
	}

	public void aggiungiUtente(Utente utente) {
		entityManager.persist(utente);
	}

	public void aggiungiIngrediente(Ingrediente ingrediente) {
		entityManager.persist(ingrediente);
	}

	public void aggiungiImpasto(Impasto impasto) {
		entityManager.persist(impasto);
	}

	public void aggiornaUtente(Utente updateUtente) {
		Utente oldUtente = entityManager.find(Utente.class, updateUtente.getId());
		oldUtente.setUsername(updateUtente.getUsername());
		oldUtente.setPassword(updateUtente.getPassword());
		entityManager.merge(oldUtente);
	}

	public void aggiornaIngrediente(Ingrediente udpateIngrediente) {
		Ingrediente oldIngrediente = entityManager.find(Ingrediente.class, udpateIngrediente.getId());
		oldIngrediente.setNome(udpateIngrediente.getNome());
		entityManager.merge(oldIngrediente);
	}

	public void aggiornaImpasto(Impasto updateImpasto) {
		Impasto oldImpasto = entityManager.find(Impasto.class, updateImpasto.getId());
		oldImpasto.setNome(updateImpasto.getNome());
		entityManager.merge(oldImpasto);
	}
}