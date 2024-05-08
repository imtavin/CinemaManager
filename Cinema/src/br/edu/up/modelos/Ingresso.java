package br.edu.up.modelos;

public class Ingresso {
    private Sessao sessao;
    private int assento;
    private boolean meia;
    private double preco;

    public Ingresso(Sessao sessao, int assento, boolean meia) {
        this.sessao = sessao;
        this.assento = assento;
        this.meia = meia;
        this.preco = sessao.getPreco();

        if(this.meia){
            this.preco = this.sessao.getPreco()/2;
        }
    }

    public Sessao getSessao() {
        return sessao;
    }

    public int getAssento() {
        return assento;
    }

    public boolean isMeia() {
        return meia;
    }

    public double getPreco() {
        return preco;
    }

    @Override
    public String toString() {
        return "Ingresso{" +
                "sessao=" + sessao +
                ", assento=" + assento +
                ", meia=" + meia +
                '}';
    }
}
