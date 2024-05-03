package br.edu.up;

public class Filme {
    private String titulo;
    private String genero;

    public Filme(String genero, String titulo) {
        this.genero = genero;
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "Filme:" + titulo + '\'' +
                "\n Gênero: " + genero + '\'' +
                '}';
    }
}
