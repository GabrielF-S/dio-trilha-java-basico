package br.com.desafio_banco_digital;

import br.com.desafio_banco_digital.model.*;

import java.util.Optional;
import java.util.Scanner;

public class Main {

    static Banco banco = new Banco();
    public static void main(String[] args) {

        menuInicial();

    }

    private static void menuInicial() {
        Scanner input = new Scanner(System.in);

        int opcao;

        do {
            System.out.println("Olá");
            System.out.println("Bem vindo ao banco " + banco.getNome());
            System.out.println("Selecione a opção");
            System.out.println("1 - Cadastrar usuario\n2 - Consultar seu Cadastro\n7 - sair");

            opcao = input.nextInt();

            switch (opcao){
                case(1) -> criarUsuario();
                case(2) -> consultarUsuario();
            }
        }while (opcao != 7);
        
    }

    private static void consultarUsuario() {
        String cpf = getString("Informe seu cpf: ");
        Cliente cliente = banco.consultarCliente(cpf).get();
        if (cliente!= null){
            System.out.println(cliente);
            menuCliente(cliente);
        }
    }

    private static void menuCliente(Cliente cliente) {
        Scanner input = new Scanner(System.in);

        int opcao;

        do {
            System.out.println("Olá "+ cliente.getNome());
            System.out.println("Bem vindo ao banco " + banco.getNome());
            System.out.println("Selecione a opção");
            System.out.println("1 - Criar Conta Corrente\n2 - Criar Conta Poupança\n3 - Listar Contas\n" +
                    "4 - Selecinar conta\n7 - sair");

            opcao = input.nextInt();

            switch (opcao){
                case(1) -> cliente.criarConta(new ContaCorrente(cliente));
                case(2) -> cliente.criarConta(new ContaPoupanca(cliente));
                case(3) -> cliente.listarContas();
                case(4) -> selecionarConta(cliente);
            }
        }while (opcao != 7);
        
    }

    private static void selecionarConta(Cliente cliente) {
        Scanner input = new Scanner(System.in);
        int numConta = 0;
        while (numConta==0){
            System.out.println("Informe o numero da conta: ");
            numConta = input.nextInt();
        }
       Conta conta =  cliente.selecionarConta(numConta);
        if (conta.isAtiva()){
            menuConta(conta);
        }
    }

    private static void menuConta(Conta conta) {
        Scanner input = new Scanner(System.in);
        System.out.println("Conta: " + conta.getNumero());
        int opcao;

        do {
            System.out.println("Selecione uma opção: ");
            System.out.println("1 - Saque\n2 - Deposito\n3 - Consultar Saldo\n4 - Transferencia\n5 - Desativar Conta");

            opcao = input.nextInt();

            switch (opcao){
                case(1) -> {
                    System.out.println("Informe o valor: ");
                    Double valor = input.nextDouble();
                    conta.sacar(valor);
                }
                case(2) -> {
                    System.out.println("Informe o valor: ");
                    Double valor = input.nextDouble();
                    conta.depositar(valor);
                }
                case(3) -> System.out.println(conta.getSaldo());
                case(4) -> {
                    System.out.println("Informe o valor: ");
                    Double valor = input.nextDouble();
                    System.out.println("Informe o numero da Conta Destino: ");
                    int numContaDestino = input.nextInt();
                    Conta ContaDestino = banco.consultarConta(numContaDestino);
                    conta.transferir(valor, ContaDestino );
                }
                case(5) -> conta.deastivarConta(conta);
            }
        }while (opcao != 7);
        
    }

    private static void criarUsuario() {
        String nome = getString("Informe seu nome: ");
        String cpf = getString("Informe seu cpf: ");
        banco.adicionarCliente(new Cliente(nome, cpf));
    }

    private static String getString(String x) {
        System.out.println(x);
        Scanner input = new Scanner(System.in);
        String texto = input.nextLine();
        return texto;
    }

}