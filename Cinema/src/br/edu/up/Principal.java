package br.edu.up;

import br.edu.up.modelos.Cliente;
import br.edu.up.modelos.FileManager;
import br.edu.up.modelos.Filme;
import br.edu.up.modelos.Sessao;

import java.io.IOException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws IOException {
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
                        //Adicionar Filme
                        case 1:
                            System.out.println("Informe o título do filme:");
                            String titulo = scanner.nextLine(); // Consumir quebra de linha pendente
                            titulo = scanner.nextLine(); // Ler o título corretamente
                            System.out.println("Informe o gênero do filme:");
                            String genero = scanner.next();
                            manager.adicionarFilme(new Filme(titulo, genero));
                            break;
                        //Remover Filme
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
                        //Procurar Filme
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
                        //Listar Filme
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
                            System.out.println("Informe o nome do filme:");
                            String titulo = scanner.next();
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
                            System.out.println("Pesquisar sessao, informe o ID da sessao:");
                            int IDSessaoPesquisar = scanner.nextInt();
                            Sessao sessaoPesquisar = manager.buscarSessao(IDSessaoPesquisar);
                            break;
                        case 4:
                            System.out.println("////////LISTAR SESSÕES//////// ");
                            Integer opListaSessao;
                            System.out.println("1-Listar");
                            System.out.println("2-Listar por ID");
                            System.out.println("3-Listar por Titulo do Filme");
                            System.out.println("4-Listar por Sala");
                            System.out.println("5-Voltar ao Inicio");
                            opListaSessao = scanner.nextInt();

                            switch (opListaSessao){
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
                    System.out.println("//////////////VENDAS//////////////");
                    Integer opIngresso;
                    System.out.println("1-Vender Ingresso");
                    System.out.println("2-Valores");
                    System.out.println("3-Listar Transações");
                    System.out.println("4-Voltar");
                    opIngresso = scanner.nextInt();
                    switch (opIngresso) {
                        case 1:
                            System.out.println("Informe o ID da sessão:");
                            break;
                        case 2:
                            System.out.println("Valores:");
                            break;
                        case 3:
                            System.out.println("////////LISTAR TRANSAÇÕES//////// ");
                            Integer opListaIngresso;
                            System.out.println("1-Listar");
                            System.out.println("2-Listar por Data");
                            System.out.println("3-Listar por ID");
                            System.out.println("4-Voltar ao Inicio");
                            opListaIngresso = scanner.nextInt();

                            switch (opListaIngresso){
                                case 1:
                                    System.out.println("Listando...");
                                    break;
                                case 2:
                                    System.out.println("Listando por data...");
                                    break;
                                case 3:
                                    System.out.println("Listando por ID...");
                                    break;
                                default:
                                    break;
                            }
                        default:
                            break;
                    }
                    break;
                case 4:
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
                            System.out.println("Informe o CPF do cliente a ser removido:");
                            String cpfClienteRemover = scanner.next();
                            manager.removerCliente(cpfClienteRemover);
                            break;
                        case 3:
                            System.out.println("Informe o CPF do cliente a ser pesquisado:");
                            String cpfClientePesquisar = scanner.next();
                            Cliente cliente = manager.buscarCliente(cpfClientePesquisar);
                            break;
                        case 4:
                            System.out.println("//////LISTAR CLIENTES//////");
                            Integer opListaClientes;
                            System.out.println("1-Listar");
                            System.out.println("2-Listar por Nome");
                            System.out.println("3-Listar por CPF");
                            System.out.println("4-Listar por Idade");
                            System.out.println("5-Voltar ao Inicio");
                            opListaClientes = scanner.nextInt();

                            switch (opListaClientes){
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
        manager.salvarSessoes();
        manager.salvarFilmes();
        manager.salvarClientes();
    }
}