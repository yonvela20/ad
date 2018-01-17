package serpis.ad;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.jboss.logging.LoggerProvider;

public class PruebaVenta {

	private static EntityManagerFactory entityManagerFactory; 
 
	public static void main(String[] args) {
		entityManagerFactory = Persistence.createEntityManagerFactory("serpis.ad.gventa");
		
		//showAll();
		
		//modify(2L);
		//remove(19L);
		
		//newCategoria();
		
		showAll();
		showArticulo();
		
		entityManagerFactory.close();
	}
	
	private static void showAll() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Categoria> categorias = entityManager.createQuery("from Categoria order by id", Categoria.class).getResultList();
		for (Categoria categoria : categorias)
			System.out.println(categoria);
		entityManager.getTransaction().commit();
	}
	
	private static void showArticulo() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Articulo> articulos = entityManager.createQuery("from Articulo order by id", Articulo.class).getResultList();
		for (Articulo articulo : articulos)
			System.out.println(articulo);
		entityManager.getTransaction().commit();
	}
	
	
	
}