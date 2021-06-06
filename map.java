import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
// import java.util.*;

public class map extends JPanel implements ActionListener{
    private Image background;

    public map(String img) {
        this(new ImageIcon(img).getImage());
    }//128(+-32/2), 135(38/2)      15, 8

    public map(Image img) {
        this.background = img;
        setSize(new Dimension(1456, 1080));
    }

    public void paintComponent(Graphics g) {
        g.drawImage(background, 0, 0, null);
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("gamer");
    }

    

    
}