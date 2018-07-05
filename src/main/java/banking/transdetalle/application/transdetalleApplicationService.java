package banking.transdetalle.application;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import banking.common.application.Notification;
import banking.common.application.enumeration.RequestBodyType;
import banking.transdetalle.application.dao.transDetalleDAO;
import banking.transdetalle.application.dto.transDetalleDto;
import banking.transdetalle.domain.entity.transDetalle;
import banking.transdetalle.domain.repository.transDetalleRepository;

@Service()
public class transdetalleApplicationService implements ItransdetalleApplicationService {

	@Autowired
	private transDetalleRepository ttransdetalleRepository;
	
	@Autowired
	transDetalleDAO ttransDetalleDAO;
	
	@Transactional
	public ResponseEntity<Object> performInsertTransDetalle(transDetalleDto ttransDetalleDto) throws Exception {		
		Notification notification = this.validation(ttransDetalleDto);
        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }
        transDetalle ttransDetalle = new transDetalle(); 
        ttransDetalle.setNumb_origen(ttransDetalleDto.getNumb_origen());
	    ttransDetalle.setNumb_destino(ttransDetalleDto.getNumb_destino());
	    ttransDetalle.setBalance(ttransDetalleDto.getBalance());
	    ttransDetalle.setIslocked(ttransDetalleDto.getIslocked());
	    ttransDetalle.setBank_account_id(ttransDetalleDto.getBank_account_id());
	    ttransDetalle.setCustomer_id(ttransDetalleDto.getBank_account_id());
	    	
		return null;				
	}	
	

	@Transactional
	public void saves(transDetalle ttransDetalle) {
		  ttransDetalleDAO.savetransDetalle(ttransDetalle);	
	}
	
	
	private Notification validation(transDetalleDto ttransDetalleDto) {
		Notification notification = new Notification();
		if (ttransDetalleDto == null || ttransDetalleDto.getRequestBodyType() == RequestBodyType.INVALID) {
			notification.addError("Invalid JSON data in request body.");
		}
		return notification;
	}

	@Transactional
	public List<transDetalle> getCustomertransDetalle(Long customerid) {		
		return ttransDetalleDAO.getCustomertransDetalle(customerid);
	}
	
}
