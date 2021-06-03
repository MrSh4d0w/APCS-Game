import java.awt.*;
import javax.swing.*;

public class Grid extends JPanel{
    public final int x;
    public final int y;

    public Grid(String coordinates){
        setSize(112, 112);
        String[] c = coordinates.split(" ");
        x = Integer.parseInt(c[0]);
        y = Integer.parseInt(c[1]);
    }

    public void paintComponent(Graphics g){
        g.setColor(new Color(200,0,0));
        g.fillRect(0, 0, 4, 112);//left
        g.fillRect(0, 112-4, 112, 4);//bottom
        g.fillRect(112-4, 0, 4, 112);//right
        g.fillRect(0, 0, 112, 4);//top
    }

    public int getX() {return x;}
    public int getY() {return y;}
}
