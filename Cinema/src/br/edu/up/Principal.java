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

        while (opcao != 6) {
            System.out.println("////////////////////GERENCIAMENTO DE CINEMA////////////////");
            System.out.println("1-Filmes");
            System.out.println("2-Sessões");
            System.out.println("3-Ingresso");
            System.out.println("4-Clientes");
            System.out.println("5-Transações");
            System.out.println("6-Encerrar Programa");
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
                            String titulo = scanner.next();
                            System.out.println("Informe o gênero do filme:");
                            String genero = scanner.next();
                            manager.adicionarFilme(new Filme(titulo, genero));
                            break;
                        //Remover Filme
                        case 2:
                            System.out.println("Informe o título do filme a ser removido:");
                            String tituloRemover = scanner.next();
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
                            String tituloProcurar = scanner.next();
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
                            System.out.println("Lista de Filmes:");
                            //manager.listarFilmes();
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
                                manager.adicionarSessao(new Sessao(filme, horario, tipo3D, tipoDublado));
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
                            //
                            break;
                        case 4:
                            System.out.println("Lista de Sessões:");
                            //manager.listarSessoes();
                            break;
                        default:
                            break;
                    }
                    break;
                case 3:
                    System.out.println("//////////////INGRESSOS//////////////");
                    Integer opIngresso;
                    System.out.println("1-Vender Ingresso");
                    System.out.println("2-Listar Ingressos");
                    System.out.println("3-Voltar");
                    opIngresso = scanner.nextInt();
                    switch (opIngresso) {
                        case 1:
                            System.out.println("Informe o ID da sessão:");
                            break;
                        case 2:
                            System.out.println("Listar Ingressos:");
                            break;
                        default:
                            break;
                    }
                    break;
                case 4:
                    System.out.println("///////////////////CLIENTES////////////////");
                    Integer opClientes;
                    System.out.println("1-Adicionar Cliente");
                    System.out.println("2-Listar Clientes");
                    System.out.println("3-Voltar");
                    opClientes = scanner.nextInt();
                    switch (opClientes) {
                        case 1:
                            System.out.println("Informe o nome do cliente:");
                            String nomeCliente = scanner.next();
                            System.out.println("Informe o CPF do cliente:");
                            String cpfCliente = scanner.next();
                            System.out.println("Informe a idade do cliente:");
                            int idadeCliente = scanner.nextInt();
                            manager.adicionarCliente(new Cliente(nomeCliente, cpfCliente, idadeCliente));
                            break;
                        case 2:
                            System.out.println("Lista de Clientes:");
                            //manager.listarClientes();
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
    }
}