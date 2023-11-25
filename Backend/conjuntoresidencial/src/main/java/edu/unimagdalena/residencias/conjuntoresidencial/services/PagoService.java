package edu.unimagdalena.residencias.conjuntoresidencial.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unimagdalena.residencias.conjuntoresidencial.Repositories.PagoRepository;
import edu.unimagdalena.residencias.conjuntoresidencial.entities.Pago;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PagoService {
    private final PagoRepository pagoRepository;

    @Autowired
    public PagoService(PagoRepository pagoRepository){
        this.pagoRepository=pagoRepository;
    }
    public List<Pago> findAll(){
        return pagoRepository.findAll();
    }
    public Optional<Pago> findByid(Long id){
        return pagoRepository.findById(id);
    }

    public Pago create (Pago pago){
        Pago copiaPago = Pago.builder()
                        .fechaPago(pago.getFechaPago())
                        .valorPago(pago.getValorPago())
                        .referencia(pago.getReferencia())
                        .build();
        return pagoRepository.save(copiaPago);
    }
    public Optional<Pago> update(Long id, Pago nuevopago){
        return pagoRepository.findById(id).map(pago -> {
            Pago pagoActualizado = pago.actualizarCon(nuevopago);
            return pagoRepository.save(pagoActualizado);
        });
    }
    public void delete(Long id){
        log.info("Borrar por id: "+id);
        pagoRepository.deleteById(id);
    }
    

}
