import javax.swing.JFrame;
import player.*;
// import environment.*;
// import enemy.*;
// import system.*;
// import maps.*;

public class GameRunner {
    public enum pClass {ASSAULT,TANK,SNIPER,MELEE}
    public static void main(String[] args) {
        JFrame f = new JFrame();
        Player p = new Player(100, 0, 0, pClass.ASSAULT, 50, 20);
    }
}
