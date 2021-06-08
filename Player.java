import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.Timer;


public class Player extends JPanel implements ActionListener{
                                             // private static int counter, assaultCounter;
    private int HP, speed, acc;
    private int i = 0;                       // This is for the animations DONT TOUCH
    private String c;
    private BufferedImage img;
    private String loc;
    private final int[][] spriteSheetCords = { { 0, 0, 112, 112 }, { 112, 0, 112, 112 }, { 224, 0, 112, 112 },
                    { 336, 0, 112, 112 }, { 448, 0, 112, 112 }, { 560, 0, 112, 112 }, { 672, 0, 112, 112 },
                    { 784, 0, 112, 112 } };
    
    private ActionListener actionListener = new ActionListener() { 
        @Override
        public void actionPerformed(ActionEvent e) {
            i++;
            if (i == spriteSheetCords.length) {i=0;}
            revalidate();
            repaint();   
        }
    };
    
    public Player(int HP, String c, int speed, int acc) {  
        Timer timer = new Timer(100, actionListener);
        System.currentTimeMillis();
        timer.setInitialDelay(0);
        timer.start();
        this.HP = HP;
        this.c = c;
        this.speed = speed;
        this.acc = acc;
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    public void setLocation(String str){
        GameRunner.setLocation(str, this);
    }

    public int getHP(){return HP;}           // returns HP of Player object
    public void setHP(int HP){this.HP = HP;} // sets HP of Player object
    public void removeHP(int x){HP-=x;}      // removes x HP of Player object
    public void addHP(int x){HP+=x;}         // adds x HP of Player object

    public String getPClass(){return c;}     // returns status of Player object
    public int getAcc(){return acc;}         // returns accuracy
    public int getSpeed(){return speed;}     // returns speed
    public void setLoc(int x, int y){loc = x + " " + y;}
    public String getLoc(){return loc;}
    
    // public void move(pClass p, int amnt){}// move char [amnt] tiles in [dir] direction

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        switch(c){
            case "ASSAULT":
                g = animateASSAULT(g);
                break;
            case "TANK":
                g = animateTANK(g);
                break;
            case "SNIPER":
                g = animateSNIPER(g);
                break;
            case "MELEE":
                g = animateMELEE(g);
                break;
        }
    }

    public Graphics animateASSAULT(Graphics g){        
        Image ii = new ImageIcon(this.getClass().getResource("images/Character1_IdleGun.gif")).getImage();
        g.drawImage(ii, 0, 0, null);
        return g;
    }
    public Graphics animateTANK(Graphics g){        
        Image ii = new ImageIcon(this.getClass().getResource("images/Character2_IdleGun.gif")).getImage();
        g.drawImage(ii, 0, 0, null);
        return g;
    }
    public Graphics animateSNIPER(Graphics g){        
        Image ii = new ImageIcon(this.getClass().getResource("images/Character3_IdleGun.gif")).getImage();
        g.drawImage(ii, 0, 0, null);
        return g;
    }
    public Graphics animateMELEE(Graphics g){        
        Image ii = new ImageIcon(this.getClass().getResource("images/Character4_IdleGun.gif")).getImage();
        g.drawImage(ii, 0, 0, null);
        return g;
    }
    private void getImg(String name){
        try {
            img = ImageIO.read(new File("images/" + name + ".png"));
        } catch (IOException e) {e.printStackTrace(); }
    }
    public void iHateWarnings(){
        getHP();
        setHP(0);
        removeHP(0);
        addHP(0);
        getAcc();
        getSpeed();
        getPClass();
    }
    public void actionPerformed(ActionEvent e) {}

    
}
