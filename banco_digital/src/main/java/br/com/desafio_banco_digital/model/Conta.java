package br.com.desafio_banco_digital.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public abstract class Conta implements  IConta{
    private static int SEQUENCIAL = 1;
    private static final int AGENCIAPADRAO = 1;
    protected int agencia;
    protected int numero;
    protected double saldo;
    protected boolean isAtiva;
    protected   Cliente cliente;

    public Conta(Cliente cliente) {
        this.agencia = Conta.AGENCIAPADRAO;
        this.numero = SEQUENCIAL++;
        isAtiva = true;
        this.cliente = cliente;
    }



    @Override
    public void depositar(Double valor) {
        this.saldo += valor;
    }

    @Override
    public void sacar(Double valor) {
        this.saldo -= valor;
    }

    @Override
    public void transferir(Double valor, Conta contaDestino) {
        if(this.saldo >= valor){
            this.sacar(valor);
            contaDestino.depositar(valor);
            System.out.println("Transferencia feita no valor de: " + valor +
                    " para a conta: "+ contaDestino + " pertencente a :" + contaDestino.cliente.getNome() );
        }
    }

    @Override
    public void exibirInformacoes() {
        System.out.println("Agencia: " + this.getAgencia());
        System.out.println("Conta: " + this.getNumero());
        System.out.println("Saldo: " + this.getSaldo());

    }

    @Override
    public void deastivarConta(Conta conta) {
        if (conta.isAtiva()){
            conta.isAtiva = false;
            System.out.println("Conta desativada");
        }
    }


}
