package br.com.desafio_banco_digital.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cliente implements ICliente {

    private String nome;
    @EqualsAndHashCode.Include
    private String cpf;
    private List<Conta> contasList = new ArrayList<>();


    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.contasList = new ArrayList<>();
    }

    @Override
    public void criarConta(Conta conta) {
        System.out.println("Contra Criada");
        this.contasList.add(conta);
        System.out.println(conta);
    }

    @Override
    public void listarContas() {
        contasList.stream().filter(conta -> conta.isAtiva()).forEach(System.out::println);
    }

    @Override
    public Conta selecionarConta(int numConta) {
        return  contasList.stream().filter(conta -> conta.getNumero() == numConta).findAny().get();

    }

    @Override
    public String toString() {
        return  "nome='" + nome + '\'' +
                ", cpf='" + cpf;
    }
}

