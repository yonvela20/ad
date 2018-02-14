package serpis.ad;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

//TODO completar mediante hibernate

public class MainClass {

	private static EntityManagerFactory entityManagerFactory;
	public static void main(String[] args) {
		
		//añadir un "*" al final del nombre de la categoria con id 1
		
		entityManagerFactory = Persistence.createEntityManagerFactory("serpis.ad.hexamen");
		
		update(1);
	}
	
	public static void update(long id) {
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		Categoria categoria = entityManager.find(Categoria.class, id);
		categoria.setNombre(categoria.getNombre()+ "*");
		entityManager.persist(categoria);
		entityManager.getTransaction().commit();
		
	}
	

}
