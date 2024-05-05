package br.edu.up;

import br.edu.up.modelos.FileManager;
import br.edu.up.modelos.Filme;
import java.io.IOException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws IOException {
        FileManager manager = new FileManager();
        Scanner scanner = new Scanner(System.in);
        Integer opcao = 1;


        while(opcao != 5){
            System.out.println("////////////////////GERENCIAMENTO DE CINEMA////////////////");
            System.out.println("1-Filmes");
            System.out.println("2-Sessões");
            System.out.println("3-Ingresso");
            System.out.println("4-Clientes");
            System.out.println("5-Encerrar Programa.");
            System.out.println("///////////////////////////////////////////////////////////");
            opcao = scanner.nextInt();

            switch (opcao){
                case 1:
                    System.out.println("//////////////FILMES//////////////");
                    Integer opFilmes;
                    System.out.println("1-Adicionar Filme");
                    System.out.println("2-Remover Filme");
                    System.out.println("3-Procurar Filme");
                    System.out.println("4-Voltar");
                    opFilmes = scanner.nextInt();

                    if(opFilmes == 1){
                        System.out.println("test1");
                        break;
                    }
                    if(opFilmes == 2){
                        System.out.println("test2");
                        break;
                    }
                    if(opFilmes == 3){
                        System.out.println("test3");
                        break;
                    }else{
                        break;
                    }
                case 2:
                    System.out.println("//////////////Sessão//////////////");
                    Integer opSessao;
                    System.out.println("1-Adicionar Sessão");
                    System.out.println("2-Voltar");
                    opSessao = scanner.nextInt();
                    if(opSessao == 1){
                        System.out.println("TesteSessão");
                        break;
                    }else{
                        break;
                    }
                case 3:
                    System.out.println("//////////////Sessão//////////////");
                    Integer opIngresso;
                    System.out.println("1-Inteira");
                    System.out.println("2-Meia");
                    System.out.println("3-Voltar");
                    opIngresso = scanner.nextInt();
                    if(opIngresso == 1){
                        System.out.println("Inteira");
                        break;
                    }
                    if(opIngresso == 2){
                        System.out.println("Meia");
                        break;
                    }else{
                        break;
                    }
                case 4:
                    System.out.println("///////////////////CLIENTES////////////////");
                default:
                    break;






            }


        }


    }

}
