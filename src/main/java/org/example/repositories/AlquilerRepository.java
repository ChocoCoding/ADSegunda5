package org.example.repositories;

import jakarta.persistence.Query;
import org.example.models.Alquiler;
import org.example.models.Cliente;
import org.example.models.Libro;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.List;

public class AlquilerRepository {
    Session session;

    public AlquilerRepository(Session session){
        this.session = session;
    }

    public boolean esAlquilado(int idLibro){
        Query query = session.createQuery("SELECT a FROM Alquiler a WHERE a.libro.idLibro = :idLibro").setParameter("idLibro",idLibro);

        try{
            List<Alquiler> alquileres = query.getResultList();
            for (Alquiler a : alquileres){
                if (a.isAlquilado()){
                    return true;
                }
            }
            return false;
        }catch (Exception exception){
            return false;
        }
    }

    public void alquilar(int idLibro,int idCliente){
        Transaction trx = session.beginTransaction();
        if (!esAlquilado(idLibro)){
            Cliente cliente = (Cliente) session.createQuery("SELECT c FROM Cliente c WHERE c.idCliente = :idCliente").setParameter("idCliente",idCliente).getSingleResult();
            Libro libro = (Libro) session.createQuery("SELECT l FROM Libro l WHERE l.idLibro = :idLibro").setParameter("idLibro",idLibro).getSingleResult();
            Alquiler alquiler = new Alquiler(new Date(),true);

            alquiler.setLibro(libro);
            alquiler.setCliente(cliente);
            alquiler.setAlquilado(true);

            libro.addAlquiler(alquiler);
            cliente.addAlquiler(alquiler);

            session.save(alquiler);
            System.out.println("El libro se ha alquilado");
        }else System.out.println("Ya esta alquilado");
        trx.commit();
    }

    public void devolverLibro(int idLibro){
        Transaction trx = session.beginTransaction();
        if (esAlquilado(idLibro)){
            Libro libro = (Libro) session.createQuery("SELECT l FROM Libro l WHERE l.idLibro = : idLibro").setParameter("idLibro",idLibro).getSingleResult();

            Alquiler alquiler = (Alquiler) session.createQuery("SELECT a FROM Alquiler a WHERE a.libro.idLibro = :idLibro AND a.alquilado = true" ).setParameter("idLibro",idLibro).getSingleResult();

            alquiler.setAlquilado(false);


            this.session.update(alquiler);
            System.out.println("Se ha devuelto el libro");

        }
        trx.commit();



    }

}
