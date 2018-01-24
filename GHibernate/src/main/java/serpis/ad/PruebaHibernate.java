package serpis.ad;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.w3c.dom.events.Event;
import org.jboss.*;



public class PruebaHibernate {

	private static EntityManagerFactory entityManagerFactory; 
	
	public static void main(String[] args) {
		Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		
		entityManagerFactory =
				Persistence.createEntityManagerFactory("serpis.ad.ghibernate");
	
		showAll();
		
		remove(10L);
		
		//newCategoria();
		
		showAll();
		
		entityManagerFactory.close();
	}
	
	private static void showAll() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Categoria> categorias = entityManager
				.createQuery("from Categoria order by id", Categoria.class).getResultList();
		for (Categoria categoria : categorias)
			System.out.println(categoria);
		entityManager.getTransaction().commit();
	}
	
	private static void newCategoria() {
		System.out.println("Creando categoría nueva ");
		Categoria categoria = new Categoria();
		categoria.setNombre("Nuevo " + new Date());
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(categoria);
		System.out.println("Creada " + categoria);
		entityManager.getTransaction().commit();
	}
	
	private static void remove(long id) {
		System.out.println("Borrando " + id);
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		// Categoria categoria = entityManager.find(Categoria.class, id);
		Categoria categoria = entityManager.getReference(Categoria.class, id);
		entityManager.remove(categoria);
		entityManager.getTransaction().commit();
	}
	
	private static void modify(long id) {
		System.out.println("Modificando " + id);
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		//Categoria categoria = entityManager.find(Categoria.class, id);
		Categoria categoria = new Categoria();
		categoria.setId(id);
		categoria.setNombre("modificando " + new Date());
		entityManager.getTransaction().commit();
	}


}