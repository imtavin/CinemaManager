package br.edu.up.modelos;

public class Ingresso {
    private Sessao sessao;
    private int assento;
    private Cliente cliente;
    private boolean meia;

    public Ingresso(Sessao sessao, int assento, Cliente cliente, boolean meia) {
        this.sessao = sessao;
        this.assento = assento;
        this.cliente = cliente;
        this.meia = meia;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public int getAssento() {
        return assento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public boolean isMeia() {
        return meia;
    }

    @Override
    public String toString() {
        return "Ingresso{" +
                "sessao=" + sessao +
                ", assento=" + assento +
                ", cliente=" + cliente +
                ", meia=" + meia +
                '}';
    }
}
