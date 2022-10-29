package br.ufsm.poow2.biblioteca_rest.Dao;

import br.ufsm.poow2.biblioteca_rest.Model.Cliente;

import java.util.ArrayList;

public class ClienteDao {

    public ArrayList<Cliente> getClientes(){
        ArrayList<Cliente> clientes = new ArrayList<>();

        clientes.add(new Cliente(1, "Pedro"));
        clientes.add(new Cliente(2, "Carlos"));
        clientes.add(new Cliente(3, "Angela"));
        clientes.add(new Cliente(4, "Teresa"));
        clientes.add(new Cliente(5, "Joaquim"));

        return clientes;
    }

    public Cliente getCliente(){
        return new Cliente(6, "Bruno");
    }

}
