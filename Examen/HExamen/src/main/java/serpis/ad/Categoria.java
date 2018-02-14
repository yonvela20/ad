package serpis.ad;

import javax.persistence.*;

@Entity
public class Categoria {
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private long id;
	private String nombre;
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
		
	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return String.format("[%s] %s", id, nombre);
	}
}
