package ralseiclickergame;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaJuego extends JFrame {
    public VentanaJuego(boolean cargarPartida) {
        // el booleano se usa para decidir si se debe crear una partida nueva (false) o cargar una existente (true)
        setTitle("Clicker Ralsei");
        setSize(975, 720);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Musica musica = new Musica();
        musica.reproducirMusica("src/resources/MyCastleTown.wav");

        PanelJuego panel = new PanelJuego(cargarPartida);
        setContentPane(panel);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //llama al metodo de GuardarProgreso para guardar la partida
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