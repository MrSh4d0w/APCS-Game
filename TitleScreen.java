import java.awt.*;
// import java.awt.Graphics.*;
import javax.swing.*;
import java.awt.event.*;

public class TitleScreen {
    
    private static Image img = new ImageIcon("images/Titlescreen.png").getImage();

    public static void main(String[] args) {
        JFrame f = new JFrame();
        JPanel p = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(img, 0, 0, null);
            }
        };
        f.setSize(1920, 1080);
        f.setUndecorated(true);
        f.add(p);
        f.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent ke) {
                // if(ke.getKeyCode() == KeyEvent.VK_LEFT){System.out.println("");};
            }
            public void keyPressed(KeyEvent ke) {
                if(ke.getKeyCode() == KeyEvent.VK_SPACE){Tutorial.main(null);f.setVisible(false);};

            }
            public void keyReleased(KeyEvent ke) {}
        });
        f.setVisible(true);
    }
}
