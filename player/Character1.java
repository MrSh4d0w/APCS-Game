import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Character1 extends Player implements ActionListener{
    // public enum status {NONE,FROZEN,BURNING}
    // public enum direction {UP,DOWN,LEFT,RIGHT}
    private JLayeredPane p;

    public Character1(int HP, int locX, int locY, status s){
        super(HP, locX, locY, s);
        p.setPreferredSize(new Dimension(16, 16));
    }

    public void move(direction dir, int amnt){
        //put animation here
    }// move char [amnt] tiles in [dir] direction

    public void paintComponent(Graphics g){
        //idle animation here
    }

    public void actionPerformed(ActionEvent e) {
        p.repaint();
    }
}