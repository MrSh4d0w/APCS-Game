import javax.swing.*;
import java.awt.*;

public class major {
    public static void main(String[] args) {
        JFrame fram = new JFrame();//makes JFrame
        fram.setSize(1920, 1080);//sets JFrame size
        fram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//exits program on JFrame close
        fram.getContentPane().setBackground(new Color(50, 50, 50));//sets background color of JFrame

        JLayeredPane layeredPane = new JLayeredPane();//makes new JLayeredPane (only one)
        layeredPane.setPreferredSize(new Dimension(300, 310));//sets size of JLayeredPane
        layeredPane.setLayout(null);

        majorPain m1 = new majorPain("screenshot.png");//makes new majorPain object
        m1.setBounds(100, 200, 200, 100);//puts m1 in pos x,y and of size width,height
        m1.setLocation(500, 500);//sets location of m1

        layeredPane.add(m1, 1);//makes house image at z of 0 in JLayeredPane
        layeredPane.add(new majorPain("house.png"), 0);//makes otter image at z of 1 in JLayeredPane
        fram.add(layeredPane);//adds JLayeredPane to JFrame

        fram.setResizable(false);
        fram.setVisible(true); //makes JFrame visible
    }
}