package jogo_da_velha.arquivos;

public class JogoModel {

    private char[] tabuleiro;
    private char jogadorAtual;

    public JogoModel() {

        tabuleiro = new char[9];
        jogadorAtual = 'X';

        for (int i = 0; i < 9; i++) {
            tabuleiro[i] = ' ';
        }
    }

    public boolean fazerJogada(int posicao) {

        if (tabuleiro[posicao] == ' ') {
            tabuleiro[posicao] = jogadorAtual;
            return true;
        }

        return false;
    }

    public boolean verificarVitoria() {

        if (tabuleiro[0] == jogadorAtual &&
            tabuleiro[1] == jogadorAtual &&
            tabuleiro[2] == jogadorAtual) {
            return true;
        }

        if (tabuleiro[3] == jogadorAtual &&
            tabuleiro[4] == jogadorAtual &&
            tabuleiro[5] == jogadorAtual) {
            return true;
        }

        if (tabuleiro[6] == jogadorAtual &&
            tabuleiro[7] == jogadorAtual &&
            tabuleiro[8] == jogadorAtual) {
            return true;
        }

        if (tabuleiro[0] == jogadorAtual &&
            tabuleiro[3] == jogadorAtual &&
            tabuleiro[6] == jogadorAtual) {
            return true;
        }

        if (tabuleiro[1] == jogadorAtual &&
            tabuleiro[4] == jogadorAtual &&
            tabuleiro[7] == jogadorAtual) {
            return true;
        }

        if (tabuleiro[2] == jogadorAtual &&
            tabuleiro[5] == jogadorAtual &&
            tabuleiro[8] == jogadorAtual) {
            return true;
        }

        if (tabuleiro[0] == jogadorAtual &&
            tabuleiro[4] == jogadorAtual &&
            tabuleiro[8] == jogadorAtual) {
            return true;
        }

        if (tabuleiro[2] == jogadorAtual &&
            tabuleiro[4] == jogadorAtual &&
            tabuleiro[6] == jogadorAtual) {
            return true;
        }

        return false;
    }

    public boolean verificarEmpate() {

        for (int i = 0; i < 9; i++) {

            if (tabuleiro[i] == ' ') {
                return false;
            }
        }

        return true;
    }

    public void trocarJogador() {

        if (jogadorAtual == 'X') {
            jogadorAtual = 'O';
        } else {
            jogadorAtual = 'X';
        }
    }

    public char getJogadorAtual() {
        return jogadorAtual;
    }

    public void reiniciar() {

        for (int i = 0; i < 9; i++) {
            tabuleiro[i] = ' ';
        }

        jogadorAtual = 'X';
    }
}