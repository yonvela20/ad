package serpis.ad;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PedidoLinea {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private long pedido;
	private BigDecimal precio;
	private long articulo;
	private BigDecimal unidades;
	private BigDecimal importe;
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}
	
	public long getPedido() {
		return pedido;
	}
	
	public void setPedido(long pedido) {
		this.pedido = pedido;
	}
	
	public BigDecimal getPrecio() {
		return precio;
	}
	
	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}
	
	public long getCategoria() {
		return articulo;
	}
	
	public void setCategoria(long articulo) {
		this.articulo = articulo;
	}
	
	public BigDecimal getUnidades() {
		return unidades;
	}
	
	public void setUnidades(BigDecimal unidades) {
		this.unidades = unidades;
	}
	
	public BigDecimal getImporte() {
		return importe;
	}
	
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	
	@Override
	public String toString() {
		return String.format("[%s] %s %s %s€ %s %s€", id, pedido, articulo, precio, unidades,  importe);
	}
}
