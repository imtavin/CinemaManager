package br.edu.up;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileManager {
    private Filme filme;
    private Integer id_filmes = 0;

    File diretorio = new File("C:\\Users\\autologon\\IdeaProjects\\Cinema\\src\\br\\edu\\up\\dados");
    File arqFilmes = new File(diretorio, "listaFilmes.txt");

    private void addFilmes(Filme filme) throws IOException {
        if (arqFilmes.exists() == false) {
            arqFilmes.createNewFile();
        }
        FileWriter arquivo = new FileWriter(arqFilmes);
        PrintWriter gravador = new PrintWriter(arquivo);
        gravador.println();

        }


    }




}

