import java.awt.*;
// import java.awt.Graphics.*;
import javax.swing.*;
import java.awt.event.*;

public class Tutorial extends JPanel implements ActionListener, KeyListener {

    private static Image img = new ImageIcon("images/tutorial/tutorial1.png").getImage();
    private static String currentImage = "images/tutorial/tutorial1.png";
    private static JFrame f;

    public Tutorial() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, null);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {tutorialForward();}
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {tutorialBackward();}
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {GameRunner.main(null); f.setVisible(false);}
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
    
    public void tutorialForward() { // patch work solution
        switch(currentImage) {
            case "images/tutorial/tutorial1.png":
                img = new ImageIcon("images/tutorial/tutorial2.png").getImage();
                currentImage = "images/tutorial/tutorial2.png";
                repaint();
                break;
            case "images/tutorial/tutorial2.png":
                img = new ImageIcon("images/tutorial/tutorial3.png").getImage();
                currentImage = "images/tutorial/tutorial3.png";
                repaint();
                break;
            case "images/tutorial/tutorial3.png":
                img = new ImageIcon("images/tutorial/tutorial4.png").getImage();
                currentImage = "images/tutorial/tutorial4.png";
                repaint();
                break;
            case "images/tutorial/tutorial4.png":
                img = new ImageIcon("images/tutorial/tutorial5.png").getImage();
                currentImage = "images/tutorial/tutorial5.png";
                repaint();
                break;
            case "images/tutorial/tutorial5.png":
                img = new ImageIcon("images/tutorial/tutorial6.png").getImage();
                currentImage = "images/tutorial/tutorial6.png";
                repaint();
                break;
            case "images/tutorial/tutorial6.png":
                img = new ImageIcon("images/tutorial/tutorial7.png").getImage();
                currentImage = "images/tutorial/tutorial7.png";
                repaint();
                break;
        }
    }
    public void tutorialBackward() { // patch work solution
        switch(currentImage) {
            case "images/tutorial/tutorial7.png":
                img = new ImageIcon("images/tutorial/tutorial6.png").getImage();
                currentImage = "images/tutorial/tutorial6.png";
                repaint();
                break;
            case "images/tutorial/tutorial6.png":
                img = new ImageIcon("images/tutorial/tutorial5.png").getImage();
                currentImage = "images/tutorial/tutorial5.png";
                repaint();
                break;
            case "images/tutorial/tutorial5.png":
                img = new ImageIcon("images/tutorial/tutorial4.png").getImage();
                currentImage = "images/tutorial/tutorial4.png";
                repaint();
                break;
            case "images/tutorial/tutorial4.png":
                img = new ImageIcon("images/tutorial/tutorial3.png").getImage();
                currentImage = "images/tutorial/tutorial3.png";
                repaint();
                break;
            case "images/tutorial/tutorial3.png":
                img = new ImageIcon("images/tutorial/tutorial2.png").getImage();
                currentImage = "images/tutorial/tutorial2.png";
                repaint();
                break;
            case "images/tutorial/tutorial2.png":
                img = new ImageIcon("images/tutorial/tutorial1.png").getImage();
                currentImage = "images/tutorial/tutorial1.png";
                repaint();
                break;
        }
    }


    public static void main(String[] args) {
        f = new JFrame("Cyberbreak - Titlescreen");
        Tutorial t = new Tutorial();
        f.setSize(1920, 1080);
        f.setUndecorated(true);
        f.getContentPane().add(t);
        f.setVisible(true);
    }

}
