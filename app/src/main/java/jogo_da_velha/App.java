package jogo_da_velha;

import java.awt.*;
import java.awt.event.*;


public class App  extends Frame implements ActionListener {
    // Vetor que armazena os 9 botões do tabuleiro
    private Button[] casas = new Button[9];
    private char jogadorAtual = 'X';
    private boolean jogoTerminado = false;
    private Label mensagem;
 
    //teste engraçadinho

    // ==========================================
    // CONSTRUTOR
    // ==========================================

    public App() {

        // Configurações da janela
        setTitle("Jogo da Velha");
        setSize(500, 600);
        setLayout(new BorderLayout());


        // ==========================================
        // PARTE SUPERIOR
        // ==========================================

        mensagem = new Label("Vez do jogador X", Label.CENTER);

        mensagem.setFont(
            new Font("Arial", Font.BOLD, 24)
        );

        add(mensagem, BorderLayout.NORTH);


        // ==========================================
        // TABULEIRO
        // ==========================================

        Panel tabuleiro = new Panel();

        // Cria uma grade 3x3
        tabuleiro.setLayout(
            new GridLayout(3, 3, 5, 5)
        );


        // Cria as 9 casas
        for (int i = 0; i < 9; i++) {

            casas[i] = new Button();

            casas[i].setFont(
                new Font("Arial", Font.BOLD, 60)
            );

            // Define o botão como ouvinte de eventos
            casas[i].addActionListener(this);

            // Adiciona o botão ao tabuleiro
            tabuleiro.add(casas[i]);
        }


        add(tabuleiro, BorderLayout.CENTER);


        // ==========================================
        // PARTE INFERIOR
        // ==========================================

        Panel painelInferior = new Panel();

        painelInferior.setLayout(
            new FlowLayout()
        );


        // Botão de reiniciar
        Button reiniciar = new Button("Reiniciar");

        reiniciar.setFont(
            new Font("Arial", Font.BOLD, 18)
        );

        reiniciar.addActionListener(
            new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    reiniciarJogo();
                }
            }
        );


        // Botão de sair
        Button sair = new Button("Sair");

        sair.setFont(
            new Font("Arial", Font.BOLD, 18)
        );

        sair.addActionListener(
            new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            }
        );


        painelInferior.add(reiniciar);
        painelInferior.add(sair);

        add(painelInferior, BorderLayout.SOUTH);


        // ==========================================
        // MOSTRAR A JANELA
        // ==========================================

        setVisible(true);

        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }


    // ==========================================
    // EVENTO DE CLIQUE NAS CASAS
    // ==========================================

    @Override
    public void actionPerformed(ActionEvent e) {

        // Se o jogo terminou, não permite novas jogadas
        if (jogoTerminado) {
            return;
        }


        // Procura qual casa foi clicada
        for (int i = 0; i < 9; i++) {

            if (e.getSource() == casas[i]) {

                // Verifica se a casa já foi utilizada
                if (!casas[i].getLabel().equals("")) {
                    return;
                }


                // Coloca X ou O na casa
                casas[i].setLabel(
                    String.valueOf(jogadorAtual)
                );


                // ==========================================
                // VERIFICA SE O JOGADOR GANHOU
                // ==========================================

                if (verificarVitoria()) {

                    mensagem.setText(
                        "Jogador " + jogadorAtual + " venceu!"
                    );

                    jogoTerminado = true;

                    return;
                }


                // ==========================================
                // VERIFICA EMPATE
                // ==========================================

                if (verificarEmpate()) {

                    mensagem.setText("Empate!");

                    jogoTerminado = true;

                    return;
                }


                // ==========================================
                // TROCA O JOGADOR
                // ==========================================

                trocarJogador();

                break;
            }
        }
    }


    // ==========================================
    // TROCAR JOGADOR
    // ==========================================

    private void trocarJogador() {

        if (jogadorAtual == 'X') {

            jogadorAtual = 'O';

        } else {

            jogadorAtual = 'X';
        }


        mensagem.setText(
            "Vez do jogador " + jogadorAtual
        );
    }


    // ==========================================
    // VERIFICAR VITÓRIA
    // ==========================================

    private boolean verificarVitoria() {

        /*
         * O tabuleiro é organizado assim:
         *
         *  0 | 1 | 2
         * ---+---+---
         *  3 | 4 | 5
         * ---+---+---
         *  6 | 7 | 8
         *
         * Estas são todas as possibilidades
         * de vitória.
         */

        int[][] combinacoes = {

            // Linhas
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},

            // Colunas
            {0, 3, 6},
            {1, 4, 7},
            {2, 5, 8},

            // Diagonais
            {0, 4, 8},
            {2, 4, 6}
        };


        // Percorre todas as combinações
        for (int[] combinacao : combinacoes) {

            String primeira =
                casas[combinacao[0]].getLabel();

            String segunda =
                casas[combinacao[1]].getLabel();

            String terceira =
                casas[combinacao[2]].getLabel();


            /*
             * Verifica se:
             *
             * 1. A primeira casa não está vazia
             * 2. A primeira é igual à segunda
             * 3. A segunda é igual à terceira
             */

            if (!primeira.equals("")
                    && primeira.equals(segunda)
                    && segunda.equals(terceira)) {

                return true;
            }
        }


        // Nenhuma combinação vencedora foi encontrada
        return false;
    }


    // ==========================================
    // VERIFICAR EMPATE
    // ==========================================

    private boolean verificarEmpate() {

        // Percorre todas as casas
        for (int i = 0; i < 9; i++) {

            // Se encontrar uma casa vazia,
            // significa que ainda é possível jogar
            if (casas[i].getLabel().equals("")) {

                return false;
            }
        }


        // Todas as casas estão preenchidas
        return true;
    }


    // ==========================================
    // REINICIAR JOGO
    // ==========================================

    private void reiniciarJogo() {

        // Limpa todas as casas
        for (int i = 0; i < 9; i++) {

            casas[i].setLabel("");
        }


        // O jogador X começa novamente
        jogadorAtual = 'X';


        // O jogo volta a ficar ativo
        jogoTerminado = false;


        // Atualiza a mensagem
        mensagem.setText(
            "Vez do jogador X"
        );
    }


    // ==========================================
    // MÉTODO MAIN
    // ==========================================

    public static void main(String[] args) {

        // Cria o jogo
        new App();
    }
}
