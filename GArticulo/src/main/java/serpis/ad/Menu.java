package serpis.ad;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Menu {
	public static void Menu() throws SQLException {
		System.out.println("Bienvenido al gestor de Artículos \n"
				+ "Selecciona la opción a realizar\n"
				+ "0 Salir \n"
				+ "1 Nuevo \n"
				+ "2 Editar \n"
				+ "3 Eliminar \n"
				+ "4 Consultar \n"
				+ "5 Listar");
		
		Scanner scn = new Scanner(System.in);
		int eleccion = scn.nextInt();
	        switch (eleccion) {
	            case 0: //Salir
	            	System.out.println("Saliendo del programa...");
	            	System.exit(0);
	                     break;
	            case 1: //Nuevo
	            	ArticuloDao.Nuevo();
	            	;
	                     break;
	            case 2: //Editar
	            	ArticuloDao.Editar();
	            	;
	                     break;
	            case 3: //Eliminar
	            	ArticuloDao.Eliminar();
	                     break;
	            case 4: //Consultar
	            	ArticuloDao.Consultar();
	                     break;
	            case 5: //Listar
	            	ArticuloDao.Listar();
	                     break;
	            default: 
	            	System.out.println("Error");
	                     break;
	        }
	    }
	
}