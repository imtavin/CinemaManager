package br.edu.up;

import java.util.List;

public class Sessao {

        private String horario;
        private boolean tipo3D;
        private boolean tipoDublado;
        private List<Sessao> sessoes;

        public Sessao(boolean tipo3D, String horario, boolean tipoDublado) {
            this.tipo3D = tipo3D;
            this.horario = horario;
            this.tipoDublado = tipoDublado;
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

    @Override
    public String toString() {
        return "Sessao{" +
                "horario='" + horario + '\'' +
                ", tipo3D=" + tipo3D +
                ", tipoDublado=" + tipoDublado +
                ", filmes=" + filmes +
                ", sessoes=" + sessoes +
                '}';
    }
}

