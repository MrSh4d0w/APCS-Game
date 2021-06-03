import java.awt.*;
import javax.swing.*;

public class Grid extends JPanel{
    public Grid(){
        setSize(112, 112);
    }

    public void paintComponent(Graphics g){
        g.setColor(new Color(200,0,0));
        g.fillRect(0, 0, 4, 112);//left
        g.fillRect(0, 112-4, 112, 4);//bottom
        g.fillRect(112-4, 0, 4, 112);//right
        g.fillRect(0, 0, 112, 4);//top
    }
}
