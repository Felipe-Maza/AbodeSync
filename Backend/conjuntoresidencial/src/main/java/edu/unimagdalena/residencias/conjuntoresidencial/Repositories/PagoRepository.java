package edu.unimagdalena.residencias.conjuntoresidencial.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.unimagdalena.residencias.conjuntoresidencial.entities.Pago;
@Repository
public interface PagoRepository extends JpaRepository<Pago,Long>{
    public List<Pago> findByreferencia(String referencia);

    
}
