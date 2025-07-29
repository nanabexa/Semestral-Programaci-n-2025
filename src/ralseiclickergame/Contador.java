package ralseiclickergame;

import javax.swing.*;

public class Contador {
    private int puntos = 0;
    private JLabel labelPuntos;
    private JLabel labelValor;

    public Contador() {
        labelPuntos = new JLabel("Puntos:");
        labelPuntos.setBounds(640, 520, 300, 50);

        labelValor = new JLabel("0");
        labelValor.setBounds(640, 580, 300, 50);
    }

    public void sumar(int cantidad) {
        puntos += cantidad;
        labelValor.setText(String.valueOf(puntos));
    }

    public boolean gastar(int cantidad) {
        if (puntos >= cantidad) {
            puntos -= cantidad;
            labelValor.setText(String.valueOf(puntos));
            return true;
        }
        return false;
    }

    public JLabel getLabelPuntos() { return labelPuntos; }
    public JLabel getLabelValor() { return labelValor; }
    public int getPuntos() { return puntos; }
}
