package ralseiclickergame;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Musica {
    private Clip clip;

    public void reproducirMusica(String ruta) {
        try {
            File archivo = new File(ruta);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(archivo);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void detenerMusica() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
    public static void reproducirEfecto(String ruta) {
        try {
            File archivo = new File(ruta);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(archivo);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start(); // solo lo reproduce una vez
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
