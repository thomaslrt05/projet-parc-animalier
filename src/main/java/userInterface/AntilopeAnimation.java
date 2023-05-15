package userInterface;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;


public class AntilopeAnimation extends Thread {
    private JFrame frame;
    private JPanel panel;
    private BufferedImage antilopeImage;
    private Point antilopePosition;
    private int step;
    private boolean running;


    public AntilopeAnimation(MainJFrame mainJFrame) {

        try {
            antilopeImage = ImageIO.read(new File("./src/main/ressources/antilope.png"));
            int antilopeWidth = antilopeImage.getWidth() / 4;
            int antilopeHeight = antilopeImage.getHeight() / 4;
            antilopePosition = new Point(-antilopeWidth, mainJFrame.getHeight() / 2 - antilopeHeight / 2);
            step = 5;
            running = false;
            panel = new JPanel() {
                @Override
                public void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Dimension size = mainJFrame.getSize();
                    g.drawImage(antilopeImage, antilopePosition.x, antilopePosition.y, antilopeWidth, antilopeHeight, null);
                    panel.setBounds(0, 0, size.width, size.height);
                    panel.setOpaque(false);
                }
            };
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Le chemin pour charger l'image est incorrect","Erreur",JOptionPane.ERROR_MESSAGE);

        }
    }


    public int getAntilopePositionX() {
        return antilopePosition.x;
    }

    public int getAntilopePositionY() {
        return antilopePosition.y;
    }

    public void setAntilopePosition(Point newPosition) {
        this.antilopePosition = newPosition;
    }

    public BufferedImage getAntilopeImage() {
        return antilopeImage;
    }

    public Point getAntilopePosition() {
        return antilopePosition;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void run() {
        running = true;
        while (running) {
            antilopePosition.translate(step, 0);
            if (antilopePosition.x > panel.getWidth()) {
                antilopePosition.x = -antilopeImage.getWidth();
            } else if (antilopePosition.x < -antilopeImage.getWidth()) {
                antilopePosition.x = panel.getWidth();
            }
            panel.repaint();
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}