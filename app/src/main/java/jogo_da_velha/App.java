package jogo_da_velha;

import jogo_da_velha.arquivos.JogoView;

import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new JogoView());

    }
}
