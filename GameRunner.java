import javax.swing.JFrame;
// import environment.*;
// import enemy.*;
// import system.*;
// import maps.*;

public class GameRunner {
    public static void main(String[] args) {
        JFrame f = new JFrame();
        Player p = new Player(100, 0, 0, "ASSAULT", 50, 20);
        System.out.println(p.getHP());
    }
}
