package br.edu.up.modelos;

import java.time.LocalDateTime;
import java.util.List;

public class Transacao {
    private int idTransacao;
    private List<Ingresso> ingressos;
    private Cliente cliente;
    private LocalDateTime horario;
    private double valorTotal;

    public Transacao(List<Ingresso> ingressos, Cliente cliente, LocalDateTime horario) {
        this.ingressos = ingressos;
        this.cliente = cliente;
        this.horario = horario;
        this.valorTotal = valorTotal;

        this.idTransacao = (LastId.getLastIdTransacao()) + 1;

        for (Ingresso ingresso : ingressos) {
            this.valorTotal = this.valorTotal + ingresso.getPreco();
        }
    }

    public Transacao(int idTransacao, List<Ingresso> ingressos, Cliente cliente, LocalDateTime horario, double valorTotal) {
        this.idTransacao = idTransacao;
        this.ingressos = ingressos;
        this.cliente = cliente;
        this.horario = horario;
        this.valorTotal = valorTotal;
    }

    public int getIdTransacao() {
        return idTransacao;
    }

    public List<Ingresso> getIngressos() {
        return ingressos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    @Override
    public String toString() {
        return "Transacao{" +
                "idTransacao=" + idTransacao +
                ", ingressos=" + ingressos +
                ", cliente=" + cliente +
                ", horario=" + horario +
                ", valorTotal=" + valorTotal +
                '}';
    }
}
