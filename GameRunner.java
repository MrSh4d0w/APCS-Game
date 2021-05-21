import javax.swing.JFrame;
// import environment.*;
// import enemy.*;
// import system.*;
// import maps.*;
import javax.swing.*;
import java.awt.*;

public class GameRunner {
    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setSize(1920, 1080);
        // f.setLayout(null);
        f.getContentPane().setBackground(new Color(50, 50, 50));
        f.setUndecorated(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocation(0, 0);

        Player p = new Player(100, 0, 0, "ASSAULT", 50, 20);
        f.setBackground(new Color(50, 50, 50));
        f.add(p, null, 0);

        // f.setLayout(new GridLayout(0,1));
        // Player p2 = new Player(100, 0, 0, "ASSAULT", 50, 20);
        // p2.setBackground(new Color(50, 50, 50));
        // f.add(p2, BorderLayout.CENTER);

        f.setVisible(true);
        f.setResizable(false);

        //imsad 
    }
}