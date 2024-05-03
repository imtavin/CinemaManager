package br.edu.up.modelos;

import java.util.List;

public class Sessao {
    private int idSessao;
    private Filme filme;
    private String horario;
    private boolean tipo3D;
    private boolean tipoDublado;

    public Sessao(Filme filme, String horario, boolean tipoDublado, boolean tipo3D) {
        this.filme = filme;
        this.horario = horario;
        this.tipoDublado = tipoDublado;
        this.tipo3D = tipo3D;
    }

    public Sessao(int idSessao, Filme filme, String horario, boolean tipo3D, boolean tipoDublado) {
        this.idSessao = idSessao;
        this.filme = filme;
        this.horario = horario;
        this.tipo3D = tipo3D;
        this.tipoDublado = tipoDublado;
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


}

