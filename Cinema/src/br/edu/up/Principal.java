//Projeto de Gerenciamento de Cinema
//Alunos: Pedro Henrique Costa Dias e Gustavo Espenchitt

package br.edu.up;

import br.edu.up.modelos.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws IOException {
        //Se cria os objetos do FileManager e do Scanner, também se inicializando a variável que controla o switch case.
        FileManager manager = new FileManager();
        Scanner scanner = new Scanner(System.in);
        Integer opcao = 1;

        while (opcao != 5) {
            System.out.println("////////////////////GERENCIAMENTO DE CINEMA////////////////");
            System.out.println("1-Filmes");
            System.out.println("2-Sessões");
            System.out.println("3-Vendas");
            System.out.println("4-Clientes");
            System.out.println("5-Encerrar Programa");
            System.out.println("///////////////////////////////////////////////////////////");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("//////////////FILMES//////////////");
                    Integer opFilmes;
                    System.out.println("1-Adicionar Filme");
                    System.out.println("2-Remover Filme");
                    System.out.println("3-Procurar Filme");
                    System.out.println("4-Listar Filmes");
                    System.out.println("5-Voltar");
                    opFilmes = scanner.nextInt();

                    switch (opFilmes) {
                        //Se obtem o título e genero do filme e usa do objeto Manager criado anteriormente para adicionar o filme à lista de filmes.
                        case 1:
                            System.out.println("Informe o título do filme:");
                            String titulo = scanner.nextLine(); // Consumir quebra de linha pendente
                            titulo = scanner.nextLine(); // Ler o título corretamente
                            System.out.println("Informe o gênero do filme:");
                            String genero = scanner.next();
                            manager.adicionarFilme(new Filme(titulo, genero));
                            break;
                        //Se pesquisa o filme via um dado título, e se o mesmo for identificado na lista de filmes via o objeto manager, o remove da lista.
                        case 2:
                            System.out.println("Informe o título do filme a ser removido:");
                            String tituloRemover = scanner.nextLine();// Consumir quebra de linha pendente
                            tituloRemover = scanner.nextLine();
                            Filme filmeRemover = manager.buscarFilme(tituloRemover);
                            if (filmeRemover != null) {
                                manager.deletarFilme(filmeRemover);
                            } else {
                                System.out.println("Filme não encontrado.");
                            }
                            break;
                        //O usuário informa um título e é informado se o mesmo se encontra na lista de filmes via o objeto Manager
                        case 3:
                            System.out.println("Informe o título do filme a ser procurado:");
                            String tituloProcurar = scanner.nextLine();// Consumir quebra de linha pendente
                            tituloProcurar = scanner.nextLine();
                            Filme filmeProcurado = manager.buscarFilme(tituloProcurar);
                            if (filmeProcurado != null) {
                                System.out.println("Filme encontrado:");
                                System.out.println(filmeProcurado);
                            } else {
                                System.out.println("Filme não encontrado.");
                            }
                            break;
                        //Submenu de listagem de filmes, com Listagens padrão, por Título e por Gênero usando o objeto manager.
                        case 4:
                            System.out.println("////////LISTAR FILMES//////// ");
                            Integer opListaFilmes;
                            System.out.println("1-Listar");
                            System.out.println("2-Listar por Titulo");
                            System.out.println("3-Listar por Genero");
                            System.out.println("4-Voltar ao Inicio");
                            opListaFilmes = scanner.nextInt();

                            switch (opListaFilmes){
                                case 1:
                                    System.out.println("Listando...");
                                    manager.listarFilmes();
                                    break;
                                case 2:
                                    System.out.println("Listando por titulo...");
                                    manager.listarFilmesOrdenadosPorTitulo();
                                    break;
                                case 3:
                                    System.out.println("Listando por genero...");
                                    manager.listarFilmesOrdenadosPorGenero();
                                    break;
                                default:
                                    break;
                            }
                            break;
                        default:
                            break;
                    }
                    break;
                case 2:
                    //Submenu de Sessões, com as opções para Adicionar, Remover, Pesquisar e Listar Sessões
                    System.out.println("//////////////SESSÕES//////////////");
                    Integer opSessao;
                    System.out.println("1-Adicionar Sessão");
                    System.out.println("2-Remover Sessão");
                    System.out.println("3-Pesquisar Sessão");
                    System.out.println("4-Listar Sessões");
                    System.out.println("5-Voltar");
                    opSessao = scanner.nextInt();
                    switch (opSessao) {
                        case 1:
                            //Pega um titulo e pesquisa na lista de filme. Se achar o filme, pede em sequência os componentes de uma sessão(Horário, 3D, Dublado e Sala) e marca a sessão.
                            System.out.println("Informe o nome do filme:");
                            String titulo = scanner.nextLine();// Consumir quebra de linha pendente
                            titulo = scanner.nextLine();
                            Filme filme = manager.buscarFilme(titulo);
                            if (filme != null) {
                                System.out.println("Informe o horário da sessão (HH:MM):");
                                String horario = scanner.next();
                                System.out.println("A sessão é em 3D (true/false):");
                                boolean tipo3D = scanner.nextBoolean();
                                System.out.println("A sessão é dublada (true/false):");
                                boolean tipoDublado = scanner.nextBoolean();
                                System.out.println("Informe o número da sala da sessão:");
                                int sala = scanner.nextInt();
                                manager.adicionarSessao(new Sessao(filme, horario, tipo3D, tipoDublado, sala));
                            } else {
                                System.out.println("Filme não encontrado.");
                            }
                            break;
                        case 2:
                            //A partir de um ID informado pelo usuário, se pesquisa a lista de sessões e deleta uma sessão se for encontrada uma sessão com o mesmo ID.
                            System.out.println("Informe o ID da sessão a ser removido:");
                            int IDSessaoRemover = scanner.nextInt();
                            Boolean sessaoRemover = manager.deletarSessao(IDSessaoRemover);
                            if (sessaoRemover != true) {
                                System.out.println("Sessao deletada com sucesso.");
                            } else {
                                System.out.println("Sessao não encontrado.");
                            }
                            break;
                        case 3:
                            //É informado um ID pelo usuário e, se encontrado na lista, informa a sessão.
                            System.out.println("Pesquisar sessao, informe o ID da sessao:");
                            int IDSessaoPesquisar = scanner.nextInt();
                            Sessao sessaoPesquisar = manager.buscarSessao(IDSessaoPesquisar);
                            break;
                        case 4:
                            //Submenu de listagem de sessões
                            System.out.println("////////LISTAR SESSÕES//////// ");
                            Integer opListaSessao;
                            System.out.println("1-Listar");
                            System.out.println("2-Listar por ID");
                            System.out.println("3-Listar por Titulo do Filme");
                            System.out.println("4-Listar por Sala");
                            System.out.println("5-Voltar ao Inicio");
                            opListaSessao = scanner.nextInt();

                            switch (opListaSessao){
                                    //Imprime a lista de sessões marcada de forma padrão, ordenada via ID, Titulo do Filme ou Sala
                                case 1:
                                    System.out.println("Listando...");
                                    manager.listarSessoes();
                                    break;
                                case 2:
                                    System.out.println("Listando por ID...");
                                    manager.listarSessoesOrdenadasPorId();
                                    break;
                                case 3:
                                    System.out.println("Listando por titulo do filme...");
                                    manager.listarSessoesOrdenadasPorTituloFilme();
                                    break;
                                case 4:
                                    System.out.println("Listando por sala...");
                                    manager.listarSessoesOrdenadasPorSala();
                                    break;
                                default:
                                    break;
                            }
                            break;
                        default:
                            break;
                    }
                    break;
                case 3:
                    //Submenu de Vendas, com as opções de realizar a venda de um ingresso, Valores e listar todas as transações.
                    System.out.println("//////////////VENDAS//////////////");
                    Integer opIngresso;
                    System.out.println("1-Vender Ingresso");
                    System.out.println("2-Valor Arrecado");
                    System.out.println("3-Listar Transações");
                    System.out.println("4-Voltar");
                    opIngresso = scanner.nextInt();
                    switch (opIngresso) {
                        case 1:
                            //Informando a quantidade de ingressos, se cria uma lista para os ingressos.
                            System.out.println("Quantos ingressos deseja comprar?");
                            int quantidade = scanner.nextInt();
                            scanner.nextLine(); // Limpar o buffer do scanner

                            List<Ingresso> ingressos = new ArrayList<>();
                            //Pesquisa um CPF informado no sistema para confirmar se é a primeira vez do cliente no cinema. Se é, o registra.

                            System.out.println("Informe o seu CPF:");
                            String cpf = scanner.nextLine();
                            Cliente cliente = manager.buscarCliente(cpf);

                            if (cliente == null) {
                                System.out.println("Informe o seu nome:");
                                String nome = scanner.nextLine();
                                System.out.println("Informe a sua idade:");
                                int idade = scanner.nextInt();
                                cliente = new Cliente(cpf, nome, idade);
                                manager.adicionarCliente(cliente);
                                scanner.nextLine();
                            }
                            //Para a quantidade de ingressos requesitadas, faz o display dos filmes, sessões e assentos disponíveis, e se será meia entrada ou não.

                            for (int i = 0; i < quantidade; i++) {
                                System.out.println("Ingresso " + (i + 1));

                                System.out.println("Filmes disponíveis:");
                                manager.listarFilmes();
                                System.out.println("Informe o título do filme:");
                                if (i > 0){
                                    scanner.nextLine();
                                }
                                String tituloFilme = scanner.nextLine();

                                Filme filme = manager.buscarFilme(tituloFilme);

                                System.out.println("Sessões disponíveis para o filme " + tituloFilme + ":");
                                manager.listarSessoesPorFilme(tituloFilme);
                                System.out.println("Informe o ID da sessão:");
                                int idSessao = scanner.nextInt();
                                scanner.nextLine(); // Limpar o buffer do scanner
                                Sessao sessao = manager.buscarSessao(idSessao);
                                int assento;
                                while (true){
                                    System.out.println("Assentos disponiveis:");
                                    sessao.mostrarAssentosDisponiveis();
                                    System.out.println("Escolha um assento:");
                                    assento = scanner.nextInt();
                                    if (sessao.getAssentosDisponiveis().contains(assento)){
                                        break;
                                    }
                                    System.out.println("Assento errado, informe novamente um assento.");
                                }

                                System.out.println("O ingresso será meia? (true/false) ");
                                Boolean meia = scanner.nextBoolean();

                                //Se as respostas forem válidas, registra um novo ingresso e tira o assento escolhido da lista.

                                if (cliente != null && filme != null && sessao != null) {
                                    System.out.println("Ingresso vendido com sucesso para " + cliente.getNome());
                                    ingressos.add(new Ingresso(sessao, assento, meia));
                                    sessao.ocuparAssento(assento);
                                } else {
                                    System.out.println("Erro ao vender ingresso. Verifique os dados informados.");
                                }
                            }
                            //Cria um objeto transação com os dados de Ingresso, Cliente, e o horário da transação, salvando esta numa lista.

                            String horario = manager.obterHorarioAtual();
                            Transacao transacao = new Transacao(ingressos, cliente, horario);

                            System.out.println("Valor total em R$ " + transacao.getValorTotal());

                            manager.adicionarTransacao(transacao);
                            break;
                        case 2:
                            //Lista o total arrecadado com as vendas.
                            System.out.println("O valor arrecado com ingressos é igual a R$" + manager.somarValoresTotaisTransacoes());
                            break;
                        case 3:
                            //Submenu de Listagem de Transações
                            System.out.println("////////LISTAR TRANSAÇÕES//////// ");
                            Integer opListaIngresso;
                            System.out.println("1-Listar");
                            System.out.println("2-Listar por Data");
                            System.out.println("3-Listar por ID");
                            System.out.println("4-Listar por Valores");
                            System.out.println("5-Voltar ao Inicio");
                            opListaIngresso = scanner.nextInt();

                            switch (opListaIngresso){
                                    //Lista de forma padrão ou ordenada via Data, ID ou Valores.
                                case 1:
                                    System.out.println("Listando...");
                                    manager.listarTransacoes();
                                    break;
                                case 2:
                                    System.out.println("Listando por data...");
                                    manager.listarTransacoesOrdenadasPorData();
                                    break;
                                case 3:
                                    System.out.println("Listando por ID...");
                                    manager.listarTransacoesOrdenadasPorIdTransacao();
                                    break;
                                case 4:
                                    System.out.println("Listando por valores...");
                                    manager.listarTransacoesOrdenadasPorValorTotal();
                                default:
                                    break;
                            }
                        default:
                            break;
                    }
                    break;
                case 4:
                    //Submenu de Clientes, com as opções de Adicionar, Remover, Pesquisar e Listar Clientes
                    System.out.println("///////////////////CLIENTES////////////////");
                    Integer opClientes;
                    System.out.println("1-Adicionar Cliente");
                    System.out.println("2-Remover Cliente");
                    System.out.println("3-Pesquisar Cliente");
                    System.out.println("4-Listar Clientes");
                    System.out.println("5-Voltar");
                    opClientes = scanner.nextInt();
                    switch (opClientes) {
    
                        case 1:

                            //Informando o nome, CPF e Idade, se cadastra o cliente na lista de clientes usando o objeto manager.
                            System.out.println("Informe o nome do cliente:");
                            String nomeCliente = scanner.nextLine();
                            nomeCliente = scanner.nextLine();
                            System.out.println("Informe o CPF do cliente:");
                            String cpfCliente = scanner.next();
                            System.out.println("Informe a idade do cliente:");
                            int idadeCliente = scanner.nextInt();
                            manager.adicionarCliente(new Cliente(nomeCliente, cpfCliente, idadeCliente));
                            break;
                        case 2:
                            //Usando um CPF, se pesquisa na lista de clientes por um correspondente e remove se encontrado.
                            System.out.println("Informe o CPF do cliente a ser removido:");
                            String cpfClienteRemover = scanner.next();
                            manager.removerCliente(cpfClienteRemover);
                            break;
                        case 3:
                            //Usando um CPF, se faz uma pesquisa na lista de clientes por um cliente com o CPF informado.
                            System.out.println("Informe o CPF do cliente a ser pesquisado:");
                            String cpfClientePesquisar = scanner.next();
                            Cliente cliente = manager.buscarCliente(cpfClientePesquisar);
                            break;
                        case 4:
                            //Submenu para listagem de clientes.
                            System.out.println("//////LISTAR CLIENTES//////");
                            Integer opListaClientes;
                            System.out.println("1-Listar");
                            System.out.println("2-Listar por Nome");
                            System.out.println("3-Listar por CPF");
                            System.out.println("4-Listar por Idade");
                            System.out.println("5-Voltar ao Inicio");
                            opListaClientes = scanner.nextInt();

                            switch (opListaClientes){
                                    //Opções para Listagem dos Clientes, podendo ser de forma padrão, ordenado pelo Nome, CPF ou Idade
                                case 1:
                                    System.out.println("Listando...");
                                    manager.listarClientes();
                                    break;
                                case 2:
                                    System.out.println("Listando por nome...");
                                    manager.listarClientesOrdenadosPorNome();
                                    break;
                                case 3:
                                    System.out.println("Listando por CPF...");
                                    manager.listarClientesOrdenadosPorCpf();
                                    break;
                                case 4:
                                    System.out.println("Listando por idade...");
                                    manager.listarClientesOrdenadosPorIdade();
                                    break;
                                default:
                                    break;
                            }
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
        //Ao se encerrar o programa, o objeto manager pega as três listas(Sessões, Filmes e Clientes) e salva nos respectivos arquivos de texto.
        manager.salvarSessoes();
        manager.salvarFilmes();
        manager.salvarClientes();
        manager.salvarTransacoes();
    }
}
