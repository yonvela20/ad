package serpis.ad;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pedido {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private long cliente;
	private Date fecha;
	private BigDecimal importe;
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getCliente() {
		return cliente;
	}
	
	public void setCliente(long cliente) {
		this.cliente = cliente;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public BigDecimal getImporte() {
		return importe;
	}
	
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	
	@Override
	public String toString() {
		return String.format("[%s] %s %s %s€", id, cliente, fecha, importe);
	}
}