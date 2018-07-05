package banking.transdetalle.application.dao;

import java.util.List;

import banking.transdetalle.domain.entity.transDetalle;


public interface ItransDetalleDAO {
	public List<transDetalle> getCustomertransDetalle(Long customerid);
	public void savetransDetalle(transDetalle p);
}
