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

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Casa")

public class CasaDelCondominio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; 

    private String nombre; 
    private Double numeroCasa;  
    private Double metrosCuadrados; 

    public CasaDelCondominio actualizarCon(CasaDelCondominio casaDelCondominio){
        return new CasaDelCondominio(
            this.id,
            casaDelCondominio.nombre,
            casaDelCondominio.numeroCasa,
            casaDelCondominio.metrosCuadrados
        );
    }

}
