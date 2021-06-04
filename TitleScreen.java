import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class TitleScreen{
    private static Image titleScreen = new ImageIcon("images/Titlescreen.png").getImage();
    private static JFrame f;
    public static int d2;
    public static JPanel p;
    
    public static void main(String[] args) {
        p = new JPanel();
        p.setBounds(0, 0, 1920, 1080);
        p.setImage(titleScreen);

        f = new JFrame();
        f.setSize(1920,1080);
        f.setUndecorated(true);
        f.setVisible(true);

        f.add(p);
    }

    public void paintComponent(Graphics g) {
        g.drawImage(titleScreen, 0, 0, null);
    }

    public int d() {
        KeyListener l = new KeyListener() {
            public void keyTyped(KeyEvent ke) {}
            public void keyPressed(KeyEvent ke) {
                if(ke.getKeyCode() == KeyEvent.VK_SPACE) {
                    GameRunner.main(null);
                }
            }
            public void keyReleased(KeyEvent ke) {}
        };

        f.addKeyListener(l);
        return d2;
    }
    public static void dir(int d){
        d2 = d;
    }
}
