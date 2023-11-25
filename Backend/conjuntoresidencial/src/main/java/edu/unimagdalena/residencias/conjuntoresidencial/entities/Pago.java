package edu.unimagdalena.residencias.conjuntoresidencial.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "pago")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate fechaPago;
    private Double valorPago;
    private String referencia;

    public Pago actualizarCon(Pago pago){
        return new Pago(
            this.id,
            pago.fechaPago,
            pago.valorPago,
            pago.referencia
        );
    }

}
