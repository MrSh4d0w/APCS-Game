import javax.swing.*;
import java.awt.*;
// import java.awt.event.*;


public class major {
    public static void main(String[] args) {
        JFrame fram = new JFrame();
        fram.setSize(500, 500);
        fram.setLayout(new GridBagLayout());
        GridBagConstraints constraint = new GridBagConstraints();
        fram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fram.setBackground(new Color(50, 50, 50));
        
        constraint.gridx = 0;
        constraint.gridy = 0;

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(300, 300));



        ImageIcon closeLarg = new ImageIcon("C:/Users/arg10/Pictures/Saved Pictures/closeLarge.png");
        JPanel closeLarge = new JPanel();
        JLabel closeLargLabel = new JLabel(closeLarg);
        closeLargLabel.setOpaque(false);
        closeLarge.add(closeLargLabel);
        fram.add(closeLarge, constraint);


        
        constraint.gridx = 1;
        JPanel close = new JPanel();
        close.add(new JLabel(new ImageIcon("C:/Users/arg10/Pictures/Saved Pictures/close.png")));
        fram.add(close, constraint);

        JPanel back = new JPanel();
        back.setBackground(new Color(50, 50, 50));
        fram.add(back);


        




        fram.setVisible(true);
    }
}