package ralseiclickergame;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class PaginaInicio extends JFrame {

    private JPanel panelPaginaInicio;
    private JLabel logoUTP;
    private ImageIcon logoUTPImagen = new ImageIcon("src/resources/logoUTP.png");
    private JLabel logoFISC;
    private JButton nuevaPartidaButton;
    private JButton continuarButton;
    private JLabel labelUniversidad;
    private ImageIcon logoFISCImagen = new ImageIcon("src/resources/logoFISC.png");
    private Musica musica1;

    public PaginaInicio()
    {
        musica1 = new Musica();
        musica1.reproducirMusica("src/resources/School.wav");

        setTitle("Semestral Programación");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(975, 720);
        setLocationRelativeTo(null);

        setResizable(false);
        setVisible(true);

        logoUTPImagen = new ImageIcon( logoUTPImagen.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH) );
        logoUTP.setIcon(logoUTPImagen);
        logoFISCImagen = new ImageIcon( logoFISCImagen.getImage().getScaledInstance(108, 108, Image.SCALE_SMOOTH) );
        logoFISC.setIcon(logoFISCImagen);

        nuevaPartidaButton.addActionListener(e -> {
            musica1.detenerMusica(); // Detiene la música
            // aquí se define qué pasa cuando se hace clic en nueva partida
            VentanaJuego ventanaJuego = new VentanaJuego(false); // false significa nueva partida
            ventanaJuego.setVisible(true); // Hace visible la ventana del juego
            this.dispose(); // cierra la ventana actual (PaginaInicio)
        });

        // logica para el boton d3 continuar
        continuarButton.addActionListener(e -> {
            musica1.detenerMusica(); // También la detiene
            // Crea una nueva instancia de VentanaJuego, indicando que SÍ es una partida cargada.
            VentanaJuego ventanaJuego = new VentanaJuego(true); // true significa cargar partida
            ventanaJuego.setVisible(true);
            this.dispose();
        });


        try {
            File archivoPartida = new File("partida.ser"); // La ruta o nombre de tu archivo de guardado
            if (!archivoPartida.exists()) {
                continuarButton.setEnabled(false); // Deshabilita el botón
                continuarButton.setText("Continuar (No hay partida)");
                continuarButton.setForeground(Color.GRAY);
            }

            File fuenteEstilo = new File("src/resources/determination.ttf");
            Font fuente = Font.createFont(Font.TRUETYPE_FONT, fuenteEstilo).deriveFont(30f);
            this.asignarFuenteParaLabelenPanel(this.panelPaginaInicio, fuente);

            this.nuevaPartidaButton.setFont(fuente);
            this.continuarButton.setFont(fuente);

            // Ahora para tamaño en especifico
            this.labelUniversidad.setFont(this.labelUniversidad.getFont().deriveFont(45f));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        this.setContentPane(this.panelPaginaInicio);

        // Los siguientes cambios de tamaño es un apaño para un bug raro que pone blanco la ventana y se arregla si se "refresca"
        // cambiando el borde un poco, Verdaderamente los apaños de un game developer JAJAJAJJA XD
        // ¿Es estupido? Sí, ¿pero funciona?, entonces no es estupido xD
        this.setSize(975, 723);
        this.setSize(975, 720);
    }


    private void asignarFuenteParaLabelenPanel(Container container, Font font) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof JLabel) {
                comp.setFont(font);
            } else if (comp instanceof Container) {
                // Recursivamente llama a todos los contenedores
                asignarFuenteParaLabelenPanel((Container) comp, font);
            }
        }
    }
}
