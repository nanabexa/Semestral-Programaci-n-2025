package ralseiclickergame;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class PanelJuego extends JPanel {
    private Contador contador;
    private Personaje personaje;
    private Tienda tienda;

    private JLabel fondo;
    private JLabel borde;
    private JLabel titulo;

    public PanelJuego() {
        setLayout(null);

        Font font = null;
        Font fontGrande = null;
        // Fuente personalizada
        try {
            File fileFuente = new File("src/resources/determination.ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, fileFuente).deriveFont(Font.PLAIN, 40);
            fontGrande = font.deriveFont(Font.PLAIN, 60); // 50% más grande
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
            UIManager.put("Label.font", font);
            UIManager.put("Label.foreground", Color.WHITE);
        } catch (Exception e) {
            e.printStackTrace();
            font = new Font("Arial", Font.BOLD, 40); // fallback
            fontGrande = font.deriveFont(Font.PLAIN, 60);
        }

        try {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Image cursorImage = toolkit.getImage("src/resources/corazon.png");
            Cursor customCursor = toolkit.createCustomCursor(cursorImage, new Point(0, 0), "CustomCursor");
            setCursor(customCursor);
        } catch (Exception e) {
            e.printStackTrace();
        }
        contador = new Contador();
        personaje = new Personaje(contador,this);
        tienda = new Tienda(contador, personaje);

        titulo = new JLabel("Ralsei Clicker");
        titulo.setBounds(330, 20, 500, 70);
        titulo.setFont(fontGrande);
        titulo.setForeground(Color.WHITE);

        add(titulo);
        add(contador.getLabelPuntos());
        add(contador.getLabelValor());

        for (JLabel ribbon : personaje.getRibbons()) {
            add(ribbon);
        }

        add(personaje.getLabelImagen());
        add(personaje.getBoton());

        if (personaje.isJevilstailActivo()) add(personaje.getJevilstailLabel());
        if (personaje.isDealmakerActivo()) add(personaje.getDealmakerLabel());

        add(tienda.getTitulo());
        add(tienda.getItem1Label());
        add(tienda.getItem1Boton());
        add(tienda.getItem2Label());
        add(tienda.getItem2Boton());
        add(tienda.getItem3Label());
        add(tienda.getItem3Boton());

        add(tienda.getInfoLabel1());
        add(tienda.getInfoLabel2());

        borde = new JLabel(new ImageIcon("src/resources/borde.png"));
        borde.setBounds(0, 152, borde.getIcon().getIconWidth(), borde.getIcon().getIconHeight());
        add(borde);

        fondo = new JLabel(new ImageIcon("src/resources/battbg.gif"));
        fondo.setBounds(0, 0, 975, 720);
        add(fondo);
        setComponentZOrder(fondo, getComponentCount() - 1);
    }
}