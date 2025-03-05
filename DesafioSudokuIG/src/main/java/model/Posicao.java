package model;

public class Posicao {


    private Integer valorPreechido;
    private Integer valorEsperado;
    private boolean fixo;


    public Posicao(int expected, boolean fixed) {
        this.valorEsperado = expected;
        this.fixo = fixed;
        if (fixed){
            valorPreechido = expected;
        }
    }




    public Integer getValorPreechido() {
        return valorPreechido;
    }



    public boolean isPreenchido() {
        return this.valorPreechido != null;
    }

    public boolean isCorreto() {
        return this.valorPreechido == this.valorEsperado;
    }

    public boolean isFixo() {
        return fixo;
    }



    public void setValorPreechido(Integer valorEsperado) {
        if (!isFixo()){
            this.valorPreechido = valorEsperado;
        }
    }


    public void limparValor() {
        if (!isFixo()){
            this.valorPreechido = null;
        }

    }
}
