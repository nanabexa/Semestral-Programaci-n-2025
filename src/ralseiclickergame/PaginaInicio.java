package ralseiclickergame;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class PaginaInicio extends JFrame {

    public PaginaInicio() {
        setTitle("Ralsei Clicker - Menú Principal");
        setSize(500, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        JLabel titulo = new JLabel("Menú Principal", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 30));

        JButton botonNuevaPartida = new JButton("Nueva Partida");
        JButton botonContinuarPartida = new JButton("Continuar Partida");

        botonNuevaPartida.addActionListener(e -> iniciarPartida(false));
        botonContinuarPartida.addActionListener(e -> iniciarPartida(true));

        File archivoPartida = new File("partida.ser");
        if (!archivoPartida.exists()) {
            botonContinuarPartida.setEnabled(false);
        }

        panel.add(titulo);
        panel.add(botonNuevaPartida);
        panel.add(botonContinuarPartida);

        add(panel);
    }

    private void iniciarPartida(boolean cargar) {
        dispose();

        Musica musica = new Musica();
        musica.reproducirMusica("src/resources/MyCastleTown.wav");

        VentanaJuego ventana = new VentanaJuego(cargar);
        ventana.setVisible(true);
    }
}
