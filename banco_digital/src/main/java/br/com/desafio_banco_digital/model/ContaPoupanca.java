package br.com.desafio_banco_digital.model;

public class ContaPoupanca extends Conta{


    public ContaPoupanca(Cliente cliente) {
        super(cliente);
    }

    @Override
    public String toString() {
        return "Conta Poupanca{" +
                "numero=" + numero +
                ", agencia=" + agencia +
                ", saldo=" + saldo +
                '}';
    }
}
