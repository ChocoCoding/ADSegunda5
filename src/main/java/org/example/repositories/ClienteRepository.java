package org.example.repositories;

import org.example.models.Cliente;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ClienteRepository implements Repositorio<Cliente>{
    Session session;

    public ClienteRepository(Session session){
        this.session = session;
    }

    @Override
    public void create(Cliente cliente) {
        Transaction trx = session.beginTransaction();
        this.session.save(cliente);
        trx.commit();

    }

    @Override
    public void remove(Cliente cliente) {
        Transaction trx = session.beginTransaction();
        this.session.delete(cliente);
        trx.commit();
    }

    @Override
    public void update(Cliente cliente) {
        Transaction trx = session.beginTransaction();
        this.session.update(cliente);
        trx.commit();
    }

    @Override
    public List<Cliente> findAll() {
        return null;
    }

    @Override
    public Cliente findById(int id) {
        Transaction trx = session.beginTransaction();
        Cliente cliente = (Cliente) this.session.createQuery("SELECT c FROM Cliente c WHERE c.idCliente=:id").setParameter("id",id).getSingleResult();
        trx.commit();
        return cliente;
    }
}
