import model.Board;
import model.Posicao;
import util.BoardTemplate;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static util.BoardTemplate.*;

public class App {
    private final static Scanner input = new Scanner(System.in);

    private static Board board;

    private final static  int BOARD_LITMIT = 9;

    public static void main(String[] args) {

        final var position = Stream.of(args).collect(Collectors.toMap(
                k -> k.split(";")[0],
                v -> v.split(";")[1]
        ));

        int opcao = 1;
        while (true){
            System.out.println("Selecione uma das opções a seguir");
            System.out.println("1 - Iniciar um novo Jogo");
            System.out.println("2 - Colocar um novo número");
            System.out.println("3 - Remover um número");
            System.out.println("4 - Visualizar jogo atual");
            System.out.println("5 - Verificar status do jogo");
            System.out.println("6 - limpar jogo");
            System.out.println("7 - Finalizar jogo");
            System.out.println("8 - Sair");

            opcao = input.nextInt();

            switch (opcao){
                case 1 -> startGame(position);
                case 2 -> inputNumber();
                case 3 -> removeNumber();
                case 4 -> showCurrentGame();
                case 5 -> showGameStatus();
                case 6 -> clearGame();
                case 7 -> finishGame();
                case 8 -> System.exit(0);
                default -> System.out.println("Opção inválida, selecione uma das opções do menu");

            }
        }

    }

    private static void startGame(Map<String, String> position) {
        if(Objects.nonNull(board)){
            System.out.println("O Jogo já foi iniciado");
            return;
        }

        List<List<Posicao>> spaces = new ArrayList<>();
        for (int i = 0; i< BOARD_LITMIT; i++){
            spaces.add(new ArrayList<>());
            for (int j = 0; j < BOARD_LITMIT; j++){
                var positionConfig = position.get("%s,%s".formatted(i,j));
                var expected = Integer.parseInt(positionConfig.split(",")[0]);
                var fixed = Boolean.parseBoolean(positionConfig.split(",")[1]);
                var currentSpace = new Posicao(expected, fixed);
                spaces.get(i).add(currentSpace);
            }
        }
        board = new Board(spaces);
        System.out.println("O jogo está pronto para começar");

    }

    private static void inputNumber() {
        if(Objects.isNull(board)){
            System.out.println("O Jogo ainda não foi iniciado");
            return;
        }

        int coluna = runUntilGetValidNumber("Informe a coluna que o numero sera inserido",0, 8);
        int linha = runUntilGetValidNumber("Informe a linha que o numero sera inserido",0, 8);
        int value = runUntilGetValidNumber("Informe o número que vai entrar na posição [%s,%s]".formatted(coluna,linha),1, 9);
        if (!board.inserirValor(value,coluna,linha)){
            System.out.println("A posição [%s,%s] tem um valor fixo\n".formatted(coluna, linha));
        }
    }

    private static void removeNumber() {
        if(Objects.isNull(board)){
            System.out.println("O Jogo ainda não foi iniciado");
            return;
        }

        int coluna = runUntilGetValidNumber("Informe a coluna que o numero sera removido",0, 8);
        int linha = runUntilGetValidNumber("Informe a linha que o numero sera removido",0, 8);
        if (!board.limparValor(coluna,linha)){
            System.out.println("A posição [%s,%s] tem um valor fixo\n".formatted(coluna, linha));
        }

    }

    private static void showCurrentGame() {
        if(Objects.isNull(board)){
            System.out.println("O Jogo ainda não foi iniciado");
            return;
        }

        var args = new Object[81];
        var argsPos =  0 ;
       for(int i = 0; i < BOARD_LITMIT; i++){
           for (var col : board.getPosicoes()){
               args[argsPos++] = " " +  ((Objects.isNull(col.get(i).getValorPreechido())) ? " " : col.get(i).getValorPreechido());
           }
       }
        System.out.println("Seu jogo se encontra da seguinte forma");
        System.out.println(BOARD_TEMPLATE.formatted(args));

    }

    private static void showGameStatus() {
        if(Objects.isNull(board)){
            System.out.println("O Jogo ainda não foi iniciado");
            return;
        }
        System.out.println("O jogo atuamente se encontra no status: "+ board.getStatus().getLabel());
        if (board.hasErorr()){
            System.out.println("O jogo possui erros");
        }else {
            System.out.println("O jogo não possui erros");
        }

    }

    private static void clearGame() {
        if(Objects.isNull(board)){
            System.out.println("O Jogo ainda não foi iniciado");
            return;
        }

        String confirm = soliciarValidString("Tem certeza que deseja  limpar o jogo e perder todo seu progresso? ");
        while (!confirm.equalsIgnoreCase("sim") &&!confirm.equalsIgnoreCase("não"  )){
                confirm = soliciarValidString("Informe 'sim' ou 'não'");
        }
        if (confirm.equalsIgnoreCase("sim")){
            board.reset();
        }


    }

    private static void finishGame() {
        if(Objects.isNull(board)){
            System.out.println("O Jogo ainda não foi iniciado");
            return;
        }

        if (board.gameIsFinished()){
            System.out.println("Parabens você concluiu o jogo");
            showCurrentGame();
            board= null;
        } else if (board.hasErorr()) {
            System.out.println("Seu jogo contem erros, verifique seu board e ajuste");
        }else {
            System.out.println("Ainda há campos sem preenchimento");
        }

    }

    private static int runUntilGetValidNumber(String x, final int min, final int max){
        System.out.println(x);
        int current = input.nextInt();
        while(current<min || current >max){
            System.out.println("Informe um numero entre " + min + " e " +  max );
            current = input.nextInt();
        }
        return  current;
    }

    private static String soliciarValidString(String s) {
        System.out.println(s);
        String resposta = input.nextLine();
        return  resposta;
    }
}
