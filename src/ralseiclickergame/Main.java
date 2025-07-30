package ralseiclickergame;


public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
//            VentanaJuego ventana = new VentanaJuego();
//            ventana.setVisible(true);

            PaginaInicio menu = new PaginaInicio(); // PaginaInicio SÍ tiene un constructor sin argumentos ahora.
            menu.setVisible(true);
        });
    }
}