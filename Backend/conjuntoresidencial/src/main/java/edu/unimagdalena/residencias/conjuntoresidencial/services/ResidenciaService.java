package edu.unimagdalena.residencias.conjuntoresidencial.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unimagdalena.residencias.conjuntoresidencial.Repositories.ResidenciaRepository;
import edu.unimagdalena.residencias.conjuntoresidencial.entities.CasaDelCondominio;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ResidenciaService {
    private final ResidenciaRepository residenciaRepository;

    @Autowired
    public ResidenciaService(ResidenciaRepository residenciaRepository) {
        this.residenciaRepository = residenciaRepository;
    }
    public List<CasaDelCondominio> findAll(){
        return residenciaRepository.findAll();
    }
    public Optional<CasaDelCondominio> findByid(Long id){
        return residenciaRepository.findById(id);
    }
    public CasaDelCondominio create(CasaDelCondominio casaDelCondominio){
        CasaDelCondominio copiaResidencia = CasaDelCondominio.builder()
                                            .nombre(casaDelCondominio.getNombre())
                                            .numeroCasa(casaDelCondominio.getNumeroCasa())
                                            .metrosCuadrados(casaDelCondominio.getMetrosCuadrados())
                                            .build();
        return residenciaRepository.save(copiaResidencia);
    }
    public Optional<CasaDelCondominio> update(Long id, CasaDelCondominio nuevaCasa){
        return residenciaRepository.findById(id).map(casaDelCondominio -> {
            CasaDelCondominio casaActualizada = casaDelCondominio.actualizarCon(nuevaCasa);
            return residenciaRepository.save(casaActualizada);
        });
    }
    public void delete(Long id){
        log.info("Borrar por id: "+id);
        residenciaRepository.deleteById(id);
    }



}
