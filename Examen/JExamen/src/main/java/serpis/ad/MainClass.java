package serpis.ad;

//TODO completar
public class MainClass {

	public static void main(String[] args) {
		
		Categoria categoria = load(1L); 
		categoria.setNombre(categoria.getNombre() + "#j");
		update(categoria);
		
	}
	
	private static Categoria load(Object id) {
		return null;
	}
	
	private static void update(Categoria categoria) {
	}

}
