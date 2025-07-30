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
    private JLabel labelFacultad;
    private ImageIcon logoFISCImagen = new ImageIcon("src/resources/logoFISC.png");

    public PaginaInicio()
    {
        setTitle("Clicker Ralsei");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(975, 720);
        setLocationRelativeTo(null);

        setResizable(false);
        setVisible(true);

        logoUTPImagen = new ImageIcon( logoUTPImagen.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH) );
        logoUTP.setIcon(logoUTPImagen);
        logoFISCImagen = new ImageIcon( logoFISCImagen.getImage().getScaledInstance(108, 108, Image.SCALE_SMOOTH) );
        logoFISC.setIcon(logoFISCImagen);
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

    public static void main(String[] args) {
        PaginaInicio paginaInicio = new PaginaInicio();

        try {
            File fuenteEstilo = new File("src/resources/determination.ttf");
            Font fuente = Font.createFont(Font.TRUETYPE_FONT, fuenteEstilo).deriveFont(30f);
            paginaInicio.asignarFuenteParaLabelenPanel(paginaInicio.panelPaginaInicio, fuente);

            paginaInicio.nuevaPartidaButton.setFont(fuente);
            paginaInicio.continuarButton.setFont(fuente);

            // Ahora para tamaño en especifico
            paginaInicio.labelUniversidad.setFont(paginaInicio.labelUniversidad.getFont().deriveFont(45f));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        paginaInicio.setContentPane(paginaInicio.panelPaginaInicio);

        // Los siguientes cambios de tamaño es un apaño para un bug raro que pone blanco la ventana y se arregla si se "refresca"
        // cambiando el borde un poco, Verdaderamente los apaños de un game developer JAJAJAJJA XD
        // ¿Es estupido? Sí, ¿pero funciona?, entonces no es estupido xD
        paginaInicio.setSize(975, 723);
        paginaInicio.setSize(975, 720);
    }
}
