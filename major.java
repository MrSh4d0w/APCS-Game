import javax.swing.*;
import java.awt.*;

public class major {
    public static void main(String[] args) {
        JFrame fram = new JFrame();//makes JFrame
        fram.setSize(500, 500);//sets JFrame size
        fram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//exits program on JFrame close
        fram.getContentPane().setBackground(new Color(50, 50, 50));//sets background color of JFrame

        JLayeredPane layeredPane = new JLayeredPane();//makes new JLayeredPane (only one)
        layeredPane.setPreferredSize(new Dimension(300, 310));//sets size of JLayeredPane

        layeredPane.add(new majorPain(new ImageIcon("house.png").getImage()), 0);//makes house image at z of 0 in JLayeredPane
        layeredPane.add(new majorPain(new ImageIcon("otter.png").getImage()), 1);//makes otter image at z of 1 in JLayeredPane
        fram.add(layeredPane);//adds JLayeredPane to JFrame

        
        fram.setVisible(true); //makes JFrame visible
    }
}