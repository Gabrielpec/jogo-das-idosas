
package jogo_da_velha.arquivos;

import javax.swing.*;

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
        setLocationRelativeTo(null);

        mensagem = new JLabel("Vez do jogador X");
        mensagem.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel painel = new JPanel();
        painel.setLayout(new java.awt.GridLayout(3, 3));

        botoes = new JButton[9];

        for (int i = 0; i < 9; i++) {

            botoes[i] = new JButton();
            botoes[i].setFocusPainted(false);

            final int posicao = i;

            botoes[i].addActionListener(e -> jogar(posicao));

            painel.add(botoes[i]);
        }

        reiniciar = new JButton("Reiniciar");
        reiniciar.addActionListener(e -> reiniciarJogo());

        JPanel principal = new JPanel();
        principal.setLayout(new BoxLayout(principal, BoxLayout.Y_AXIS));

        principal.add(mensagem);
        principal.add(painel);
        principal.add(reiniciar);

        add(principal);

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

                for (int i = 0; i < botoes.length; i++) {
                    botoes[i].setEnabled(false);
                }

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
