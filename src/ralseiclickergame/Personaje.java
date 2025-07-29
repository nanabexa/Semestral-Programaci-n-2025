package ralseiclickergame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Personaje {
    private JLabel imagen;
    private JButton boton;
    private Contador contador;
    private List<JLabel> ribbons = new ArrayList<>();
    private JLabel jevilstailLabel;
    private JLabel dealmakerLabel;

    private int ribbonCount = 0;
    private boolean jevilstailActivo = false;
    private boolean dealmakerActivo = false;
    private JLabel info1;
    private JPanel panelClick; // Referencia al panel para mostrar efecto
    private boolean animandoRebote = false;

    public Personaje(Contador contador, JPanel panel) {
        this.contador = contador;
        this.panelClick = panel;

        ImageIcon ralesImagen = new ImageIcon("src/resources/ralsei.png");
        int anchoOriginal = ralesImagen.getIconWidth();
        int altoOriginal = ralesImagen.getIconHeight();

        imagen = new JLabel(ralesImagen);
        imagen.setBounds(130 - anchoOriginal / 2, 120 - altoOriginal / 2, anchoOriginal * 2, altoOriginal * 2);
        imagen.setHorizontalAlignment(SwingConstants.CENTER);
        imagen.setVerticalAlignment(SwingConstants.CENTER);

        boton = new JButton("");
        boton.setBounds(imagen.getBounds());
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);

        // Evento de clic
        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Random rand = new Random();
                int puntosGanados = 1 + ribbonCount;
                if (jevilstailActivo && rand.nextInt(10) == 1) {
                    puntosGanados *= 4;
                }
                if (dealmakerActivo) {
                    puntosGanados *= 2;
                }
                contador.sumar(puntosGanados);

                if (info1 != null) {
                    String[] mensajes = {"* squish!", "* squeak!", "* sploosh!", "* squash!", "* purr!", "* kapow!!!"};
                    info1.setText(mensajes[rand.nextInt(mensajes.length)]);
                }

                if (panelClick != null) {
                    Point botonEnPanel = SwingUtilities.convertPoint(boton, e.getX(), e.getY(), panelClick);
                    EfectoClick.mostrarEfecto(panelClick, botonEnPanel.x, botonEnPanel.y);
                }

                // ⬇️ Llamar efecto boing sobre la imagen
                animarRebote(imagen);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // Rebote al pasar el cursor (opcional)
                animarRebote(imagen);
            }
        });

        // Cintas
        ImageIcon ribbonIcon = new ImageIcon("src/resources/ribbon.png");
        for (int i = 0; i < 10; i++) {
            JLabel ribbon = new JLabel(ribbonIcon);
            ribbon.setBounds(130 - i * 10, 110 - i * 25, ribbonIcon.getIconWidth(), ribbonIcon.getIconHeight());
            ribbon.setVisible(false);
            ribbons.add(ribbon);
        }

        // Powerups
        jevilstailLabel = new JLabel(new ImageIcon("src/resources/jevilstail.png"));
        jevilstailLabel.setBounds(365, 330, 60, 60);
        jevilstailLabel.setVisible(false);

        dealmakerLabel = new JLabel(new ImageIcon("src/resources/ralseidealmaker.png"));
        dealmakerLabel.setBounds(130, 120, 256, 256);
        dealmakerLabel.setVisible(false);
    }

    private void animarRebote(JLabel label) {
        if (animandoRebote) return; // Evitar múltiples animaciones a la vez
        animandoRebote = true;

        final int pasos = 6;
        final int delay = 20;
        final double escalaMax = 1.1;

        ImageIcon originalIcon = (ImageIcon) label.getIcon();
        int originalWidth = originalIcon.getIconWidth();
        int originalHeight = originalIcon.getIconHeight();

        Timer timer = new Timer(delay, null);
        timer.addActionListener(new ActionListener() {
            int paso = 0;
            boolean expandiendo = true;

            @Override
            public void actionPerformed(ActionEvent e) {
                double escala;
                if (expandiendo) {
                    escala = 1 + ((escalaMax - 1) * paso / (double) pasos);
                } else {
                    escala = 1 + ((escalaMax - 1) * (pasos - paso) / (double) pasos);
                }

                int nuevoAncho = (int) (originalWidth * escala);
                int nuevoAlto = (int) (originalHeight * escala);

                Image imagenEscalada = originalIcon.getImage().getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH);
                label.setIcon(new ImageIcon(imagenEscalada));

                paso++;
                if (paso > pasos) {
                    if (expandiendo) {
                        paso = 0;
                        expandiendo = false;
                    } else {
                        label.setIcon(originalIcon);
                        ((Timer) e.getSource()).stop();
                        animandoRebote = false;
                    }
                }
            }
        });
        timer.start();
    }

    // Resto de métodos públicos
    public void agregarRibbon() {
        if (ribbonCount < 10) {
            ribbons.get(ribbonCount).setVisible(true);
            ribbonCount++;
        }
    }

    public void activarJevilstail() {
        jevilstailActivo = true;
        jevilstailLabel.setVisible(true);
    }

    public void activarDealmaker() {
        dealmakerActivo = true;
        dealmakerLabel.setVisible(true);
    }

    public void setInfoLabel(JLabel info) {
        this.info1 = info;
    }

    public JLabel getLabelImagen() { return imagen; }
    public JButton getBoton() { return boton; }
    public List<JLabel> getRibbons() { return ribbons; }
    public JLabel getJevilstailLabel() { return jevilstailLabel; }
    public JLabel getDealmakerLabel() { return dealmakerLabel; }
    public boolean isJevilstailActivo() { return jevilstailActivo; }
    public boolean isDealmakerActivo() { return dealmakerActivo; }
}
