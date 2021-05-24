import javax.swing.*;

// import environment.*;
// import enemy.*;
// import system.*;
// import maps.*;
import java.awt.*;

public class GameRunner {
    public static void main(String[] args) {
        
        JFrame f = new JFrame();
        f.setSize(650, 650);
        f.getContentPane().setBackground(new Color(50, 50, 50));
        f.setUndecorated(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocation(0, 0);




        JLayeredPane layeredPane = new JLayeredPane();//makes new JLayeredPane (only one)
        layeredPane.setPreferredSize(new Dimension(300, 310));//sets size of JLayeredPane
        layeredPane.setLayout(null);

        majorPain back = new majorPain("images/Level-0-Resized.png");
        layeredPane.add(back, -1);

        Player p = new Player(100, "ASSAULT", 50, 20);
        p.setBounds(0, 0, 100, 100);
        layeredPane.add(p, 0);
        
        f.add(layeredPane);
        f.setResizable(false);
        f.setVisible(true);
    }
}