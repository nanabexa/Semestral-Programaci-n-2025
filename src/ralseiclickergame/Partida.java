package ralseiclickergame;

import java.io.Serializable;

public class Partida implements Serializable {
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