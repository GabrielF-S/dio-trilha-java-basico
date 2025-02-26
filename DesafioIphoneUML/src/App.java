public class App {
    public static void main(String[] args) throws Exception {
        Iphone phone = new Iphone();
        phone.selecionarMusica("Even Flow - Pear Jam");
        phone.toca();
        phone.pausar();

        phone.ligar("11912345678");
        phone.atender();
        phone.iniciarCorreioVoz();

        phone.atualizarPagina();
        phone.adicionarNovaAba();
        phone.exibirPagina("www.dio.me");
        phone.atualizarPagina();
        phone.adicionarNovaAba();

       
    }
}
