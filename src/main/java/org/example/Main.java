package org.example;

import org.example.models.Cliente;
import org.example.repositories.AlquilerRepository;
import org.example.repositories.ClienteRepository;
import org.example.utilities.HibernateUtil;
import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {
        System.out.println("Test");
        Session session = HibernateUtil.get().openSession();

        ClienteRepository clienteRepository = new ClienteRepository(session);
        AlquilerRepository alquilerRepository = new AlquilerRepository(session);

        Cliente cliente = new Cliente("DDDDDDDD8","paco","paco@gmail.com");

        //clienteRepository.create(cliente);

        Cliente clVer = clienteRepository.findById(2);

        System.out.println(clVer.getDni());
        System.out.println(clVer.getNombre());

        clVer.setDni("ZZZZZZZZ9");
        clienteRepository.update(clVer);

        System.out.println(alquilerRepository.esAlquilado(3));

        alquilerRepository.alquilar(1,2);

        alquilerRepository.devolverLibro(1);

        session.close();
        System.out.println("Fin de la conexion");
    }
}