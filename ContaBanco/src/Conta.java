public class Conta {
    private int numero;
    private String agencia;
    private String nomeCliente;
    private double saldo;

    

    public Conta() {
        this.numero = 1234;
        this.agencia = "067-8";
        this.nomeCliente = "GABRIEL FERREIRA";
        this.saldo = 25.0;
    }
    

    public Conta(int numero, String agencia, String nomeCliente) {
        this.numero = numero;
        this.agencia = agencia;
        this.nomeCliente = nomeCliente;
        this.saldo = 25.0;
    }


    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
    public String getAgencia() {
        return agencia;
    }
    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }
    public String getNomeCliente() {
        return nomeCliente;
    }
    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
    public double getSaldo() {
        return saldo;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    
}
