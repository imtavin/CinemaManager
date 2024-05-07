package br.edu.up.modelos;

import java.util.ArrayList;
import java.util.List;

public class Sessao {
    private int idSessao;
    private Filme filme;
    private String horario;
    private boolean tipo3D;
    private boolean tipoDublado;
    private int sala;
    private List<Integer> assentosDisponiveis;

    public Sessao(Filme filme, String horario, boolean tipoDublado, boolean tipo3D, int sala) {
        this.filme = filme;
        this.horario = horario;
        this.tipoDublado = tipoDublado;
        this.tipo3D = tipo3D;
        this.idSessao = (LastId.getLastIdSessao()) + 1;
        this.sala = sala;
        this.assentosDisponiveis = new ArrayList<>();
        for (int i = 1; i <= 34; i++) {
            this.assentosDisponiveis.add(i);
        }
    }

    public Sessao(int idSessao, Filme filme, String horario, boolean tipo3D, boolean tipoDublado, int sala, List<Integer> assentosDisponiveis) {
        this.idSessao = idSessao;
        this.filme = filme;
        this.horario = horario;
        this.tipo3D = tipo3D;
        this.tipoDublado = tipoDublado;
        this.sala = sala;
        this.assentosDisponiveis = assentosDisponiveis;
    }

    public int getIdSessao() {
        return idSessao;
    }

    public Filme getFilme() {
        return filme;
    }

    public String getHorario() {
        return horario;
    }

    public boolean getTipo3D() {
        return tipo3D;
    }

    public boolean getTipoDublado() {
        return tipoDublado;
    }

    public int getSala() {return sala;}

    public boolean ocuparAssento(int assento) {
        if (assentosDisponiveis.contains(assento)) {
            assentosDisponiveis.remove(Integer.valueOf(assento));
            return true;
        } else {
            return false;
        }
    }

    public List<Integer> getAssentosDisponiveis() {
        return assentosDisponiveis;
    }
}

