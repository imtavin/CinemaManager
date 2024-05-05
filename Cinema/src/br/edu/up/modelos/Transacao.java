package br.edu.up.modelos;

import java.time.LocalDateTime;

public class Transacao {
    private int idTransacao;
    private Ingresso ingresso;
    private Sessao sessao;
    private Cliente cliente;
    private LocalDateTime dataTransacao;

    public Transacao(int idTransacao, Ingresso ingresso, Sessao sessao, Cliente cliente, LocalDateTime dataTransacao) {
        this.idTransacao = idTransacao;
        this.ingresso = ingresso;
        this.sessao = sessao;
        this.cliente = cliente;
        this.dataTransacao = dataTransacao;
    }

    public Ingresso getIngresso() {
        return ingresso;
    }

    public void setIngresso(Ingresso ingresso) {
        this.ingresso = ingresso;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDateTime getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(LocalDateTime dataTransacao) {
        this.dataTransacao = dataTransacao;
    }
}
