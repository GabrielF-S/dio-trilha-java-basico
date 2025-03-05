package service;

import model.Board;
import model.GameStatus;
import model.Posicao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BoardService {

    private final   int BOARD_LITMIT = 9;

    private  Board board;

    public BoardService(final Map<String,String> gameConfig) {
        this.board = new Board(initBoard(gameConfig));
    }

    public List<List<Posicao>> getSpaces(){
        return this.board.getPosicoes();
    }

    public void reset(){
        this.board.reset();
    }

    public  boolean hasError(){
        return this.board.hasErorr();
    }

    public GameStatus getStatus(){
        return this.board.getStatus();
    }

    public boolean gameIsFinished(){
        return this.board.gameIsFinished();
    }

    private List<List<Posicao>> initBoard(Map<String, String> gameConfig) {
        List<List<Posicao>> spaces = new ArrayList<>();
        for (int i = 0; i< BOARD_LITMIT; i++){
            spaces.add(new ArrayList<>());
            for (int j = 0; j < BOARD_LITMIT; j++){
                var positionConfig = gameConfig.get("%s,%s".formatted(i,j));
                var expected = Integer.parseInt(positionConfig.split(",")[0]);
                var fixed = Boolean.parseBoolean(positionConfig.split(",")[1]);
                var currentSpace = new Posicao(expected, fixed);
                spaces.get(i).add(currentSpace);
            }
        }
       return spaces;

    }
}
