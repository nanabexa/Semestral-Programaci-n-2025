package ralseiclickergame;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class GuardarProgreso {
    private static final String ARCHIVO = "progreso.dat";

    public static void guardar(String nombre, int puntos, int ribbons, boolean jevil, boolean deal) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nombre + ".dat"))) {
            out.writeInt(puntos);
            out.writeInt(ribbons);
            out.writeBoolean(jevil);
            out.writeBoolean(deal);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int[] cargar(String nombre) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(nombre + ".dat"))) {
            int puntos = in.readInt();
            int ribbons = in.readInt();
            boolean jevil = in.readBoolean();
            boolean deal = in.readBoolean();
            return new int[]{puntos, ribbons, jevil ? 1 : 0, deal ? 1 : 0};
        } catch (IOException e) {
            return new int[]{0, 0, 0, 0};
        }
    }
}
