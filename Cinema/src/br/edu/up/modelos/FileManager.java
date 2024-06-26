//Pacote e importes
package br.edu.up.modelos;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

// Definição da classe FileManager
public class FileManager {
    // As listas são declarada como privada e final para garantir que não seja reatribuída após a inicialização
    private final List<Filme> filmes;
    private final List<Sessao> sessoes;
    private final List<Cliente> clientes;
    private final  List<Transacao> transacoes;

    //Diretorio dos dados
    File diretorio = new File("C:\\Users\\autologon\\Desktop\\CinemaManager\\CinemaManager-develop\\Cinema\\src\\br\\edu\\up\\dados");
    File arqFilmes = new File(diretorio, "listaFilmes.txt");
    File arqSessoes = new File(diretorio, "listaSessoes.txt");
    File arqClientes = new File(diretorio, "listaClientes.txt");
    File arqTransacoes = new File(diretorio, "listaTransacoes.txt");

    // Construtor que carrega filmes e sessões
    public FileManager() throws IOException {
        // Inicializa as listas como um ArrayList vazio
        this.sessoes = new ArrayList<>();
        this.filmes = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.transacoes = new ArrayList<>();

        // Chamamos as funçoes para pegar os dados do TXT e colocar nas listas
        carregarFilmes();
        carregarSessoes();
        carregarClientes();
        carregarTransacoes();
    }

    // Método para carregar filmes a partir do arquivo txt listaFilmes
    private void carregarFilmes() throws IOException {
        //Verificando se ja existe o arquivo txt
        if (!arqFilmes.exists()) {
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
        //System.out.println("Filmes: " + filmes.size());
    }

    //Escreve os filmes da Lista filmes no arquivo txt
    public void salvarFilmes() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(arqFilmes));
        for (Filme filme : filmes) {
            bw.write(filme.getTitulo() + ", " + filme.getGenero());
            bw.newLine();
        }
        bw.close();
        System.out.println("Filmes salvos!");
    }

    //Adiciona um filme á lista filme, checando se o filme já se encontra na lista.
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

    //Deleta um filme da lista ao receber um título.
    public void deletarFilme(Filme filme) {
        for (Filme i : filmes) {
            if (i.getTitulo().equals(filme.getTitulo())) {
                filmes.remove(i);
                System.out.println("Filme removido");
                return;
            }
        }
        System.out.println("Filme não removido");
    }

    //Função que pesquisa um título na lista de filmes
    public Filme buscarFilme(String titulo) {
        for (Filme i : filmes) {
            if (i.getTitulo().equals(titulo)) {
                return i;
            }
        }
        return null;
    }

    //Função que retorna um sinal booleano se o filme existe no título.
    public boolean buscarFilmeExiste(String titulo) {
        for (Filme i : filmes) {
            if (i.getTitulo().equals(titulo)) {
                return true;
            }
        }
        return false;
    }

    // Listar todos os filmes em ordem de título
    public void listarFilmesOrdenadosPorTitulo() {
        List<Filme> filmesOrdenados = new ArrayList<>(filmes);
        filmesOrdenados.sort(Comparator.comparing(Filme::getTitulo));

        for (Filme filme : filmesOrdenados) {
            System.out.println("Título: " + filme.getTitulo());
            System.out.println("Gênero: " + filme.getGenero());
            System.out.println("--------------------------------------");
        }
    }

    // Listar todos os filmes em ordem de gênero
    public void listarFilmesOrdenadosPorGenero() {
        List<Filme> filmesOrdenados = new ArrayList<>(filmes);
        filmesOrdenados.sort(Comparator.comparing(Filme::getGenero));

        for (Filme filme : filmesOrdenados) {
            System.out.println("Título: " + filme.getTitulo());
            System.out.println("Gênero: " + filme.getGenero());
            System.out.println("--------------------------------------");
        }
    }

    // Listar os filmes na ordem em que foram adicionados
    public void listarFilmes() {
        for (Filme filme : filmes) {
            System.out.println("Título: " + filme.getTitulo());
            System.out.println("Gênero: " + filme.getGenero());
            System.out.println("--------------------------------------");
        }
    }


    //Carrega a lista de sessões baseado no arquivo txt listaSessoes
    private void carregarSessoes() throws IOException {
        if (!arqSessoes.exists()) {
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
            int sala = Integer.parseInt(dados[5].trim());
            String[] assentosStr = dados[6].trim().split("-");
            List<Integer> assentos = new ArrayList<>();
            for (String assentoStr : assentosStr) {
                assentos.add(Integer.parseInt(assentoStr.trim()));
            }

            if (idSessao > LastId.getLastIdSessao()) {
                LastId.setLastIdSessao(idSessao);
            }

            Filme filme = buscarFilme(tituloFilme);
            if (filme != null) {
                Sessao sessao = new Sessao(idSessao, filme, horario, tipo3D, tipoDublado, sala, assentos);
                sessoes.add(sessao);
            }else {
                Sessao sessao = new Sessao(idSessao, null, horario, tipo3D, tipoDublado, sala, assentos);
                sessoes.add(sessao);
            }
        }
        br.close();
    }

    //Função que salva no arquivo txt listaSessões o conteudo da lista Sessões
    public void salvarSessoes() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(arqSessoes));
        for (Sessao sessao : sessoes) {
            bw.write(sessao.getIdSessao() + ", " + sessao.getFilme().getTitulo() + ", "
                    + sessao.getHorario() + ", " + sessao.getTipo3D() + ", "
                    + sessao.getTipoDublado() + ", " + sessao.getSala() + ", " + contatenaAssentos(sessao.getAssentosDisponiveis()));
            bw.newLine();
        }
        bw.close();
        System.out.println("Sessoes salvas!");
    }

    //Transforma a lista de assentos de inteiro para String.
    public String contatenaAssentos(List<Integer> assentosDisponiveis){
        StringBuilder assentosStr = new StringBuilder();

        for (int i = 0; i < assentosDisponiveis.size(); i++) {
            assentosStr.append(assentosDisponiveis.get(i));
            if (i != assentosDisponiveis.size() - 1) {
                assentosStr.append("-");
            }
        }

        return assentosStr.toString();
    }

    //Recebe Nome, Horário, e 3D. Se todos forem únicos, adiciona a sessão à lista e a atribui um ID.
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

    //Se o ID obtido igualar um ID na lista de sessões, deleta a sessão.
    public Boolean deletarSessao(int idSessao) throws IOException {
        for (Sessao i : sessoes) {
            if (i.getIdSessao() == idSessao) {
                sessoes.remove(i);
                return true;
            }
        }
        return false;
    }

    //Se o ID obtido igualar a um ID na lista de sessões, imprime os dados da sessão
    public Sessao buscarSessao(int IdSessao) {
        for (Sessao i : sessoes) {
            if (i.getIdSessao() == IdSessao) {
                return i;
            }
        }
        return null;
    }

    //função para listar as sessões.
    public void listarSessoes(){
        for (Sessao sessao : sessoes) {
            System.out.println("ID: " + sessao.getIdSessao());
            System.out.println("Filme: " + sessao.getFilme().getTitulo());
            System.out.println("Horário: " + sessao.getHorario());
            System.out.println("Tipo 3D: " + (sessao.getTipo3D() ? "Sim" : "Não"));
            System.out.println("Tipo Dublado: " + (sessao.getTipoDublado() ? "Sim" : "Não"));
            System.out.println("Sala: " + sessao.getSala());
            System.out.println("Assentos disponíveis: " + sessao.getAssentosDisponiveis());
            System.out.println("--------------------------------------");
        }
    }

    //função para listar as sessões de forma ordenada pelo seu ID
    public void listarSessoesOrdenadasPorId() {
        List<Sessao> sessoesOrdenadas = new ArrayList<>(sessoes);
        sessoesOrdenadas.sort(Comparator.comparingInt(Sessao::getIdSessao));

        for (Sessao sessao : sessoesOrdenadas) {
            System.out.println("ID: " + sessao.getIdSessao());
            System.out.println("Filme: " + sessao.getFilme().getTitulo());
            System.out.println("Horário: " + sessao.getHorario());
            System.out.println("Tipo 3D: " + (sessao.getTipo3D() ? "Sim" : "Não"));
            System.out.println("Tipo Dublado: " + (sessao.getTipoDublado() ? "Sim" : "Não"));
            System.out.println("Sala: " + sessao.getSala());
            System.out.println("Assentos disponíveis: " + sessao.getAssentosDisponiveis());
            System.out.println("--------------------------------------");
        }
    }

    //função para listar as sessões de forma ordenada pelo titulo do filme.
    public void listarSessoesOrdenadasPorTituloFilme() {
        List<Sessao> sessoesOrdenadas = new ArrayList<>(sessoes);
        sessoesOrdenadas.sort(Comparator.comparing(sessao -> sessao.getFilme().getTitulo()));

        for (Sessao sessao : sessoesOrdenadas) {
            System.out.println("ID: " + sessao.getIdSessao());
            System.out.println("Filme: " + sessao.getFilme().getTitulo());
            System.out.println("Horário: " + sessao.getHorario());
            System.out.println("Tipo 3D: " + (sessao.getTipo3D() ? "Sim" : "Não"));
            System.out.println("Tipo Dublado: " + (sessao.getTipoDublado() ? "Sim" : "Não"));
            System.out.println("Sala: " + sessao.getSala());
            System.out.println("Assentos disponíveis: " + sessao.getAssentosDisponiveis());
            System.out.println("--------------------------------------");
        }
    }

    //função para listar as sessões de forma ordenada pela Sala
    public void listarSessoesOrdenadasPorSala() {
        List<Sessao> sessoesOrdenadas = new ArrayList<>(sessoes);
        sessoesOrdenadas.sort(Comparator.comparingInt(Sessao::getSala));

        for (Sessao sessao : sessoesOrdenadas) {
            System.out.println("ID: " + sessao.getIdSessao());
            System.out.println("Filme: " + sessao.getFilme().getTitulo());
            System.out.println("Horário: " + sessao.getHorario());
            System.out.println("Tipo 3D: " + (sessao.getTipo3D() ? "Sim" : "Não"));
            System.out.println("Tipo Dublado: " + (sessao.getTipoDublado() ? "Sim" : "Não"));
            System.out.println("Sala: " + sessao.getSala());
            System.out.println("Assentos disponíveis: " + sessao.getAssentosDisponiveis());
            System.out.println("--------------------------------------");
        }
    }

    //Função para listar todos as sessões de um filme
    public void listarSessoesPorFilme(String tituloFilme) {
        for (Sessao sessao : sessoes) {
            if (sessao.getFilme().getTitulo().equalsIgnoreCase(tituloFilme)) {
                System.out.println("ID: " + sessao.getIdSessao());
                System.out.println("Horário: " + sessao.getHorario());
                System.out.println("Sessao " + (sessao.getTipo3D() ? "3D" : "2D"));
                System.out.println("Sessao " + (sessao.getTipoDublado() ? "Dublada" : "Legendada"));
                System.out.println("Sala: " + sessao.getSala());
                System.out.println("--------------------------------------");
            }
        }
    }


    //função que carrega o conteúdo do arquivo txt dentro da lista Clientes
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
        }


    //função que salva os conteúdos da lista Clientes no arquivo txt listaClientes
        public void salvarClientes() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(arqClientes));
        for (Cliente cliente : clientes) {
            bw.write(cliente.getNome() + "," + cliente.getCpf() + "," + cliente.getIdade());
            bw.newLine();
        }
        bw.close();
        System.out.println("Clientes salvos!");
    }
//Função que adiciona um cliente na lista clientes, checando via nome e CPF se o cliente já n foi adicionado
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
//Obtem um CPF e, se encontrado um CPF igual na lista de Clientes, remove o Cliente.
    public void removerCliente(String cpf) {
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                clientes.remove(cliente);
                System.out.println("Cliente removido");
                return;
            }
        }
        System.out.println("Cliente não encontrado");
    }

    //A partir de um CPF, busca na lista por um Cliente com CPF igual
    public Cliente buscarCliente(String cpf){
        for (Cliente cliente : clientes) {
            if (Objects.equals(cliente.getCpf(), cpf)) {
                return cliente;
            }
        }
        return null;
    }

    //Faz uma listagem de todos os clientes
    public void listarClientes(){
        for (Cliente cliente : clientes) {
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("CPF: " + cliente.getCpf());
            System.out.println("Idade: " + cliente.getIdade());
            System.out.println("--------------------------------------");
        }
    }

    //faz uma listagem ordenada de todos os clientes pelo nome.
    public void listarClientesOrdenadosPorNome() {
        List<Cliente> clientesOrdenados = new ArrayList<>(clientes);
        clientesOrdenados.sort(Comparator.comparing(Cliente::getNome));

        for (Cliente cliente : clientesOrdenados) {
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("CPF: " + cliente.getCpf());
            System.out.println("Idade: " + cliente.getIdade());
            System.out.println("--------------------------------------");
        }
    }

    //Lista ordenada dos clientes pelo CPF
    public void listarClientesOrdenadosPorCpf() {
        List<Cliente> clientesOrdenados = new ArrayList<>(clientes);
        clientesOrdenados.sort(Comparator.comparing(Cliente::getCpf));

        for (Cliente cliente : clientesOrdenados) {
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("CPF: " + cliente.getCpf());
            System.out.println("Idade: " + cliente.getIdade());
            System.out.println("--------------------------------------");
        }
    }

    //lista ordenada de clientes pela Idade
    public void listarClientesOrdenadosPorIdade() {
        List<Cliente> clientesOrdenados = new ArrayList<>(clientes);
        clientesOrdenados.sort(Comparator.comparingInt(Cliente::getIdade));

        for (Cliente cliente : clientesOrdenados) {
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("CPF: " + cliente.getCpf());
            System.out.println("Idade: " + cliente.getIdade());
            System.out.println("--------------------------------------");
        }
    }

    //Carrega o conteúdo do arquivo txt listaTransacoes na lista de Transacoes
    private void carregarTransacoes() throws IOException {
        if (!arqTransacoes.exists()) {
            arqTransacoes.createNewFile();
            return;
        }

        BufferedReader br = new BufferedReader(new FileReader(arqTransacoes));
        String linha;
        while ((linha = br.readLine()) != null) {
            String[] dados = linha.split(",");
            int idTransacao = Integer.parseInt(dados[0].trim());
            List<Ingresso> ingressos = new ArrayList<>();
            int i = 1; // Índice para percorrer os dados de ingresso
            while (i < dados.length - 3) {
                int assento = Integer.parseInt(dados[i].trim());
                boolean meia = Boolean.parseBoolean(dados[i + 1].trim());
                int idSessao = Integer.parseInt(dados[i + 2].trim());
                Sessao sessao = buscarSessao(idSessao);
                if (sessao == null) {
                    ingressos.add(new Ingresso(null, assento, meia));
                } else {
                    ingressos.add(new Ingresso(sessao, assento, meia));
                }
                i += 3; // Avança para o próximo conjunto de valores
            }

            String cpfCliente = dados[dados.length - 3].trim();
            Cliente cliente = buscarCliente(cpfCliente);

            String horario = dados[dados.length - 2].trim();
            double valorTotal = Double.parseDouble(dados[dados.length - 1].trim());

            if (idTransacao > LastId.getLastIdTransacao()) {
                LastId.setLastIdTransacao(idTransacao);
            }

            Transacao transacao = new Transacao(idTransacao, ingressos, cliente, horario, valorTotal);
            transacoes.add(transacao);
        }
        br.close();
    }

    //Salva os conteudos da lista de transações no arquivo txt listaTransacoes
    public void salvarTransacoes() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(arqTransacoes));
        for (Transacao transacao : transacoes) {
            bw.write(transacao.getIdTransacao() + ",");
            for (Ingresso ingresso : transacao.getIngressos()) {
                if (ingresso.getSessao() == null) {
                    bw.write(ingresso.getAssento() + "," + ingresso.isMeia() + ",null,");
                } else {
                    bw.write(ingresso.getAssento() + "," + ingresso.isMeia() + "," + ingresso.getSessao().getIdSessao() + ",");
                }
            }
            bw.write(transacao.getCliente().getCpf() + ",");
            bw.write(transacao.getHorario() + ","); // Adiciona o horário aqui
            bw.write(transacao.getValorTotal() + "\n");
        }
        bw.close();
        System.out.println("Transações salvas!");
    }

    //função que adiciona a transação à lista de transações e lhe atribui um ID
    public void adicionarTransacao(Transacao transacao) {
        LastId.setLastIdTransacao(LastId.getLastIdTransacao() + 1);
        transacoes.add(transacao);
    }

    public Transacao buscarTransacao(int idTransacao) {
        for (Transacao transacao : transacoes) {
            if (transacao.getIdTransacao() == idTransacao) {
                return transacao;
            }
        }
        return null;
    }

    //Função para listar todas as transações sem ordenação
    public void listarTransacoes() {
        for (Transacao transacao : transacoes) {
            System.out.println("ID da Transação: " + transacao.getIdTransacao());
            System.out.println("Cliente: " + transacao.getCliente().getNome());
            System.out.println("Data da Transação: " + transacao.getHorario());
            System.out.println("Valor Total: " + transacao.getValorTotal());
            System.out.println("--------------------------------------");
        }
    }
//Listagem ordenada das transações pelo seu ID de transação
    public void listarTransacoesOrdenadasPorIdTransacao() {
        List<Transacao> transacoesOrdenadas = new ArrayList<>(transacoes);
        transacoesOrdenadas.sort(Comparator.comparingInt(Transacao::getIdTransacao));

        for (Transacao transacao : transacoesOrdenadas) {
            System.out.println("ID da Transação: " + transacao.getIdTransacao());
            System.out.println("Cliente: " + transacao.getCliente().getNome());
            System.out.println("Data da Transação: " + transacao.getHorario());
            System.out.println("Valor Total: " + transacao.getValorTotal());
            System.out.println("--------------------------------------");
        }
    }

    //listagem ordenada das transações pela sua data de origem
    public void listarTransacoesOrdenadasPorData() {
        List<Transacao> transacoesOrdenadas = new ArrayList<>(transacoes);
        transacoesOrdenadas.sort(Comparator.comparing(Transacao::getHorario));

        for (Transacao transacao : transacoesOrdenadas) {
            System.out.println("ID da Transação: " + transacao.getIdTransacao());
            try {
                System.out.println("Cliente: " + transacao.getCliente().getNome());
            }
            catch (Exception e) {
                System.out.println("Cliente:" + null);
            }
            System.out.println("Data da Transação: " + transacao.getHorario());
            System.out.println("Valor Total: " + transacao.getValorTotal());
            System.out.println("--------------------------------------");
        }
    }

    //Listagem ordenada das transações pelo seu valor total
    public void listarTransacoesOrdenadasPorValorTotal() {
        List<Transacao> transacoesOrdenadas = new ArrayList<>(transacoes);
        transacoesOrdenadas.sort(Comparator.comparingDouble(Transacao::getValorTotal));

        for (Transacao transacao : transacoesOrdenadas) {
            System.out.println("ID da Transação: " + transacao.getIdTransacao());
            System.out.println("Cliente: " + transacao.getCliente().getNome());
            System.out.println("Data da Transação: " + transacao.getHorario());
            System.out.println("Valor Total: " + transacao.getValorTotal());
            System.out.println("--------------------------------------");
        }
    }

    //função para obter o valor total das transações
    public double somarValoresTotaisTransacoes() {
        double soma = 0.0;
        for (Transacao transacao : transacoes) {
            soma += transacao.getValorTotal();
        }
        return soma;
    }

    //função para obter o horário local atual
    public static String obterHorarioAtual() {
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return agora.format(formatter);
    }
}

