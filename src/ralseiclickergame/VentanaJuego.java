package ralseiclickergame;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaJuego extends JFrame {
    public VentanaJuego(boolean cargarPartida) {
        setTitle("Ralsei Clicker - Menú Principal");
        setTitle("Clicker Ralsei");
        setSize(975, 720);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//        Musica musica = new Musica();
//        musica.reproducirMusica("src/resources/MyCastleTown.wav");

        PanelJuego panel = new PanelJuego(cargarPartida);
        setContentPane(panel);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                GuardarProgreso.guardarPartida(
                        panel.getContador().getPuntos(),
                        panel.getPersonaje().getRibbonCount(),
                        panel.getPersonaje().isJevilstailActivo(),
                        panel.getPersonaje().isDealmakerActivo()
                );
            }
        });

    }
}