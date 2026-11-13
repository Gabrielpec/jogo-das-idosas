package jogo_da_velha.arquivos;

import javax.swing.*;
import java.awt.*;

public class JogoView extends JFrame {

    private JButton[] botoes;
    private JButton reiniciar;
    private JLabel mensagem;

    private JogoModel model;

    public JogoView() {

        model = new JogoModel();

        setTitle("Jogo da Velha");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        mensagem = new JLabel("Vez do jogador X", SwingConstants.CENTER);
        mensagem.setFont(new Font("Arial", Font.PLAIN, 16));
        add(mensagem, BorderLayout.NORTH);

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(3, 3));

        botoes = new JButton[9];

        for (int i = 0; i < 9; i++) {

            botoes[i] = new JButton();
            botoes[i].setFont(new Font("Arial", Font.BOLD, 40));
            botoes[i].setFocusPainted(false);

            final int posicao = i;

            botoes[i].addActionListener(e -> jogar(posicao));

            painel.add(botoes[i]);
        }

        add(painel, BorderLayout.CENTER);

        reiniciar = new JButton("Reiniciar");
        reiniciar.setFont(new Font("Arial", Font.PLAIN, 16));

        reiniciar.addActionListener(e -> reiniciarJogo());

        add(reiniciar, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void jogar(int posicao) {

        char jogador = model.getJogadorAtual();

        if (model.fazerJogada(posicao)) {

            botoes[posicao].setText(String.valueOf(jogador));

            if (model.verificarVitoria()) {

                mensagem.setText("Jogador " + jogador + " venceu!");

                for (int i = 0; i < botoes.length; i++) {
                    botoes[i].setEnabled(false);
                }

                return;
            }

            if (model.verificarEmpate()) {

                mensagem.setText("Empate!");
                return;
            }

            model.trocarJogador();

            mensagem.setText("Vez do jogador " +
                    model.getJogadorAtual());
        }
    }

    public void reiniciarJogo() {

        model.reiniciar();

        for (int i = 0; i < 9; i++) {
            botoes[i].setText("");
            botoes[i].setEnabled(true);
        }

        mensagem.setText("Vez do jogador X");
    }
}
