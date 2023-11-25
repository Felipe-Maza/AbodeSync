package edu.unimagdalena.residencias.conjuntoresidencial.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.unimagdalena.residencias.conjuntoresidencial.entities.CasaDelCondominio;

@Repository
public interface ResidenciaRepository extends JpaRepository<CasaDelCondominio, Long>{
    public List<CasaDelCondominio> findByNombre(String nombre);

    
}
