package banking.transdetalle.application.dto;

import java.math.BigDecimal;
import java.util.Date;

import banking.common.application.dto.RequestBaseDto;

public class transDetalleDto  extends RequestBaseDto {

	private long id;
	private String numb_origen;
    private String numb_destino;
    private BigDecimal monto;
	private Date fecha;	
	private long customer_id;;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNumb_origen() {
		return numb_origen;
	}
	public void setNumb_origen(String numb_origen) {
		this.numb_origen = numb_origen;
	}
	public String getNumb_destino() {
		return numb_destino;
	}
	public void setNumb_destino(String numb_destino) {
		this.numb_destino = numb_destino;
	}
	
	 public BigDecimal getMonto() {
			return monto;
		}
		public void setMonto(BigDecimal monto) {
			this.monto = monto;
		}
		public Date getFecha() {
			return fecha;
		}
		public void setFecha(Date fecha) {
			this.fecha = fecha;
		}
		
	
	public long getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(long customer_id) {
		this.customer_id = customer_id;
	}
	
	
}
