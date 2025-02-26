public class Iphone implements AparelhoTelefonico, NavegadorInternet, ReprodutorMusica {
    
    private boolean estaReproduzindo;
    private String musicaAtual = "";

    @Override
    public void toca() {
        estaReproduzindo = true;
        if (!musicaAtual.equals("")) {
           System.out.println("Tocando musica: "+ musicaAtual); 
       } else {
            System.out.println("Selecione uma musica");
        }
       
    }

    @Override
    public void pausar() {
        if (estaReproduzindo) {
            System.out.println("Musica pausada");
            estaReproduzindo = false;
        } else {
            System.out.println("Não há músicas sendo reproduzida");
        }
        
    }

    @Override
    public void selecionarMusica(String musica) {
        
        System.out.println("Musica: " + musica + " selecionada");
        musicaAtual = musica;
        toca();
    }

    @Override
    public void ligar(String numero) {
        System.out.println("Ligando para: "+ numero);
    }

    @Override
    public void atender() {
        System.out.println("Ligação atendida");
       
    }

    @Override
    public void iniciarCorreioVoz() {
        System.out.println("Iniciando Correio de voz");
      
    }

    @Override
    public void exibirPagina(String url) {
        System.out.println("Acessando pagina: "+ url);
       
    }

    @Override
    public void adicionarNovaAba() {
        System.out.println("Abrindo nova guia");
      
    }

    @Override
    public void atualizarPagina() {
        System.out.println("Pagina atualizada");
        
    }
    
}
