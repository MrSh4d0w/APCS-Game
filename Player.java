import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Player extends JLayeredPane implements ActionListener{
    private Timer time = new Timer(5, this);
    private int HP, locX, locY, speed, acc;
    public enum pClass {ASSAULT,TANK,SNIPER,MELEE}
    public enum direction {UP,DOWN,LEFT,RIGHT}
    private pClass c;

    public Player(int HP, int locX, int locY, pClass c, int speed, int acc) {
        this.HP = HP;
        this.locX = locX;
        this.locY = locY;
        this.c = c;
        this.speed = speed;
        this.acc = acc;

        time.start();
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    private int getLocationX(){return locX;}// returns location of Player object x
    private int getLocationY(){return locY;}// returns location of Player object y
    private void setLocationX(int x){locX = x;}// sets location of Player object x
    private void setLocationY(int y){locY = y;}// sets location of Player object y

    private int getHP(){return HP;}// returns HP of Player object
    private void setHP(int HP){this.HP = HP;}// sets HP of Player object
    private void removeHP(int x){HP-=x;}// removes x HP of Player object
    private void addHP(int x){HP+=x;}// adds x HP of Player object

    private pClass getPClass(){return c;}// returns status of Player object
    private int getAcc(){return acc;}// returns accuracy
    private int getSpeed(){return speed;}// returns speed
    
    public void move(pClass p, direction dir, int amnt){}// move char [amnt] tiles in [dir] direction

    


    public void paintComponent(Graphics g){
        super.paintComponent(g);
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