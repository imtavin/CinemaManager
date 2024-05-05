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
}
