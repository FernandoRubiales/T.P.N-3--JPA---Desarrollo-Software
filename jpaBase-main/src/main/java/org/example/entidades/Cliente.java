package org.example.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
@Setter
@Entity //indica que esta clase debe guardarse en la base de datos como tal
@Table(name = "cliente") //indica que va a ser una tabla en la base de datos

public class Cliente implements Serializable {     //convertirlo en un flujo de bytes para almacenarlo
    @Id //indica que va a ser la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) //indica que se va autoincrementar
    private Long id;
    private String nombre;
    private String apellido;
    @Column(name = "dni", unique = true)
    private  int dni;

    @OneToOne(cascade = CascadeType.ALL) //cualquier cambio que yo realice en nuestro cliente se ve reflejado en el domicilio del mismo
    @JoinColumn(name = "fk_domicilio") //se crea la columna de la clave foranea
    private Domicilio domicilio;

    @OneToMany(mappedBy = "cliente")
    private Set<Factura> facturas = new HashSet<>(); //Bidireccionalidad
}
