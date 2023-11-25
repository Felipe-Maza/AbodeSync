
package edu.unimagdalena.residencias.conjuntoresidencial.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;


import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import edu.unimagdalena.residencias.conjuntoresidencial.entities.CasaDelCondominio;
import edu.unimagdalena.residencias.conjuntoresidencial.entities.ConjuntoResidencial;
import edu.unimagdalena.residencias.conjuntoresidencial.entities.Pago;
import edu.unimagdalena.residencias.conjuntoresidencial.exepcion.ResourceNotFound;
import edu.unimagdalena.residencias.conjuntoresidencial.services.CondominioService;
import edu.unimagdalena.residencias.conjuntoresidencial.services.PagoService;
import edu.unimagdalena.residencias.conjuntoresidencial.services.ResidenciaService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class CondominioController {
    private final CondominioService condominioService;
    private final ResidenciaService residenciaService;
    private final PagoService pagoService;

   

    public CondominioController(CondominioService condominioService, ResidenciaService residenciaService, PagoService pagoService ) {
        this.condominioService = condominioService;
        this.residenciaService = residenciaService;
        this.pagoService = pagoService;

    }


    @GetMapping("/conjuntos/{idConjunto}/casas/{idCasa}")
    public ResponseEntity<Optional<CasaDelCondominio>> detalleCasa (@PathVariable("idConjunto") Long idConjunto, @PathVariable
    ("idCasa") Long idCasa){
        /*ConjuntoResidencial condominio= condominioService.findByid(idConjunto).orElseThrow(() -> new ResourceNotFound("No se encontro el condominio"));
        ResponseEntity.ok().body(condominio);

        CasaDelCondominio caassssss = residenciaService.findByid(idCasa).orElseThrow(() -> new ResourceNotFound("No se encontro la casa"));
        return ResponseEntity.ok().body(caassssss);*/
        try{
            ConjuntoResidencial condominio= condominioService.findByid(idConjunto).orElseThrow();
            ResponseEntity.ok().body(condominio);

            Optional<CasaDelCondominio> cass= residenciaService.findByid(idCasa);
            return ResponseEntity.ok().body(cass);
        }catch(Exception e){
            log.error("Error al listar", e);
            throw new ResourceNotFound("El condominio no se encuentra registrado"); 
        }

        
    }
    @GetMapping("/conjuntos")
    public ResponseEntity<List<ConjuntoResidencial>> listartodo(){
        List<ConjuntoResidencial> conjuntoResidencials = condominioService.findAll();
        return ResponseEntity.ok().body(conjuntoResidencials);
    }
    @GetMapping("/conjuntos/pagos")
    public ResponseEntity<List<Pago>> listartodog(){
        List<Pago> pagos=pagoService.findAll();
        return ResponseEntity.ok().body(pagos);
    }
    @GetMapping("/conjuntos/{idconjunto}/casas")
    public ResponseEntity<List<CasaDelCondominio>> listar(@PathVariable("idconjunto") Long idConjunto){
        try{
            ConjuntoResidencial condominio= condominioService.findByid(idConjunto).orElseThrow();
            ResponseEntity.ok().body(condominio);

            List<CasaDelCondominio> casaDelCondominios = residenciaService.findAll();
            return ResponseEntity.ok().body(casaDelCondominios);
        }catch (Exception e) {
            log.error("error al listar", e);
            throw new ResourceNotFound("El condominio no se encuentra registrado"); 
        }
    }
    @PostMapping("/conjuntos")
    public ResponseEntity<ConjuntoResidencial> create (@Validated @RequestBody ConjuntoResidencial conjuntoResidencial){
        ConjuntoResidencial condominioCreado = condominioService.create(conjuntoResidencial);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{idConjunto}")
                    .buildAndExpand(condominioCreado.getId())
                    .toUri();
        return ResponseEntity.created(location).body(condominioCreado);
    }
    @PostMapping("/conjuntos/pagos")
    public ResponseEntity<Pago> create(@Validated @RequestBody Pago pago){
        Pago pagocreado = pagoService.create(pago);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("({idPago})")
                        .buildAndExpand(pagocreado.getId())
                        .toUri();
        return ResponseEntity.created(location).body(pagocreado);
    }

    @PostMapping("/conjuntos/{idConjunto}/casas/{idCasa}/pago")
    public ResponseEntity<Pago> create (@Validated @RequestBody  @PathVariable("idConjunto") Long idConjunto, 
    @PathVariable("idCasa") Long idCasa, Pago pago){
        ConjuntoResidencial conjuntoResidencial= condominioService.findByid(idConjunto).orElseThrow(() -> new ResourceNotFound("No se encontro el condominio"));
        ResponseEntity.ok().body(conjuntoResidencial);

        CasaDelCondominio casaDelCondominio=residenciaService.findByid(idCasa).orElseThrow(() -> new ResourceNotFound("No se encontro la casa"));
        ResponseEntity.ok().body(casaDelCondominio);

        Pago pagocreado = pagoService.create(pago);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{idpago}")
                        .buildAndExpand(pagocreado.getId())
                        .toUri();
        return ResponseEntity.created(location).body(pagocreado);

    }

    @PostMapping("/conjuntos/{idconjunto}/casas")
    public ResponseEntity<CasaDelCondominio> create (@Validated @RequestBody @PathVariable("idconjunto") Long idConjunto, 
    CasaDelCondominio casaDelCondominio){
        ConjuntoResidencial conjuntoResidencial = condominioService.findByid(idConjunto).orElseThrow();
        ResponseEntity.ok().body(conjuntoResidencial);

        CasaDelCondominio casaCreada = residenciaService.create(casaDelCondominio);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                       .path("/{idCasa}")
                       .buildAndExpand(casaCreada.getId())
                       .toUri();
        return ResponseEntity.created(location).body(casaCreada); 

    }

    @PutMapping("/conjuntos/{idconjunto}/casas/{idcasa}")
    public ResponseEntity<CasaDelCondominio> update(@Validated @RequestBody @PathVariable("idconjunto") Long idConjunto,
    @PathVariable("idcasa") Long idCasa,  CasaDelCondominio updatecasa){

        ConjuntoResidencial conjuntoResidencial = condominioService.findByid(idConjunto).orElseThrow();
        ResponseEntity.ok().body(conjuntoResidencial);

        Optional<CasaDelCondominio> update = residenciaService.update(idCasa, updatecasa);
        return update.map(casaDelCondominio -> ResponseEntity.ok().body(casaDelCondominio))
        .orElseGet(() -> {
            CasaDelCondominio nuevaCasa = residenciaService.create(updatecasa);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{idcasa}")
            .buildAndExpand(nuevaCasa.getId())
            .toUri();
            return ResponseEntity.created(location).body(nuevaCasa);
        });

    }
    //@PutMapping("")

    @PutMapping("/conjuntos/{idConjunto}/casas/{idCasa}/pago/{idPago}")
    public ResponseEntity<Pago> update(@Validated @RequestBody @PathVariable("idConjunto") Long idConjunto, 
    @PathVariable("idCasa") Long idCasa,@PathVariable("idPago") Long idPago, Pago updatedpago){
        ConjuntoResidencial conjuntoResidencial = condominioService.findByid(idConjunto).orElseThrow();
        ResponseEntity.ok().body(conjuntoResidencial);

        CasaDelCondominio casaDelCondominio = residenciaService.findByid(idCasa).orElseThrow();
        ResponseEntity.ok().body(casaDelCondominio);

        Optional<Pago> update = pagoService.update(idPago,updatedpago);
        return update.map(pago -> ResponseEntity.ok().body(pago))
        .orElseGet(() -> {
            Pago nuevopago = pagoService.create(updatedpago);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{idPago}")
            .buildAndExpand(nuevopago.getId())
            .toUri();
            return ResponseEntity.created(location).body(nuevopago);
        });

    }

    @PutMapping("/conjuntos/{idconjunto}")
    public ResponseEntity<ConjuntoResidencial> update(@PathVariable("idconjunto") Long idConjunto, @RequestBody ConjuntoResidencial updatedCondominio){
        Optional<ConjuntoResidencial> update = condominioService.update(idConjunto, updatedCondominio);
        return update.map(conjuntoResidencial -> ResponseEntity.ok().body(conjuntoResidencial))
        .orElseGet(() -> {
            ConjuntoResidencial nuevoCondominio = condominioService.create(updatedCondominio);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{idconjuntos}")
            .buildAndExpand(nuevoCondominio.getId())
            .toUri();
            return ResponseEntity.created(location).body(nuevoCondominio);
        });

    }
    @PutMapping("/conjuntos/casas/{idCasa}")
    public ResponseEntity<CasaDelCondominio> update(@PathVariable("idCasa") Long idCasa, @RequestBody CasaDelCondominio updatedcasa){
        Optional<CasaDelCondominio> update = residenciaService.update(idCasa, updatedcasa);
        return update.map(casa -> ResponseEntity.ok().body(casa))
        .orElseGet(() -> {
            CasaDelCondominio nuevocasa = residenciaService.create(updatedcasa);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(nuevocasa.getId())
            .toUri();
            return ResponseEntity.created(location).body(nuevocasa);
        });
    }

    @PutMapping("/conjuntos/pago/{idPago}")
    public ResponseEntity<Pago> update(@PathVariable("idPago") Long idPago,@RequestBody Pago updatedPago){
        Optional<Pago> update = pagoService.update(idPago,updatedPago);
        return update.map(pago -> ResponseEntity.ok().body(pago))
        .orElseGet(() -> {
            Pago nuevopago = pagoService.create(updatedPago);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{idPago}")
            .buildAndExpand(nuevopago.getId())
            .toUri();
            return ResponseEntity.created(location).body(nuevopago);
        });
    }

    @DeleteMapping("/conjuntos/{idconjunto}/casas/{idcasa}")
    public ResponseEntity<CasaDelCondominio> delete(@PathVariable("idconjunto") Long idConjunto, 
    @PathVariable("idcasa") Long idCasa){
        try{
            condominioService.findByid(idConjunto);
            residenciaService.delete(idCasa);
        }catch (Exception e) {
            log.error("Error al borrar", e);
            throw new ResourceNotFound("La casa a eliminar no se encuentra registrada");
        }
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/conjuntos/{idconjunto}")
    public ResponseEntity<ConjuntoResidencial> delete(@PathVariable("idconjunto") Long idConjunto){
        try{
            condominioService.delete(idConjunto);
        }catch (Exception e) {
            log.error("Error al borrar", e);
            throw new ResourceNotFound("El condominio a eliminar no se encuentra registrado");

        }
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/conjuntos/{idConjunto}/casas/{idCasa}/pago/{idPago}")
    public ResponseEntity<Pago> deleted(@PathVariable("idConjunto") Long idConjunto, @PathVariable("idCasa") Long idCasa,
    @PathVariable("idPago") Long idPago){
        try{
            condominioService.findByid(idConjunto).orElseThrow(() -> new ResourceNotFound("No se encontro el condominio"));
            residenciaService.findByid(idCasa).orElseThrow(() -> new ResourceNotFound("No se encontro la casa"));
            pagoService.delete(idPago);
        }catch (Exception e){
            log.error("Error al borrar", e);
            throw new ResourceNotFound("El pago a eliminar no se encuentra registrado");
        }
        return ResponseEntity.noContent().build();
    }



    

    


}
