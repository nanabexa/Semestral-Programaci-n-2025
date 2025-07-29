package ralseiclickergame;


public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            VentanaJuego ventana = new VentanaJuego();
            ventana.setVisible(true);
        });
    }
}