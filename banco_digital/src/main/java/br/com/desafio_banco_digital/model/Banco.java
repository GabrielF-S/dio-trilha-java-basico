package br.com.desafio_banco_digital.model;

import lombok.Data;

import java.util.HashSet;
import java.util.Optional;

@Data

public class Banco {

    private String nome = "DIO";
    private static HashSet<Cliente> listaClientes;

    public  Banco (){
        listaClientes = new HashSet<Cliente>();
    }
    public Banco(String nome) {
        this.nome = nome;
        listaClientes = new HashSet<Cliente>();
    }



    public  void adicionarCliente(Cliente cliente){
        listaClientes.add(cliente);
    }


    public Optional<Cliente> consultarCliente(String cpf) {
        return listaClientes.stream().filter(cliente -> cliente.getCpf().equalsIgnoreCase(cpf)).findAny();

    }

    public Conta consultarConta(int numContaDestino) {
        return listaClientes.stream()
                .flatMap(cliente -> cliente.getContasList().stream())
                .filter(conta -> conta.getNumero() == numContaDestino)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Conta não localizada"));
    }
}
