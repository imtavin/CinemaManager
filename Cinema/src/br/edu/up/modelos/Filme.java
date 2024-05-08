package br.edu.up.modelos;

public class Filme {
    private Integer idFilme;
    private String titulo;
    private String genero;

    public Filme(Integer idFilme, String titulo, String genero) {
        this.idFilme = idFilme;
        this.titulo = titulo;
        this.genero = genero;
    }

    public Filme(String titulo, String genero) {
        this.genero = genero;
        this.titulo = titulo;
        this.idFilme = LastId.getLastIdFilme();
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
