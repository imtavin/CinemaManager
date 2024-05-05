//Pacote e importes
package br.edu.up.modelos;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Definição da classe FileManager
public class FileManager {
    // As listas são declarada como privada e final para garantir que não seja reatribuída após a inicialização
    private final List<Filme> filmes;
    private final List<Sessao> sessoes;
    private final List<Cliente> clientes;

    //Diretorio dos dados
    File diretorio = new File("E:\\UP\\5ºSem\\DesnvolvimentoDeSoftware\\Cinema\\src\\br\\edu\\up\\dados");
    File arqFilmes = new File(diretorio, "listaFilmes.txt");
    File arqSessoes = new File(diretorio, "listaSessoes.txt");
    File arqClientes = new File(diretorio, "listaClientes.txt");
    File arqVendas = new File(diretorio, "listaVendas.txt");

    // Construtor que carrega filmes e sessões
    public FileManager() throws IOException {
        // Inicializa as listas como um ArrayList vazio
        this.sessoes = new ArrayList<>();
        this.filmes = new ArrayList<>();
        this.clientes = new ArrayList<>();

        // Chamamos as funçoes para pegar os dados do TXT e colocar nas listas
        carregarFilmes();
        carregarSessoes();
        carregarClientes();
    }

    // Método para carregar filmes a partir do arquivo txt listaFilmes
    private void carregarFilmes() throws IOException {
        //Verificando se ja existe o arquivo txt
        if (!arqClientes.exists()) {
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
        if (!arqClientes.exists()) {
            arqSessoes.createNewFile();
            return;
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

            if (idSessao > LastId.getLastIdSessao()) {
                LastId.setLastIdSessao(idSessao);
            }

            Filme filme = buscarFilme(tituloFilme);
            if (filme != null) {
                Sessao sessao = new Sessao(idSessao, filme, horario, tipo3D, tipoDublado);
                sessoes.add(sessao);
            }else {
                Sessao sessao = new Sessao(idSessao, null, horario, tipo3D, tipoDublado);
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

    public void adicionarSessao(Sessao sessao) {
        for (Sessao i : sessoes) {
            if (Objects.equals(i.getFilme().getTitulo(), sessao.getFilme().getTitulo())) {
                System.out.println("Nome igual");
                if (Objects.equals(i.getHorario(), sessao.getHorario())) {
                    System.out.println("Horario igual");
                    if (i.getTipo3D() == sessao.getTipo3D()) {
                        System.out.println("3D igual");
                        if (i.getTipoDublado() == sessao.getTipoDublado()) {
                            System.out.println("Sessão ja adicionado");
                            return;
                        }
                    }
                }
            }
        }
        try {
            sessoes.add(sessao);
            LastId.setLastIdSessao(LastId.getLastIdSessao() + 1);
            System.out.println("Sessoes adicionado!");
        }
        catch (Exception e) {
            System.out.println("Sessão não adicionado");
        }
    }

    private void carregarClientes() throws IOException {
        if (!arqClientes.exists()) {
            arqClientes.createNewFile();
        }
        BufferedReader br = new BufferedReader(new FileReader(arqClientes));
        String linha;
        while ((linha = br.readLine()) != null) {
            String[] dados = linha.split(",");
            String nome = dados[0].trim();
            String cpf = dados[1].trim();
            int idade = Integer.parseInt(dados[2].trim());

            Cliente cliente = new Cliente(nome, cpf, idade);
            clientes.add(cliente);
            }
        br.close();
        System.out.println("Clientes: " + clientes.size());
        }


    public void salvarClientes() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(arqClientes));
        for (Cliente cliente : clientes) {
            bw.write(cliente.getNome() + "," + cliente.getCpf() + "," + cliente.getIdade());
            bw.newLine();
        }
        bw.close();
        System.out.println("Clientes salvos!");
    }

    public void adicionarCliente(Cliente cliente) {
        for (Cliente i : clientes) {
            if (Objects.equals(i.getCpf(), cliente.getCpf())) {
                System.out.println("Cliente ja adicionado");
                return;
            }
        }
        clientes.add(cliente);
        System.out.println("Cliente adicionado");

    }
}

