package br.edu.up.modelos;

public class Ingresso extends Sessao {
    private double preco = 30.00;
    private Boolean meia;

    public Ingresso(Filme filme, String horario, boolean tipoDublado, boolean tipo3D, Boolean meia) {
        super(filme, horario, tipoDublado, tipo3D);
        this.meia = meia;
        if (this.getTipo3D() == true){
            this.preco = preco * 1.5;
        }

        if(this.meia == true){
            this.preco = preco / 2;
        }
    }

    @Override
    public String toString() {
        return "Ingresso{" +
                "Sessao{" +
                "horario='" + this.getHorario() + '\'' +
                ", tipo3D=" + this.getTipo3D() +
                ", tipoDublado=" + this.getTipoDublado() +
                ", Filme:" +  + '\'' +
                ", Gênero: " + + '\'' + "}" +
                ", preco=" + preco +
                ", meia=" + meia +
                '}';
    }
}
