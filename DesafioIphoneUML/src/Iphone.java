import java.util.ArrayList;
import java.util.List;

public class Iphone implements AparelhoTelefonico, NavegadorInternet, ReprodutorMusica {
    
    private boolean estaReproduzindo;
    private String musicaAtual = "";
    private String siteAtual = "";
    private List sitesAbertos = new ArrayList<>();

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
            System.out.println("Musica" + musicaAtual + " pausada");
            estaReproduzindo = false;
        } else {
            System.out.println("Não há músicas sendo reproduzida");
        }
        
    }

    @Override
    public void selecionarMusica(String musica) {
        
        System.out.println("Musica: " + musica + " selecionada");
        musicaAtual = musica;
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
        siteAtual = url;
        System.out.println("Acessando pagina: "+ url);
       
    }

    @Override
    public void adicionarNovaAba() {
        if (siteAtual.isBlank()) {
            System.out.println("Abra uma pagina");
        }else{
            sitesAbertos.add(siteAtual);
            siteAtual = "";
            System.out.println("Abrindo nova guia");
        }
        
      
    }

    @Override
    public void atualizarPagina() {
        if (siteAtual.isBlank()) {
            System.out.println("Abra uma pagina");
        } else {
            System.out.println("Pagina "+ siteAtual+ " atualizada");
        }
        
        
    }
    
}
