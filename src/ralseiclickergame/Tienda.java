package ralseiclickergame;

import javax.swing.*;
import java.awt.event.*;

public class Tienda {
    private JLabel titulo;
    private JLabel info1, info2;

    private JLabel item1Label, item2Label, item3Label;
    private JButton item1Boton, item2Boton, item3Boton;

    private Contador contador;
    private Personaje personaje;

    public Tienda(Contador contador, Personaje personaje) {
        this.contador = contador;
        this.personaje = personaje;

        titulo = new JLabel("Tienda");
        titulo.setBounds(725, 200, 300, 50);

        info1 = new JLabel("*");
        info1.setBounds(50, 520, 500, 50);
        personaje.setInfoLabel(info1);

        info2 = new JLabel("");
        info2.setBounds(70, 570, 500, 50);

        item1Label = new JLabel("Lazo Blanco  50 pts ");
        item1Label.setBounds(640, 270, 300, 50);
        item1Boton = crearBoton(item1Label.getBounds(), () -> {
            if (contador.gastar(50)) {
                personaje.agregarRibbon();
                info1.setText("* Un lindo lazo blanco");
                info2.setText("(+1 punto por clic)");

                // Ocultar el botón y el texto si ya se tienen 10 lazos
                if (personaje.getRibbonCount() == 10) {
                    item1Boton.setVisible(false);
                    item1Label.setVisible(false);
                }
            }
        }, "Este lazo blanco es UNA ARMADURA","(+1 punto por clic)");

        item2Label = new JLabel("Jevilstail  200 pts");
        item2Label.setBounds(640, 340, 300, 50);
        item2Boton = crearBoton(item2Label.getBounds(), () -> {
            if (contador.gastar(200)) {
                personaje.activarJevilstail();
                item2Boton.setEnabled(false);
                item2Boton.setVisible(false);
                item2Label.setVisible(false); // 👈 ocultar también el texto
                info1.setText("* Una cola con forma de J");
                info2.setText("(chance de x4 pts/clic)");
            }
        }, "* Una cola con forma de J", "(chance de x4 pts/clic)");


        item3Label = new JLabel("Dealmaker 1000 pts");
        item3Label.setBounds(640, 410, 300, 50);
        item3Boton = crearBoton(item3Label.getBounds(), () -> {
            if (contador.gastar(1000)) {
                personaje.activarDealmaker();
                item3Boton.setEnabled(false);
                item3Boton.setVisible(false);
                item3Label.setVisible(false); //  ocultar también el texto
                info1.setText("* Un estiloso par de lentes");
                info2.setText("(x2 puntos por clic)");
            }
        }, "* Un estiloso par de lentes", "(x2 puntos por clic)");
        }

    private JButton crearBoton(java.awt.Rectangle bounds, Runnable onClick, String hoverText1, String hoverText2) {
        JButton b = new JButton("");
        b.setBounds(bounds);
        b.setContentAreaFilled(false);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
        b.addActionListener(e -> onClick.run());
        b.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                info1.setText(hoverText1);
                info2.setText(hoverText2);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                info1.setText("*");
                info2.setText("");
            }
        });
        return b;
    }


    public JLabel getTitulo() { return titulo; }
    public JLabel getInfoLabel1() { return info1; }
    public JLabel getInfoLabel2() { return info2; }

    public JLabel getItem1Label() { return item1Label; }
    public JButton getItem1Boton() { return item1Boton; }

    public JLabel getItem2Label() { return item2Label; }
    public JButton getItem2Boton() { return item2Boton; }

    public JLabel getItem3Label() { return item3Label; }
    public JButton getItem3Boton() { return item3Boton; }
}
