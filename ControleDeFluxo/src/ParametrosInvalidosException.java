public class ParametrosInvalidosException extends Exception{
    @Override
    public String getMessage() {
        return String.format("O segundo parâmetro deve ser maior que o primeiro");
    }
}
