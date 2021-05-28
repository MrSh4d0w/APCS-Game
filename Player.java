import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;   

public class Player extends JPanel implements ActionListener{
    private int HP, speed, acc;
    private int i = 0; // This is for the animations DONT TOUCH
    public enum pClass {ASSAULT,TANK,SNIPER,MELEE}
    public enum direction {UP,DOWN,LEFT,RIGHT}
    private pClass c;
    BufferedImage img;
    String image;
    int[][] assualtSpriteSheetCoords = { { 0, 0, 112, 112 }, { 112, 0, 112, 112 }, { 224, 0, 112, 112 },
                    { 336, 0, 112, 112 }, { 448, 0, 112, 112 }, { 560, 0, 112, 112 }, { 672, 0, 112, 112 },
                    { 784, 0, 112, 112 } };
    
    private ActionListener actionListener = new ActionListener() { 
        @Override
        public void actionPerformed(ActionEvent e) {
            i++;
            if (i == assualtSpriteSheetCoords.length) {i=0;}
            revalidate();
            repaint();   
        }
    };
    
    public Player(int HP, String c, int speed, int acc) {        
        Timer timer = new Timer(100, actionListener);
        timer.setInitialDelay(0);
        timer.start();
        this.HP = HP;
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
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    public void setLocation(String str){
        GameRunner.setLocation(str);
    }

    public int getHP(){return HP;}// returns HP of Player object
    public void setHP(int HP){this.HP = HP;}// sets HP of Player object
    public void removeHP(int x){HP-=x;}// removes x HP of Player object
    public void addHP(int x){HP+=x;}// adds x HP of Player object

    public pClass getPClass(){return c;}// returns status of Player object
    public int getAcc(){return acc;}// returns accuracy
    public int getSpeed(){return speed;}// returns speed
    
    public void move(pClass p, direction dir, int amnt){}// move char [amnt] tiles in [dir] direction

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
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

    public Graphics animateASSAULT(Graphics g){        
        wait(1000);
        getImg("Character1_IdleGun_Updated");
        Image subSprite;
        subSprite = img.getSubimage(assualtSpriteSheetCoords[i][0], assualtSpriteSheetCoords[i][1], assualtSpriteSheetCoords[i][2], assualtSpriteSheetCoords[i][3]);
        g.drawImage(subSprite, 0, 0, null);
        return g;
    }// !Do this
    public Graphics animateSNIPER(Graphics g){
        getImg("house");
        g.drawImage(img, 0, 0, null);
        return g;
    }// !Do this
    public Graphics animateTANK(Graphics g){
        getImg("house"); 
        g.drawImage(img, 0, 0, null);
        return g;
    }// !Do this
    public Graphics animateMELEE(Graphics g){
        getImg("house");
        g.drawImage(img, 0, 0, null);
        return g;
    }// !Do this

    private void resizeImg(int x1, int y1, int x2, int y2){
        this.img = ((BufferedImage) img).getSubimage(x1,y1,x2,y2);
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

    public static void wait(int milli) {
        try {
            Thread.sleep(milli);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }

}