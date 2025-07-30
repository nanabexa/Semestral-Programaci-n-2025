package ralseiclickergame;

import java.io.*;

public class GuardarProgreso {

    // Guarda los datos del juego en un archivo
    public static void guardarPartida(int puntos, int ribbonCount, boolean jevilstailActivo, boolean dealmakerActivo) {
        try {
            // Crea un objeto que contendrá todos los datos
            Partida partida = new Partida(puntos, ribbonCount, jevilstailActivo, dealmakerActivo);

            // Abrimos un flujo para escribir en el archivo
            FileOutputStream fileOut = new FileOutputStream("partida.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            // Escribe el objeto en el archivo
            out.writeObject(partida);
            out.close();
            fileOut.close();
            System.out.println("Partida guardada en partida.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    // Carga los datos del juego desde un archivo
    public static Partida cargarPartida() {
        Partida partida = null;
        try {
            // Abrimos un flujo para leer el archivo
            FileInputStream fileIn = new FileInputStream("partida.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);

            // Leemos el objeto del archivo
            partida = (Partida) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Partida cargada desde partida.ser");
        } catch (IOException | ClassNotFoundException i) {
            System.out.println("No se encontró una partida guardada. Se iniciará una partida nueva.");
            return null; // Devuelve null si no hay archivo
        }
        return partida;
    }
}

// Clase para contener los datos de la partida, debe ser Serializable
class Partida implements Serializable {
    private static final long serialVersionUID = 1L;
    public int puntos;
    public int ribbonCount;
    public boolean jevilstailActivo;
    public boolean dealmakerActivo;

    public Partida(int puntos, int ribbonCount, boolean jevilstailActivo, boolean dealmakerActivo) {
        this.puntos = puntos;
        this.ribbonCount = ribbonCount;
        this.jevilstailActivo = jevilstailActivo;
        this.dealmakerActivo = dealmakerActivo;
    }
}

