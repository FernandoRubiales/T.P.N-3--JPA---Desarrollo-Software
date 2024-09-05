package org.example.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
@Setter
@Entity
@Table(name = "domicilio")
public class Domicilio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_calle")
    private String nombreCalle;

    private int numero;

    @OneToOne(mappedBy = "domicilio") //Para hacerlo bidireccional
    private Cliente cliente;

}
