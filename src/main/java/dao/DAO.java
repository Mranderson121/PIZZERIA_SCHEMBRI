package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Impasto;
import model.Ingrediente;
import model.Pizza;
import model.Utente;
import util.JPAUtil;

public class DAO {

	public static void creaPizza(long idUtente, String nomePizza, long idImpasto, String[] ingredientiSelezionati) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();

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

		entityManager.getTransaction().commit();
		System.out.println("Pizza " + nomePizza + " creata con Successo");

		entityManager.close();

	}

	public static Utente getUtente(String username, String password) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		List<Utente> listaResult = new ArrayList<>();
		String sql = "select u from Utente u where u.username = :username and u.password = :password";
		TypedQuery<Utente> query = entityManager.createQuery(sql, Utente.class).setParameter("username", username)
				.setParameter("password", password);
		listaResult = query.getResultList();
		return listaResult.isEmpty() ? null : listaResult.get(0);
	}

	public static List<Ingrediente> getIngredienti() {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		String query = "select i from Ingrediente i";
		List<Ingrediente> ingredienti = (List<Ingrediente>) entityManager.createQuery(query, Ingrediente.class)
				.getResultList();
		return ingredienti;
	}

	public static List<Impasto> getImpasti() {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		String query = "select i from Impasto i";
		List<Impasto> impasti = (List<Impasto>) entityManager.createQuery(query, Impasto.class).getResultList();
		return impasti;
	}

	public static Utente getUtenteById(long key) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		return entityManager.find(Utente.class, key);
	}

}