import javax.swing.JFrame;
// import environment.*;
// import enemy.*;
// import system.*;
// import maps.*;
import javax.swing.*;

public class GameRunner {
    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setSize(1920, 1080);
        f.setUndecorated(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocation(0, 0);

        Player p = new Player(100, 0, 0, "ASSAULT", 50, 20);
        f.add(p);
        Player p2 = new Player(100, 0, 0, "ASSAULT", 50, 20);
        f.add(p2);
        f.setVisible(true);
        f.setResizable(false);
    }
}