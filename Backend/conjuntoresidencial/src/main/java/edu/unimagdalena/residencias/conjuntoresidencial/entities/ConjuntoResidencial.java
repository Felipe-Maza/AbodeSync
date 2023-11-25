package edu.unimagdalena.residencias.conjuntoresidencial.entities;

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

@Setter
@Getter
@Entity
@Table(name = "ConjuntoResidencial")
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ConjuntoResidencial {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id; 

    private String nombre; 
    private String direccion; 

    public ConjuntoResidencial actualizarCon(ConjuntoResidencial conjuntoResidencial){
        return new ConjuntoResidencial(
            this.id,
            conjuntoResidencial.nombre,
            conjuntoResidencial.direccion
        );
    }

}
