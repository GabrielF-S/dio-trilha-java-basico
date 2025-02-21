import java.util.Scanner;

public class ContaTerminal {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Olá, Bem Vindo!");
        Thread.sleep(1500);

        System.out.println("Por favor, digite o número da Agência!");
        String agencia = scan.nextLine();
        Thread.sleep(500);

        System.out.println("Por favor, digite o Número da Conta!");
        int numConta = scan.nextInt();
        scan.nextLine();
        Thread.sleep(500);
        
        System.out.println("Por favor, digite o número o seu Nome!");
        String nome = scan.nextLine();
        Thread.sleep(500);

        Conta conta = new Conta(numConta, agencia, nome);
        Thread.sleep(1500);

        System.out.println("Olá "+conta.getNomeCliente()+", obrigado por criar uma conta em nosso banco, sua agência é "+ conta.getAgencia()+ ", conta "+conta.getNumero()+" e seu saldo "+conta.getSaldo()+" já está disponível para saque");
    }
}
