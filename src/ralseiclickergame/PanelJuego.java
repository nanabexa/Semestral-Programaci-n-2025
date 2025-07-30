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

    public PanelJuego(boolean cargarPartida) {
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
            fontGrande = font.deriveFont(Font.PLAIN, 75);
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

        //logica para cargar la partida si se solicita
        if (cargarPartida) {
            Partida partida = GuardarProgreso.cargarPartida();
            if (partida != null) {
                contador.setPuntos(partida.puntos);
                personaje.setRibbonCount(partida.ribbonCount);
                personaje.setJevilstailActivo(partida.jevilstailActivo);
                personaje.setDealmakerActivo(partida.dealmakerActivo);
                personaje.render();
            }
        }

        titulo = new JLabel("RALSEI CLICKER");
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

        // Los índices más bajos se dibujan más cerca del frente.
        setComponentZOrder(personaje.getBoton(), 0);

        // Mejoras y moños encima del personaje base
        setComponentZOrder(personaje.getDealmakerLabel(), 1);

        // CORRECCIÓN: Damos un índice Z único a cada moño para evitar conflictos
        // y aseguramos que todos estén encima del personaje.
        for (int i = 0; i < 10; i++) {
            setComponentZOrder(personaje.getRibbons().get(i), 2 + i);
        }

        // La imagen principal del personaje
        setComponentZOrder(personaje.getLabelImagen(), 12);

        // La cola se dibuja detrás del personaje
        setComponentZOrder(personaje.getJevilstailLabel(), 13);

        // Elementos de la UI y del fondo
        setComponentZOrder(titulo, 14);
        setComponentZOrder(contador.getLabelPuntos(), 15);
        setComponentZOrder(contador.getLabelValor(), 16);

        setComponentZOrder(tienda.getTitulo(), 17);
        setComponentZOrder(tienda.getItem1Label(), 18);
        setComponentZOrder(tienda.getItem1Boton(), 19);
        setComponentZOrder(tienda.getItem2Label(), 20);
        setComponentZOrder(tienda.getItem2Boton(), 21);
        setComponentZOrder(tienda.getItem3Label(), 22);
        setComponentZOrder(tienda.getItem3Boton(), 23);
        setComponentZOrder(tienda.getInfoLabel1(), 24);
        setComponentZOrder(tienda.getInfoLabel2(), 25);

        setComponentZOrder(borde, 26);
        setComponentZOrder(fondo, 27);
    }

    public Contador getContador() {
        return contador;
    }

    public Personaje getPersonaje() {
        return personaje;
    }
}