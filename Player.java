import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import javax.swing.*;   

public class Player extends JLayeredPane implements ActionListener{
    private Timer time = new Timer(5, this);
    private int HP, locX, locY, speed, acc;
    public enum pClass {ASSAULT,TANK,SNIPER,MELEE}
    public enum direction {UP,DOWN,LEFT,RIGHT}
    private pClass c;
    Image img;

    public Player(int HP, int locX, int locY, String c, int speed, int acc) {
        File file = new File("screenshot.png");
        try {
            img = ImageIO.read(new File("screenshot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }



        this.HP = HP;
        this.locX = locX;
        this.locY = locY;
        switch(c){
            case "ASSAULT":
                this.c = pClass.ASSAULT;
                break;
            case "TANK":
                this.c = pClass.TANK;
                break;
            case "MELEE":
                this.c = pClass.MELEE;
                break;
            case "SNIPER":
                this.c = pClass.SNIPER;
                break;
            default: 
                System.exit(0);
        }
        this.speed = speed;
        this.acc = acc;

        time.start();
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    public int getLocationX(){return locX;}// returns location of Player object x
    public int getLocationY(){return locY;}// returns location of Player object y
    public void setLocationX(int x){locX = x;}// sets location of Player object x
    public void setLocationY(int y){locY = y;}// sets location of Player object y

    public int getHP(){return HP;}// returns HP of Player object
    public void setHP(int HP){this.HP = HP;}// sets HP of Player object
    public void removeHP(int x){HP-=x;}// removes x HP of Player object
    public void addHP(int x){HP+=x;}// adds x HP of Player object

    public pClass getPClass(){return c;}// returns status of Player object
    public int getAcc(){return acc;}// returns accuracy
    public int getSpeed(){return speed;}// returns speed
    
    public void move(pClass p, direction dir, int amnt){}// move char [amnt] tiles in [dir] direction

    


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(img, 0, 0, null);
        //ASSAULT,TANK,SNIPER,MELEE
        switch(c){
            case ASSAULT:
                g = animateASSAULT(g);
                break;
            case TANK:
                g = animateTANK(g);
                break;
            case SNIPER:
                g = animateSNIPER(g);
                break;
            case MELEE:
                g = animateMELEE(g);
                break;
        }
    }

    public void actionPerformed(ActionEvent e) {
        repaint();   
    }

    public Graphics animateASSAULT(Graphics g){return g;}
    public Graphics animateSNIPER(Graphics g){return g;}
    public Graphics animateTANK(Graphics g){return g;}
    public Graphics animateMELEE(Graphics g){return g;}






/*switch(c){
    case ASSAULT:
        break;
    case TANK:
        break;
    case SNIPER:
        break;
    case MELEE:
        break;
}
        */


    public void iHateWarnings(){
        getLocationX();
        getLocationY();
        setLocationX(0);
        setLocationY(0);
        getHP();
        setHP(0);
        removeHP(0);
        addHP(0);
        getAcc();
        getSpeed();
        getPClass();
    }
}