import br.edu.up.modelos.FileManager;
import br.edu.up.modelos.Filme;

import java.io.IOException;

public class Principal {
    public static void main(String[] args) throws IOException {
        FileManager manager = new FileManager();

        Filme filme1 = new Filme("Uncharted", "Acao");
        Filme filme2 = new Filme("Sorria", "Terror");
        Filme filme3 = new Filme("Marly e eu", "Drama");

        manager.adicionarFilme(filme1);
        manager.adicionarFilme(filme2);
        manager.adicionarFilme(filme3);

        manager.salvarFilmes();

    }
}