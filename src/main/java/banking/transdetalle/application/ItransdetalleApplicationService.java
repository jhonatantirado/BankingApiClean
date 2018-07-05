package banking.transdetalle.application;

import java.util.List;

import banking.transdetalle.domain.entity.transDetalle;

public interface ItransdetalleApplicationService {
	public List<transDetalle> getCustomertransDetalle(Long customerid);
}
