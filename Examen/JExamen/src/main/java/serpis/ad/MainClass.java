package serpis.ad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//TODO completar
public class MainClass {

	public static void main(String[] args) throws SQLException {
		
		Categoria categoria = load(1L); 
		categoria.setNombre(categoria.getNombre() + "#j");
		update(categoria);
		
	}
	
	private static Categoria load(Object id) throws SQLException {
		Categoria categoria = new Categoria();
		
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/dbprueba","root","sistemas");
		Statement stmt = connection.createStatement();
		
		String sql = "SELECT * FROM categoria WHERE id = 1L";
		ResultSet rs = stmt.executeQuery(sql);
				
		categoria.setId(rs.getLong("id"));
		categoria.setNombre(rs.getString("nombre"));
	    
		connection.close();
		return categoria;
	}
	
	private static void update(Categoria categoria) throws SQLException{
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/dbprueba","root","sistemas");

		PreparedStatement pstmt = connection.prepareStatement("UPDATE articulo SET nombre = ? WHERE id = ?");
		
		pstmt.setString(1, categoria.getNombre());
		pstmt.setLong(2, categoria.getId());
		
		pstmt.executeUpdate();
	
		connection.close();
	}

}
