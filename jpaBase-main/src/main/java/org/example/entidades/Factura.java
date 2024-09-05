package org.example.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
@Setter
@Entity
@Table(name = "factura")
public class Factura implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fecha;
    private int numero;
    private int total;

    @ManyToOne(cascade = CascadeType.PERSIST) //queremos persistir un cliente al generar una factura, pero si borro la factura no quiero que el cliente se elimine o se modifique
    @JoinColumn(name = "fk_cliente")
    private Cliente cliente;

    //@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) //al ser composicion si se elimina una factura, se eliminan los detalles que contiene
    //private Set<DetalleFactura> detalles = new HashSet<DetalleFactura>();

    @OneToMany(mappedBy = "factura",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DetalleFactura> detalles = new HashSet<DetalleFactura>();
}
