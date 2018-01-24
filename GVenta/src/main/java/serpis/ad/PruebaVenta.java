package serpis.ad;

import java.math.BigDecimal;
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
		showArticulo();
		showCategoria();
		showCliente();
		showPedido();
		newArticulo();
		//showPedidoLinea();
		
		entityManagerFactory.close();
	}
	
	private static void showArticulo() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		List<Articulo> articulos = entityManager.createQuery("from Articulo order by id", Articulo.class).getResultList();
		
		for (Articulo articulo : articulos)
			System.out.println(articulo);
		entityManager.getTransaction().commit();
	}
	
	public static void showCategoria() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Categoria> categorias = entityManager.createQuery("from Categoria order by id", Categoria.class).getResultList();
		for (Categoria categoria : categorias)
			System.out.println(categoria);
		entityManager.getTransaction().commit();
	}
	
	public static void showCliente() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		List<Cliente> clientes = entityManager.createQuery("from Cliente order by id", Cliente.class).getResultList();
		
		for (Cliente cliente : clientes)
			System.out.println(cliente);
		entityManager.getTransaction().commit();
	}
	
	public static void showPedido() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		List<Pedido> pedidos = entityManager.createQuery("from Pedido order by id", Pedido.class).getResultList();
		
		for(Pedido pedido : pedidos) {
			System.out.println(pedido);
		entityManager.getTransaction().commit();
		}
	}
	
//	public static void showPedidoLinea() {
//		EntityManager entityManager = entityManagerFactory.createEntityManager();
//		entityManager.getTransaction().begin();
//		List<PedidoLinea> pedidolineas = entityManager.createQuery("from PedidoLinea order by id", PedidoLinea.class).getResultList();
//		for (PedidoLinea pedidolinea : pedidolineas)
//			System.out.println(pedidolinea);
//		entityManager.getTransaction().commit();
//	}
		
	private static void newArticulo() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Categoria categoria = entityManager.getReference(Categoria.class, 1L);
		
		Articulo articulo = new Articulo();
		
		articulo.setNombre("nuevo " + new Date());
		articulo.setPrecio(new BigDecimal(Math.random()));
		articulo.setCategoria(categoria);
		entityManager.persist(articulo);
		
		entityManager.getTransaction().commit();	
	}

}
	




