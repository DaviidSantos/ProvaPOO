package prova;

public abstract class Selecao {
    protected String pais;
    protected int QtdeTitulos;

    public Selecao() {
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getQtdeTitulos() {
        return QtdeTitulos;
    }

    public void setQtdeTitulos(int qtdeTitulos) {
        QtdeTitulos = qtdeTitulos;
    }
}
