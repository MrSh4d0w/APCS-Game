import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.Timer;


public class Player extends JPanel implements ActionListener{
    // private static int counter, assaultCounter;
    private int HP, speed, acc; // Creates variables for HP, speed of the player (How many space it can move), and accuracy (how well it can hit a target).
    private String c; // Name of the class. "ASSAULT", "TANK", "SNIPER", "MELEE"
    private String loc; // Locations of the current player object as a string. Ex. "112 148" - This would correspond to A1 on the gameboard.
    private boolean alive;
    //private ActionListener actionListener = new ActionListener(); 
    /*{ // This is needed for the animations to work properly.
        @Override
        public void actionPerformed(ActionEvent e) { // 
            revalidate();
            repaint();   
        }
    };*/
    
    public Player(int HP, String c, int speed, int acc, boolean alive) { 
        Timer timer = new Timer(100, this);
        System.currentTimeMillis(); // This is needed for the time in EnemyController.java to work properly, I believe. 
        timer.setInitialDelay(0);
        timer.start();
        this.HP = HP;
        this.c = c;
        this.speed = speed;
        this.acc = acc;
        this.alive = alive;
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
    public boolean getAlive(){return alive;}
    public void setAlive(boolean b){
        if(!b){this.setVisible(false);}
        alive = b;}
    public void setLoc(int x, int y){loc = x + " " + y;}
    public String getLoc(){return loc;}      // return location of Player object as a string
    public void newLevel() {
        console.setHasMoved(false);
        console.setHasAttacked(false);
        setHP(100);
    }
    public void paintComponent(Graphics g){ // The PaintComponent uses a switch case to detect which method to run to animate. "c" is the name of the class.  
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
        Image ii = new ImageIcon(this.getClass().getResource("images/Character1_IdleGun.gif")).getImage(); // the "this.getClass.getResource" is something found online that gets the image from the images folder. 
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
    public void actionPerformed(ActionEvent e) { // Needed in order for the character animations to work properly.
        repaint();
        revalidate();
    }

    
}
