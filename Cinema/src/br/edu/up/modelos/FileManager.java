//Pacote e importes
package br.edu.up.modelos;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Definição da classe FileManager
public class FileManager {
    //Inicialização das listas de filmes e sesssoes
    private List<Filme> filmes;
    private List<Sessao> sessoes;

    //Diretorio dos dados
    File diretorio = new File("E:\\UP\\5ºSem\\DesnvolvimentoDeSoftware\\Cinema\\src\\br\\edu\\up\\dados");
    File arqFilmes = new File(diretorio, "listaFilmes.txt");
    File arqSessoes = new File(diretorio, "listaSessoes.txt");
    File arqVendas = new File(diretorio, "listaVendas.txt");

    // Construtor que carrega filmes e sessões
    public FileManager() throws IOException {
        this.sessoes = new ArrayList<>();
        this.filmes = new ArrayList<>();

        carregarFilmes();
        carregarSessoes();

    }

    // Método para carregar filmes a partir do arquivo txt listaFilmes
    private void carregarFilmes() throws IOException {
        //Verificando se ja existe o arquivo txt
        if (arqFilmes.exists() == false) {
            arqFilmes.createNewFile();
        }
        //Inicializando Buffered que ira pemitir lermos linha a linha do arquivo
        BufferedReader br = new BufferedReader(new FileReader(arqFilmes));
        String linha;
        //Rodando linha por linha até que nao tenha mais linhas
        while ((linha = br.readLine()) != null) {
            //Separando os dados pela "," depois estanciando um novo obj com esses dados
            String[] dados = linha.split(",");
            String nome = dados[0].trim();
            String genero = dados[1].trim();
            Filme filme = new Filme(nome, genero);
            //Adicionando o obj a lista de filmes
            filmes.add(filme);
        }
        //Fechando o Buffered
        br.close();
        System.out.println("Filmes: " + filmes.size());
    }

    public void salvarFilmes() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(arqFilmes));
        for (Filme filme : filmes) {
            bw.write(filme.getTitulo() + ", " + filme.getGenero());
            bw.newLine();
        }
        bw.close();
        System.out.println("Filmes salvos!");
    }

    public void adicionarFilme(Filme filme) {
        for (Filme i : filmes) {
            if (i.getTitulo().equals(filme.getTitulo())) {
                System.out.println("Filme ja adicionado");
                return;
            }
        }
        filmes.add(filme);
        System.out.println("Filme adicionado");

    }

    public void deletarFilme(Filme filme) {
        for (Filme i : filmes) {
            if (i.getTitulo().equals(filme.getTitulo())) {
                filmes.remove(i);
            }
        }
    }

    public Filme buscarFilme(String titulo) {
        for (Filme i : filmes) {
            if (i.getTitulo().equals(titulo)) {
                return i;
            }
        }
        return null;
    }

    private void carregarSessoes() throws IOException {
        if (arqFilmes.exists() == false) {
            arqFilmes.createNewFile();
        }
        BufferedReader br = new BufferedReader(new FileReader(arqSessoes));
        String linha;
        while ((linha = br.readLine()) != null) {
            String[] dados = linha.split(",");
            int idSessao = Integer.parseInt(dados[0].trim());
            String tituloFilme = dados[1].trim();
            String horario = dados[2].trim();
            boolean tipo3D = Boolean.parseBoolean(dados[3].trim());
            boolean tipoDublado = Boolean.parseBoolean(dados[4].trim());

            Filme filme = buscarFilme(tituloFilme);
            if (filme != null) {
                Sessao sessao = new Sessao(idSessao, filme, horario, tipo3D, tipoDublado);
                sessoes.add(sessao);
            }
        }
        br.close();
        System.out.println("Sessoes: " + sessoes.size());
    }

    public void salvarSessoes() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(arqSessoes));
        for (Sessao sessao : sessoes) {
            bw.write(sessao.getIdSessao() + ", " + sessao.getFilme().getTitulo() + ", "
                    + sessao.getHorario() + ", " + sessao.getTipo3D() + ", " + sessao.getTipoDublado());
            bw.newLine();
        }
        bw.close();
        System.out.println("Sessoes salvas!");
    }

    public void adicionarSessao(Filme filme) {
        for (Filme i : filmes) {
            if (i.getTitulo().equals(filme.getTitulo())) {
                System.out.println("Sessão ja adicionado");
                return;
            }
        }
        filmes.add(filme);
        System.out.println("Sessão adicionado");

    }
}

