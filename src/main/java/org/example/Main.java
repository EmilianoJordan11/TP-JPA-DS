package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example-unit");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            Categoria categoria1 = Categoria.builder().denominacion("Bebidas").build();
            Categoria categoria2 = Categoria.builder().denominacion("Alimentos").build();
            Categoria categoria3 = Categoria.builder().denominacion("Hogar").build();
            Categoria categoria4 = Categoria.builder().denominacion("Electrodomésticos").build();

            Articulo articulo1 = Articulo.builder().denominacion("Coca Cola").cantidad(300).precio(2500).build();
            Articulo articulo2 = Articulo.builder().denominacion("Papas Lays").cantidad(280).precio(3000).build();
            Articulo articulo3 = Articulo.builder().denominacion("Sillón").cantidad(20).precio(55000).build();
            Articulo articulo4 = Articulo.builder().denominacion("Heladera").cantidad(25).precio(800000).build();
            Articulo articulo5 = Articulo.builder().denominacion("Arroz").cantidad(300).precio(1200).build();

            articulo1.getCategorias().add(categoria1);
            articulo2.getCategorias().add(categoria2);
            articulo3.getCategorias().add(categoria3);
            articulo4.getCategorias().add(categoria3);
            articulo4.getCategorias().add(categoria4);
            articulo5.getCategorias().add(categoria2);

            categoria1.getArticulos().add(articulo1);
            categoria2.getArticulos().add(articulo2);
            categoria2.getArticulos().add(articulo5);
            categoria3.getArticulos().add(articulo3);
            categoria3.getArticulos().add(articulo4);
            categoria4.getArticulos().add(articulo4);

            DetalleFactura detalle1 = DetalleFactura.builder().cantidad(3).subtotal(7500).articulo(articulo1).build();
            DetalleFactura detalle2 = DetalleFactura.builder().cantidad(2).subtotal(6000).articulo(articulo2).build();
            DetalleFactura detalle3 = DetalleFactura.builder().cantidad(2).subtotal(110000).articulo(articulo3).build();
            DetalleFactura detalle4 = DetalleFactura.builder().cantidad(1).subtotal(800000).articulo(articulo4).build();

            Domicilio dom1 = Domicilio.builder().nombreCalle("Gorriti").numero(1415).build();

            Cliente cliente1 = Cliente.builder().nombre("Emiliano").apellido("Jordan").dni(45255537).domicilio(dom1).build();

            Factura factura1 = Factura.builder().numero(557).fecha("06/09/2024").cliente(cliente1).build();

            factura1.getDetallesFactura().add(detalle1);
            factura1.getDetallesFactura().add(detalle2);
            factura1.getDetallesFactura().add(detalle3);
            factura1.getDetallesFactura().add(detalle4);

            factura1.setTotal(detalle1.getSubtotal()+detalle2.getSubtotal()+detalle3.getSubtotal()+detalle4.getSubtotal());

            entityManager.persist(factura1);

            entityManager.flush();

            entityManager.getTransaction().commit();
        }
        catch(Exception e){
            entityManager.getTransaction().rollback();
            System.out.println("Error: "+ e.getMessage());
            System.out.println("Hubo un error guardando las clases");}
            entityManager.close();
            entityManagerFactory.close();

        }

    }



