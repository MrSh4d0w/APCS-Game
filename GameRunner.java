import javax.swing.*;
import java.awt.*;
public class GameRunner{
    private static map m;
    private static Player p;
    
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

        m = new map("images/Level-0-Resized.png");
        layeredPane.add(m, -1);

        p = new Player(100, "ASSAULT", 50, 20);
        p.setBounds(0, 0, 100, 100);
        layeredPane.add(p, 0);
        p.setLocation(100, 100);

        

        
        
        f.add(layeredPane);
        f.setResizable(false);
        f.setVisible(true);
    }

    public static void setLocation(int x, int y){
        p.setLocation(x, y);
      }
}