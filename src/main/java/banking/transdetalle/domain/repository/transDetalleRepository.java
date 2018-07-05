package banking.transdetalle.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import banking.transdetalle.domain.entity.transDetalle;


public interface transDetalleRepository extends JpaRepository<transDetalle, Long> {

}


