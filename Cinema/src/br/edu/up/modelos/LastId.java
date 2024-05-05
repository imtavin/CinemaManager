package br.edu.up.modelos;

public class LastId {
    //Setando o ultimo ID de sessao
    private static int lastIdSessao = 0;
    private static int lastIdFilme = 0;

    public static int getLastIdSessao() {
        return lastIdSessao;
    }

    public static void setLastIdSessao(int lastIdSessao) {
        LastId.lastIdSessao = lastIdSessao;
    }

    public static int getLastIdFilme() {
        return lastIdFilme;
    }

    public static void setLastIdFilme(int lastIdFilme) {
        LastId.lastIdFilme = lastIdFilme;
    }
}
