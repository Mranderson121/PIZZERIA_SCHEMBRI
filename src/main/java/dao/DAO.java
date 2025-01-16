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

		entityManager.refresh(utente);
		entityManager.close();

	}

	public static void aggiornaPizza(long idPizza, String[] ingredientiNew, long idImpastoNew, String newNomePizza) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();

		Pizza pizza = getPizzaById(idPizza);
		Utente utente = pizza.getUtente();
		//Impasto impasto = entityManager.find(Impasto.class, idImpastoNew);
		/*List<Ingrediente> newListaIngredienti = new ArrayList<Ingrediente>();
		for (String ingr : ingredientiNew) {
			Ingrediente ingrediente = entityManager.find(Ingrediente.class, Long.parseLong(ingr));

			newListaIngredienti.add(ingrediente);
		} */
		
		rimuoviPizza(pizza.getId());
		
		creaPizza(utente.getId(), newNomePizza, idImpastoNew, ingredientiNew);
		
	/*	pizza.setNome(newNomePizza);
		pizza.setImpasto(impasto);
		

		

		pizza.setIngredienti(newListaIngredienti);
		
		
		*/
		
		entityManager.getTransaction().commit();
		
		//utente = entityManager.find(Utente.class, utente.getId());
		//entityManager.refresh(utente);
		entityManager.close();
	}

	public static void rimuoviPizza(long idPizza) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();

		Pizza pizza = entityManager.find(Pizza.class, idPizza);
		
		Utente utente = pizza.getUtente();
		
		
		
		entityManager.remove(pizza);
		
		
		entityManager.getTransaction().commit();
		entityManager.refresh(utente);
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

	public static void rimuoviUtente(long id) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        Utente utente = entityManager.find(Utente.class, id);

            
        if (utente != null) {
        	entityManager.remove(utente);
        }

            entityManager.getTransaction().commit(); // Commettiamo la transazione
        
    }
	
	public static void rimuoviIngrediente(long id) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        Ingrediente ingrediente = entityManager.find(Ingrediente.class, id);

            
        if (ingrediente != null) {
        	entityManager.remove(ingrediente);
        }

            entityManager.getTransaction().commit(); // Commettiamo la transazione
        
    }
	
	public static void rimuoviImpasto(long id) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        Impasto impasto = entityManager.find(Impasto.class, id);

            
        if (impasto != null) {
        	entityManager.remove(impasto);
        }

            entityManager.getTransaction().commit(); // Commettiamo la transazione
        
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
	
	public static List<Utente> getAllUtenti() {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		String query = "select u from Utente u";
		List<Utente> utenti = (List<Utente>) entityManager.createQuery(query, Utente.class).getResultList();
		return utenti;
	}

	public static Utente getUtenteById(long key) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		return entityManager.find(Utente.class, key);
	}

	public static Pizza getPizzaById(long id) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		return entityManager.find(Pizza.class, id);
	}
	
	public static Ingrediente getIngredienteById(long id) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		return entityManager.find(Ingrediente.class, id);
	}
	
	public static Impasto getImpastoById(long id) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		return entityManager.find(Impasto.class, id);
	}

	public static void aggiungiUtente(Utente utente) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		
		entityManager.persist(utente);

		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	public static void aggiungiIngrediente(Ingrediente ingrediente) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		
		entityManager.persist(ingrediente);

		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	public static void aggiungiImpasto(Impasto impasto) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		
		entityManager.persist(impasto);

		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	public static void aggiornaUtente(Utente updateUtente) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		
		Utente oldUtente = entityManager.find(Utente.class, updateUtente.getId());
		oldUtente.setUsername(updateUtente.getUsername());
		oldUtente.setPassword(updateUtente.getPassword());

		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	public static void aggiornaIngrediente(Ingrediente udpateIngrediente) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		
		Ingrediente oldIngrediente = entityManager.find(Ingrediente.class, udpateIngrediente.getId());
		oldIngrediente.setNome(udpateIngrediente.getNome());

		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	public static void aggiornaImpasto(Impasto updateImpasto) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		
		Impasto oldImpasto = entityManager.find(Impasto.class, updateImpasto.getId());
		oldImpasto.setNome(updateImpasto.getNome());

		entityManager.getTransaction().commit();
		entityManager.close();
	}
}