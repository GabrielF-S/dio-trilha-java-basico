package ui.custom.scream;

import model.GameStatus;
import model.Posicao;
import service.BoardService;
import service.EventEnum;
import service.NotifierService;
import ui.custom.button.CheckGameStatusButton;
import ui.custom.button.FinishGameButton;
import ui.custom.button.ResetButton;
import ui.custom.frame.MainFrame;
import ui.custom.input.NumberText;
import ui.custom.painel.MainPanel;
import ui.custom.painel.SudokuSector;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainScreen {

    private final static Dimension DIMENSION = new Dimension(600,600);

    private final BoardService boardService;
    private final NotifierService notifierService;

    private JButton checkGameStatusButton;
    private JButton finishGameButton;
    private JButton resetButton;

    public MainScreen(final Map<String,String> gameConfig) {
        this.boardService = new BoardService(gameConfig);
        this.notifierService = new NotifierService();
    }

    public void builMainScreen(){
        JPanel mainPanel = new MainPanel(DIMENSION);
        JFrame mainFrame = new MainFrame(DIMENSION, mainPanel);
        for (int l = 0; l < 9 ; l+=3){
            int endLinha = l+2;
            for (int c = 0; c <9; c+=3){
                int endColuna = c+2;
                List<Posicao> posicao = getPosicaoFromSector(boardService.getSpaces(), c, endColuna, l, endLinha);
                JPanel sector = generateSection(posicao);
                mainPanel.add(sector);
            }
        }
        addResetButton(mainPanel);
        addCheckGameStatusButton(mainPanel);
        addFinishGameButton(mainPanel);
        mainFrame.revalidate();
        mainFrame.repaint();

    }

    private List<Posicao> getPosicaoFromSector(List<List<Posicao>> spaces, int c, int endColuna, int l, int endLinha) {
        List<Posicao> setorPosicao = new ArrayList<>();
        for (int linha = l ; linha<= endLinha; linha++ ){
            for(int coluna = c; coluna<= endColuna; coluna++){
                setorPosicao.add(spaces.get(coluna).get(linha));
            }
        }
        return  setorPosicao;
    }

    private JPanel generateSection(final List<Posicao> posicaos){
        List<NumberText>fiels = new ArrayList<>(posicaos.stream().map(NumberText::new).toList());
        fiels.forEach(t -> notifierService.subscriber(EventEnum.CLEAR_SPACE, t));
        return new SudokuSector(fiels);
    }

    private void addResetButton(JPanel mainPanel) {
        resetButton = new ResetButton(e -> {
            int dialogResult = JOptionPane.showConfirmDialog(null,
                    "Deseja realmente reiniciar o jogo? ",
                    "Limpar o  Jogo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (dialogResult == 0){
                boardService.reset();
                notifierService.notify(EventEnum.CLEAR_SPACE);
            }
        });
        mainPanel.add(resetButton);
    }

    private void addCheckGameStatusButton(JPanel mainPanel) {
        checkGameStatusButton = new CheckGameStatusButton(e -> {
            boolean hasError = boardService.hasError();
            GameStatus gameStatus = boardService.getStatus();
            String message = switch (gameStatus){
                case NON_STARTED -> "O Jogo não foi iniciado";
                case INCOMPLETE ->"O Jogo esta incompleto" ;
                case COMPLETE ->"O jogo esta completo" ;
            };
            message += hasError ? " e contem erros" : " e não contem erros";
            JOptionPane.showMessageDialog(null,message);
        });
        mainPanel.add(checkGameStatusButton);
    }

    private void addFinishGameButton(JPanel mainPanel) {
         finishGameButton = new FinishGameButton(e -> {
            if (boardService.gameIsFinished()){
                JOptionPane.showMessageDialog(null, "Parabens você concluir o jogo");
                resetButton.setEnabled(false);
                checkGameStatusButton.setEnabled(false);
                finishGameButton.setEnabled(false);
            }else {
                JOptionPane.showMessageDialog(null, "Seu jogo contem alguma inconsistencia, ajuste e tente de novo");
            }
        });

        mainPanel.add(finishGameButton);
    }
}
