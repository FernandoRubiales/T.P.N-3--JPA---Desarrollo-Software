package org.example;

import org.example.entidades.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example-unit");

        EntityManager entityManager = entityManagerFactory.createEntityManager(); //lo instanciamos
        System.out.println("en marcha Alberto");

        try {
            // Persistir una nueva entidad Person
            entityManager.getTransaction().begin(); //iniciamos una transacción

            Factura factura1 = Factura.builder().fecha("10/08/2020").numero(12).total(120).build();
            Domicilio dom = Domicilio.builder().nombreCalle("San Martin").numero(1222).build();
            Cliente cliente = Cliente.builder().nombre("Pablo").apellido("Muñoz").dni(15245778).build();

            cliente.setDomicilio(dom);
            dom.setCliente(cliente);

            factura1.setCliente(cliente);

            Categoria perecederos = Categoria.builder().denominacion("Perecederos").build();
            Categoria lacteos = Categoria.builder().denominacion("Lacteos").build();
            Categoria limpieza = Categoria.builder().denominacion("Limpieza").build();

            Articulo art1 = Articulo.builder().cantidad(200).denominacion("Yogurt Ser sabor frutilla").precio(20).build();
            Articulo art2 = Articulo.builder().cantidad(300).denominacion("Detergente Magistral").precio(80).build();

            art1.getCategorias().add(perecederos);
            art1.getCategorias().add(lacteos);
            lacteos.getArticulos().add(art1);
            perecederos.getArticulos().add(art1);

            art2.getCategorias().add(limpieza);
            limpieza.getArticulos().add(art2);

            DetalleFactura det1 = DetalleFactura.builder().cantidad(2).subtotal(40).build();
            det1.setArticulo(art1);

            art1.getDetalle().add(det1);
            factura1.getDetalles().add(det1);
            det1.setFactura(factura1);

            DetalleFactura det2 = DetalleFactura.builder().cantidad(1).subtotal(80).build();
            det2.setArticulo(art2);

            art2.getDetalle().add(det2);
            factura1.getDetalles().add(det2);
            det2.setFactura(factura1);

            entityManager.persist(factura1);

            entityManager.flush();
            entityManager.getTransaction().commit();

            // Consultar y mostrar la entidad persistida

        }catch (Exception e){

            entityManager.getTransaction().rollback(); //para que se vuelva al estado anterior
            System.out.println(e.getMessage());
            System.out.println("No se pudo grabar la clase Factura");
        }

        // Cerrar el EntityManager y el EntityManagerFactory
        entityManager.close();
        entityManagerFactory.close();
    }
}
