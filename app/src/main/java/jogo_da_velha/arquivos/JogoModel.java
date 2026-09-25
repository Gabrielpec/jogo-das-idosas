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
        int[][] vetores = {
                {0,1,2}, // horizontais
                {3,4,5},
                {6,7,8},
                {0,3,6},// verticais
                {1,4,7},
                {2,5,8},
                {0,4,8}, // diagonais
                {2,4,6}
        };

        for(int i=0;i<vetores.length;i++){
            int contador = 0;

            for(int j=0;j<vetores[i].length;j++){
                if(tabuleiro[vetores[i][j]] == jogadorAtual) contador++;
            }

            if(contador==3) return   true;
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