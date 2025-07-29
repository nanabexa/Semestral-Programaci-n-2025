package ralseiclickergame;

import javax.swing.*;

public class VentanaJuego extends JFrame {
    public VentanaJuego() {
        setTitle("Clicker Ralsei");
        setSize(975, 720);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Musica musica = new Musica();
        musica.reproducirMusica("src/resources/MyCastleTown.wav");

        PanelJuego panel = new PanelJuego();
        setContentPane(panel);
    }
}