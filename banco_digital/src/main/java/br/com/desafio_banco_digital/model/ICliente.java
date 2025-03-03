package br.com.desafio_banco_digital.model;

public interface ICliente {

    void criarConta(Conta conta);

    void listarContas();


    Conta selecionarConta(int numConta);
}
