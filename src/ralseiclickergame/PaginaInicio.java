package ralseiclickergame;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class PaginaInicio extends JFrame {

    private JPanel panelPaginaInicio;

    public PaginaInicio()
    {
        setTitle("Clicker Ralsei");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(975, 720);
        setLocationRelativeTo(null);
        setBackground(new Color(15, 12, 25));
        setVisible(true);


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
        } catch (Exception e)
        {
            e.printStackTrace();
        }


        paginaInicio.setContentPane(paginaInicio.panelPaginaInicio);
    }
}
