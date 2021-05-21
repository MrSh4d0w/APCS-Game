import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

public class major {
    public static void main(String[] args) {
        JFrame fram = new JFrame();//makes JFrame
        fram.setSize(256, 256);//sets JFrame size
        fram.setUndecorated(true);
        fram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//exits program on JFrame close
        fram.getContentPane().setBackground(new Color(50, 50, 50));//sets background color of JFrame

        JLayeredPane layeredPane = new JLayeredPane();//makes new JLayeredPane (only one)
        layeredPane.setPreferredSize(new Dimension(300, 310));//sets size of JLayeredPane
        layeredPane.setLayout(null);

        majorPain c1 = new majorPain("images/Level-0.png");
        c1.setBounds(0,0, 256, 256);
        layeredPane.add(c1, -1);


        BufferedImage p1 = null;
        try {
            p1 = ImageIO.read(new File("images/AllCharacters_Idle.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image subSprite = p1.getSubimage(0, 0, 78, 78);
        majorPain p2 = new majorPain(subSprite);
        p2.setBounds(96, 96, 78, 78); 
        layeredPane.add(p2, 0);
    

        // majorPain m1 = new majorPain("screenshot.png");//makes new majorPain object
        // m1.setBounds(100, 200, 200, 100);//puts m1 in pos x,y and of size width,height

        // layeredPane.add(m1, 1);//makes house image at z of 0 in JLayeredPane
        // layeredPane.add(new majorPain("house.png"), 0);//makes otter image at z of 1 in JLayeredPane
        
        fram.add(layeredPane);//adds JLayeredPane to JFrame
        fram.setResizable(false);
        fram.setVisible(true); //makes JFrame visible
    }
}