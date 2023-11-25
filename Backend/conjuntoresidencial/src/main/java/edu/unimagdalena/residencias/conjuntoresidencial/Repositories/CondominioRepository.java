package edu.unimagdalena.residencias.conjuntoresidencial.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.unimagdalena.residencias.conjuntoresidencial.entities.ConjuntoResidencial;

@Repository
public interface CondominioRepository extends JpaRepository<ConjuntoResidencial,Long>{
    public List<ConjuntoResidencial> findByNombre(String nombre);
    
}
