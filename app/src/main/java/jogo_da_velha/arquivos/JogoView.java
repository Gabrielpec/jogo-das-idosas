package jogo_da_velha.arquivos;

import java.awt.*;
import java.awt.event.*;

public class JogoView extends Frame {

    private Button[] botoes;
    private Button reiniciar;
    private Label mensagem;

    private JogoModel model;

    public JogoView() {

        model = new JogoModel();

        setTitle("Jogo da Velha");
        setSize(400, 500);
        setLayout(new BorderLayout());

        mensagem = new Label("Vez do jogador X", Label.CENTER);
        add(mensagem, BorderLayout.NORTH);

        Panel painel = new Panel();
        painel.setLayout(new GridLayout(3, 3));

        botoes = new Button[9];

        for (int i = 0; i < 9; i++) {

            botoes[i] = new Button();
            botoes[i].setFont(new Font("Arial", Font.BOLD, 40));

            final int posicao = i;

            botoes[i].addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    jogar(posicao);
                }
            });

            painel.add(botoes[i]);
        }

        add(painel, BorderLayout.CENTER);

        reiniciar = new Button("Reiniciar");

        reiniciar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                reiniciarJogo();
            }
        });

        add(reiniciar, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    public void jogar(int posicao) {

        char jogador = model.getJogadorAtual();

        if (model.fazerJogada(posicao)) {

            botoes[posicao].setLabel(String.valueOf(jogador));

            if (model.verificarVitoria()) {

                mensagem.setText("Jogador " + jogador + " venceu!");
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
            botoes[i].setLabel("");
        }

        mensagem.setText("Vez do jogador X");
    }
}