package model;

import java.util.Collection;
import java.util.List;

import static model.GameStatus.*;

public class Board {
    public List<List<Posicao>> posicoes;

    public Board(List<List<Posicao>> espaces) {
        this.posicoes = espaces;
    }


    public GameStatus getStatus(){
        if(posicoes.stream().flatMap(Collection::stream).noneMatch(posicao -> !posicao.isFixo() &&  !posicao.isPreenchido())){
            return  NON_STARTED;

        }
        return  posicoes.stream().flatMap(Collection::stream).anyMatch(posicao -> (posicao.isPreenchido()))  ? INCOMPLETE : COMPLETE;
    }

    public boolean hasErorr(){
        if(getStatus() == NON_STARTED){
            return false;
        }
        return posicoes.stream().flatMap(Collection::stream).anyMatch(posicao -> posicao.isPreenchido() && !posicao.isCorreto());
    }

    public  boolean inserirValor(Integer valor, int coluna, int linha){
        var posicao = posicoes.get(coluna).get(linha);
        if (posicao.isFixo()){
            return  false;
        }
        posicao.setValorPreechido(valor);
        return true;
    }

    public boolean limparValor(int coluna, int linha){
        var posicao = posicoes.get(coluna).get(linha);
        if (posicao.isFixo()){
            return  false;
        }
        posicao.limparValor();
        return true;

    }

    public void reset(){
        posicoes.forEach(coluna -> coluna.forEach(Posicao::limparValor) );
    }

    public boolean gameIsFinished(){
        return !hasErorr() && getStatus() == COMPLETE;
    }


    public List<List<Posicao>> getPosicoes() {
        return posicoes;
    }
}

