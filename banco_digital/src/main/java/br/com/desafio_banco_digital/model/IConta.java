package br.com.desafio_banco_digital.model;

public interface IConta {
    void sacar(Double valor);

    void depositar(Double valor);

    void transferir(Double valor, Conta contaDestino);

    void exibirInformacoes();

    void deastivarConta(Conta conta);
}
