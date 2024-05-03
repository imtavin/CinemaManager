package br.edu.up;

public class Ingresso extends Sessao {
    private double preco = 30.00;
    private Boolean meia;

    public Ingresso(boolean tipo3D, String horario, boolean tipoDublado, Boolean meia) {
        super(tipo3D, horario, tipoDublado);
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
                    ", Gênero: " + genero + '\'' + "}" +
                ", preco=" + preco +
                ", meia=" + meia +
                '}';
    }
}
