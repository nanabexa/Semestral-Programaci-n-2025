package ralseiclickergame;

import java.io.*;

public class GuardarProgreso {
    // archivo donde se guardará la partida
    private static final String NOMBRE_ARCHIVO = "partida.ser";

    public static void guardarPartida(int puntos, int ribbonCount, boolean jevilstailActivo, boolean dealmakerActivo) {
        Partida partida = new Partida(puntos, ribbonCount, jevilstailActivo, dealmakerActivo);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(NOMBRE_ARCHIVO))) {
            oos.writeObject(partida); // escribe el objeto Partida en el archivo
            System.out.println("Partida guardada correctamente en " + NOMBRE_ARCHIVO);
        } catch (IOException e) {
            System.err.println("Error al guardar la partida: " + e.getMessage());
        }
    }

    // cargar la partida
    public static Partida cargarPartida() {
        File archivo = new File(NOMBRE_ARCHIVO);
        // para verifica si el archivo de guardado existe antes de intentar cargarlo
        if (!archivo.exists()) {
            System.out.println("No se encontró archivo de partida guardada.");
            return null; // Retorna null si no hay archivo para cargar
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(NOMBRE_ARCHIVO))) {
            Partida partida = (Partida) ois.readObject(); // esta cosaee el objeto Partida del archivo
            System.out.println("Partida cargada correctamente desde " + NOMBRE_ARCHIVO);
            return partida;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar la partida: " + e.getMessage());
            return null;
        }
    }
}