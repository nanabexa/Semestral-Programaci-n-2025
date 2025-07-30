package ralseiclickergame;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import javax.sound.sampled.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;

public class EfectoClick {

    public static void mostrarEfecto(JPanel panel, int x, int y) {
        reproducirSonido("src/resources/RalseiDialogue.wav");
        Random rand = new Random();

        int numCorazones = 3;
        int distancia = 40; // hasta dónde se moverá

        // Direcciones en diagonal: arriba-izquierda, arriba-centro, arriba-derecha
        int[][] direcciones = {
                {-1, -1},
                {0, -1},
                {1, -1}
        };

        for (int i = 0; i < rand.nextInt(1,4); i++) {
            int dx = direcciones[i][0];
            int dy = direcciones[i][1];

            JLabel corazon = new JLabel(new ImageIcon("src/resources/corazonblur.png"));
            int width = 30;
            int height = 30;
            corazon.setBounds(x - width / 2, y - height / 2, width, height);
            panel.add(corazon);
            panel.setComponentZOrder(corazon, 0);
            panel.repaint();

            Timer anim = new Timer(30, null);
            anim.addActionListener(new ActionListener() {
                int step = 0;
                int pasos = 15;

                @Override
                public void actionPerformed(ActionEvent e) {
                    double escala = 1.0 + (step * 0.02); // se agranda un poco
                    int nx = x + (dx * step * distancia / pasos);
                    int ny = y + (dy * step * distancia / pasos);
                    int w = (int) (width * escala);
                    int h = (int) (height * escala);

                    corazon.setBounds(nx - w / 2, ny - h / 2, w, h);
                    panel.repaint();

                    step++;
                    if (step > pasos) {
                        ((Timer) e.getSource()).stop();
                        panel.remove(corazon);
                        panel.repaint();
                    }
                }
            });
            anim.start();
        }
    }
    public static void reproducirSonido(String ruta) {
        try {
            File archivo = new File(ruta);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(archivo);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
