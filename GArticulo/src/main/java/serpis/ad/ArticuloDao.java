package serpis.ad;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ArticuloDao {
	
	public static void volverMenu() throws SQLException{
		Scanner scn = new Scanner(System.in);
		System.out.println("Quieres volver al menu? Y/N");
		String respuesta = scn.nextLine().toLowerCase();
		if(respuesta.equals("y")) {
			Menu.Menu();
		}else {
			System.out.println("ADIOS");
			System.exit(0);
		}
	}
	//ELIMINAR
	public static void Eliminar() throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/dbprueba","root","sistemas");
		Statement stmt = connection.createStatement();
		
		Scanner scn = new Scanner(System.in);
		System.out.println("Dime la ID del artículo que desea eliminar: ");
		long id = scn.nextLong();
		
		String sql = "DELETE FROM articulo where ID = " + id;
		PreparedStatement pstmt = connection.prepareStatement(sql);
		
	    pstmt.executeUpdate();
	    
	    System.out.println("Articulo eliminado");
	    stmt.close();
      	connection.close();
      	volverMenu();
	}
	//CONSULTAR
	public static void Consultar() throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/dbprueba","root","sistemas");
		Statement stmt = connection.createStatement();
		
		Scanner scn = new Scanner(System.in);
		System.out.println("Dime la ID del artículo que desea consultar: ");
		int id = scn.nextInt();
		String sql = "SELECT * FROM articulo where ID =" + id;
	    ResultSet rs = stmt.executeQuery(sql);
	    
		while(rs.next()){
	         String nombre = rs.getString("nombre");
	         BigDecimal precio = rs.getBigDecimal("precio");
	         long categoria = rs.getLong("categoria");
	         System.out.println("ID: " + id + " Nombre: " + nombre + " Precio: " + precio + " Categoria: " + categoria);
	      }
	    
	    rs.close();
	    stmt.close();
      	connection.close();
      	volverMenu();
	}
	//EDITAR
	public static void Editar() throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/dbprueba","root","sistemas");
		Statement stmt = connection.createStatement();
		
		Scanner scn = new Scanner(System.in);
		System.out.println("Dime la id del articulo que desea editar: ");
		long id = scn.nextLong();
		String sql = "SELECT * from articulo where ID = "+ id;
		ResultSet rs = stmt.executeQuery(sql);
				
		while(rs.next()){
			String nombre = rs.getString("nombre");
			BigDecimal precio = rs.getBigDecimal("precio");
			long categoria = rs.getLong("categoria");
			
			System.out.println("ID: " + id + "\nNombre " + nombre + "\nPrecio: " + precio + "\nCategoria: " + categoria);
		}
		scn.nextLine();
		System.out.println("Que desea editar?nombre/precio/categoria");
		String edit = scn.nextLine().toLowerCase();
		
		if(edit.equals("nombre")){
			PreparedStatement pstmt = connection.prepareStatement("UPDATE articulo SET nombre = ? WHERE id = ?");
			System.out.println("Que nombre desea poner?");
			String newNombre = scn.nextLine();
			
			pstmt.setString(1, newNombre);
			pstmt.setLong(2, id);
			
			pstmt.executeUpdate();
			pstmt.close();
			
		}else if(edit.equals("precio")) {
			PreparedStatement pstmt = connection.prepareStatement("UPDATE articulo SET precio = ? WHERE id = ?");
			System.out.println("Que precio desea poner?");
			BigDecimal newPrecio = scn.nextBigDecimal();
			
			pstmt.setBigDecimal(1, newPrecio);
			pstmt.setLong(2, id);
			
			pstmt.executeUpdate();
			pstmt.close();
		}else if(edit.equals("categoria")) {
			PreparedStatement pstmt = connection.prepareStatement("UPDATE articulo SET categoria = ? WHERE id = ?");
			System.out.println("Que categoria desea poner?");
			long newCategoria = scn.nextLong();
			
			pstmt.setLong(1, newCategoria);
			pstmt.setLong(2, id);
			
			pstmt.executeUpdate();
			pstmt.close();
		}
	    rs.close();
	    stmt.close();
      	connection.close();
      	volverMenu();
	}
	
	public static void Nuevo() throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/dbprueba","root","sistemas");
		Statement stmt = connection.createStatement();
		
		Scanner scn = new Scanner(System.in);
		
		System.out.println("Introduce el nombre: ");
		String nombre = scn.nextLine();
		
		System.out.println("Introduce el precio: ");
		BigDecimal precio = scn.nextBigDecimal();
		
		System.out.println("Introduce la categoria: ");
		long categoria = scn.nextLong();
		
		PreparedStatement pstmt = connection.prepareStatement("INSERT INTO articulo (nombre, precio, categoria) VALUES (?, ?, ?)");
		
		pstmt.setString(1, nombre);
		pstmt.setBigDecimal(2, precio);
		pstmt.setLong(3, categoria);

		pstmt.executeUpdate();
	    pstmt.close();
      	connection.close();
      	volverMenu();
	}
	
	//LISTAR
	public static void Listar() throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/dbprueba","root","sistemas");
		Statement stmt = connection.createStatement();
		String sql = "SELECT * FROM articulo";
	    ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()){
	         int id  = rs.getInt("id");
	         String nombre = rs.getString("nombre");
	         BigDecimal precio = rs.getBigDecimal("precio");
	         long categoria = rs.getLong("categoria");
	         System.out.println("ID: " + id + " nombre: " + nombre + " precio: " + precio + " categoria: " + categoria);
	      }
	    
	      rs.close();
	      stmt.close();
	      connection.close();
	      volverMenu();

	}
	
	//SALIR
	public static void Salir() throws SQLException {
		System.out.println("ADIOS");
		System.exit(0);
	}
}