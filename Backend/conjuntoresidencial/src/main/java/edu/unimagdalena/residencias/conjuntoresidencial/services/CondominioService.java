package edu.unimagdalena.residencias.conjuntoresidencial.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unimagdalena.residencias.conjuntoresidencial.Repositories.CondominioRepository;
import edu.unimagdalena.residencias.conjuntoresidencial.entities.ConjuntoResidencial;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CondominioService {
    private final CondominioRepository condominioRepository;

    @Autowired
    public CondominioService(CondominioRepository condominioRepository){
        this.condominioRepository=condominioRepository;
    }
    public List<ConjuntoResidencial> findAll(){
        return condominioRepository.findAll();
    }
    public Optional<ConjuntoResidencial> findByid(Long id){
        return condominioRepository.findById(id);
    }
    public ConjuntoResidencial create(ConjuntoResidencial conjuntoResidencial){
        ConjuntoResidencial copiaCondominio = ConjuntoResidencial.builder()
                                            .nombre(conjuntoResidencial.getNombre())
                                            .direccion(conjuntoResidencial.getDireccion())
                                            .build();
        return condominioRepository.save(copiaCondominio);
    }
    public Optional<ConjuntoResidencial> update(Long id, ConjuntoResidencial nuevoCondominio){
        return condominioRepository.findById(id).map(conjuntoResidencial -> {
            ConjuntoResidencial condominioActualizado = conjuntoResidencial.actualizarCon(nuevoCondominio);
            return condominioRepository.save(condominioActualizado);
        });
    }
    public void delete(Long id){
        log.info("Borrar por: "+id);
        condominioRepository.deleteById(id);
    }

}
