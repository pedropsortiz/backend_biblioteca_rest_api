package br.ufsm.poow2.biblioteca_rest.Controller;

import br.ufsm.poow2.biblioteca_rest.Dao.ClienteDao;
import br.ufsm.poow2.biblioteca_rest.Model.Cliente;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @GetMapping("/clientes")
    public ArrayList<Cliente> getClientes(){
        return new ClienteDao().getClientes();
    }

    @GetMapping
    public Cliente getCliente(){
        return new ClienteDao().getCliente();
    }

}
