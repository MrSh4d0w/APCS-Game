// import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
// import java.io.File;
// import java.io.IOException;
import java.util.*;
// import java.awt.image.*;

public class GameRunner {
    private static JFrame f;
    private static map m;
    private static Level l;
    private static Player p1, p2, p3, p4;
    public static Enemy e1, e2, e3, e4, e5;
    private static String[][] grid;
    private static console c;
    private static JLayeredPane mainPanel;
    private static ArrayList<Grid> grids;
    private static boolean hasMoved = false;

    public static void main(String[] args) {
        grid = GameController.createGrid();

        f = new JFrame();
        f.setSize(1920, 1080);
        f.setUndecorated(true);

        mainPanel = new JLayeredPane();
        mainPanel.setSize(1920, 1080);
        mainPanel.setLayout(null);
        
        c = new console(new ImageIcon("images/console_backdrop.png").getImage());// *Console
        c.setBounds(1456, 0, 464, 1080);
        mainPanel.add(c, 0);

        l = new Level(1);
        level1();
        
        GameRunner.drawGrid();
        f.getContentPane().add(mainPanel);
        f.setResizable(false);
        f.setVisible(true);
        GameController.getEntities();
    }


    public static void level1() {
        
        m = new map("images/Level1.png");// *Map
        m.setBounds(0, 0, 1456, 1080);
        mainPanel.add(m, -1);

        p1 = new Player(100, "ASSAULT", 2, 75);// *Player
        p2 = new Player(100, "TANK", 2, 60);
        p3 = new Player(100, "SNIPER", 2, 90);
        p4 = new Player(100, "MELEE", 4, 0);
        
        p1.setSize(new Dimension(112, 112));
        p1.setLocation(grid[10][6]);
        p1.setOpaque(false);
        mainPanel.add(p1, 1);

        p2.setSize(new Dimension(112, 112));
        p2.setLocation(grid[10][7]);
        p2.setOpaque(false);
        mainPanel.add(p2, 2);

        p3.setSize(new Dimension(112, 112));
        p3.setLocation(grid[11][6]);
        p3.setOpaque(false);
        mainPanel.add(p3, 3);

        p4.setSize(new Dimension(112, 112));
        p4.setLocation(grid[11][7]);
        p4.setOpaque(false);
        mainPanel.add(p4, 4);

        e1 = new Enemy(0, "COP", 3, 20);
        e2 = new Enemy(0, "COP2", 3, 20);
        e3 = new Enemy(0, "ROBOT", 3, 20);       

        e1.setSize(new Dimension(112, 112));
        e1.setLocation(grid[1][1]);
        e1.setOpaque(false);
        mainPanel.add(e1,5);
        
        e2.setSize(new Dimension(112, 112));
        e2.setLocation(grid[3][5]);
        e2.setOpaque(false);
        mainPanel.add(e2,5);

        e3.setSize(new Dimension(112, 112));
        e3.setLocation(grid[4][3]);
        e3.setOpaque(false);
        mainPanel.add(e3,5);
    }

    public static void level2() {
        m.setMap("images/Level2.png");
        p1.setLocation(grid[3][7]);
        p2.setLocation(grid[2][7]);
        p3.setLocation(grid[9][7]);
        p4.setLocation(grid[10][7]);

        e1.setLocation(grid[6][1]);
        e2.setLocation(grid[6][4]);
        e3.setLocation(grid[4][3]);

        e4 = new Enemy(0, "ROBOT2", 3, 20);
        e4.setSize(new Dimension(112, 112));
        e4.setLocation(grid[8][3]);
        e4.setOpaque(false);
        mainPanel.add(e4,5);
    }

    public static void level3() {
        m.setMap("images/Level3.png");

        p1.setLocation(grid[10][6]);
        p2.setLocation(grid[10][4]);
        p3.setLocation(grid[11][1]);
        p4.setLocation(grid[9][4]);

        e1.setLocation(grid[4][1]);
        e2.setLocation(grid[3][5]);
        e3.setLocation(grid[4][3]);
        e4.setLocation(grid[6][6]);

        e5 = new Enemy(0, "BOOMER", 3, 20);
        e5.setSize(new Dimension(112, 112));
        e5.setLocation(grid[1][7]);
        e5.setOpaque(false);
        mainPanel.add(e5,5);
    }

    public static void win() {
        m.setMap("images/Win.png");
        mainPanel.moveToFront(m);
    }
    public static void lose() {
        m.setMap("images/lose.png");
        mainPanel.moveToFront(m);
    }

    public static void setLocation(int c, int x, int y) {
        if (x < 112 || y < 112 || x >= 1344 || y >= 896) {
        } else {
            switch(c){
                case 0:
                    p1.setLocation(x, y);
                    p1.setLoc(x,y);
                    break;
                case 1:
                    p2.setLocation(x, y);
                    p2.setLoc(x,y);
                    break;
                case 2:
                    p3.setLocation(x, y);
                    p3.setLoc(x,y);
                    break;
                case 3:
                    p4.setLocation(x, y);
                    p4.setLoc(x,y);
                    break;
            }
        }

    }
    public static void setLocation(String str, Player p) {
        int x;
        int y;
        
        String[] arr = str.split("\s");
        x = Integer.parseInt(arr[0]);
        y = Integer.parseInt(arr[1]);
        
        switch(p.getPClass()){
            case "ASSAULT":
                p1.setLocation(x, y);
                p1.setLoc(x,y);
                break;
            case "TANK":
                p2.setLocation(x, y);
                p2.setLoc(x,y);
                break;
            case "SNIPER":
                p3.setLocation(x, y);
                p3.setLoc(x,y);
                break;
            case "MELEE":
                p4.setLocation(x, y);
                p4.setLoc(x,y);
                break;
        }
    }
    public static Player getP(int c){
        switch(c){
            case 0:
                return p1;
            case 1:
                return p2;
            case 2:
                return p3;
            case 3:
                return p4;
        }
        return null;
    }

    public static void setLocation(String str, Enemy p) {
        int x;
        int y;
        
        String[] arr = str.split("\s");
        x = Integer.parseInt(arr[0]);
        y = Integer.parseInt(arr[1]);
        
        switch(p.getEClass()){
            case "COP":
                e1.setLocation(x, y);
                e1.setLoc(x,y);
                break;
            case "COP2":
                e2.setLocation(x, y);
                e2.setLoc(x,y);
                break;
            case "ROBOT":
                e3.setLocation(x, y);
                e3.setLoc(x,y);
                break;
            case "ROBOT2":
                e4.setLocation(x, y);
                e4.setLoc(x,y);
                break;
            case "BOOMER":
                e5.setLocation(x, y);
                e5.setLoc(x,y);
                break;
        }
    }
    public static Enemy getE(int c){
        switch(c){
            case 0:
                return e1;
            case 1:
                return e2;
            case 2:
                return e3;
            case 3:
                return e4;
            case 4:
                return e5;
        }
        return null;
    }
    public static void setLocationE(int c, int x, int y) {
        if (x < 112 || y < 112 || x >= 1344 || y >= 896) {
        } else {
            switch(c){
                case 0:
                    e1.setLocation(x, y);
                    e1.setLoc(x,y);
                    break;
                case 1:
                    e2.setLocation(x, y);
                    e2.setLoc(x,y);
                    break;
                case 2:
                    e3.setLocation(x, y);
                    e3.setLoc(x,y);
                    break;
                case 3:
                    e4.setLocation(x, y);
                    e4.setLoc(x,y);
                    break;
                case 4:
                    e5.setLocation(x, y);
                    e5.setLoc(x,y);
                    break;
            }
        }

    }


    public static void drawGrid(){
        String[][] g = GameController.createGrid();
        String[] loc = GameRunner.getP(console.getTurn()).getLoc().split(" ");
        int x = Integer.parseInt(loc[0]);
        int y = Integer.parseInt(loc[1]);
        int speed = GameRunner.getP(console.getTurn()).getSpeed();
        grids = new ArrayList<Grid>();
        int counter = -1;

        for(int i = 1; i < g.length-1; i++){
            for(int j = 1; j < g[0].length-1; j++){


                String[] gLoc = g[i][j].split(" ");
                int x2 = Integer.parseInt(gLoc[0]);
                int y2 = Integer.parseInt(gLoc[1]);
                int[][] idk = GameController.getEntities();

                if(x==x2 && y==y2){
                    grids.add(new Grid("gold"));
                    counter++;
                    grids.get(counter).setLocation(x2, y2);
                    mainPanel.add(grids.get(counter), 6);
                } else if(idk[i][j]==1){} 
                else if(idk[i][j]>1){
                    grids.add(new Grid ("red"));
                    counter++;
                    grids.get(counter).setLocation(x2, y2);
                    mainPanel.add(grids.get(counter), 6);

                } else if(Math.abs(x-x2)<=(112*speed)&&Math.abs(y-y2)<(36+(112*speed))) {
                    if(!hasMoved) {
                    grids.add(new Grid ("green"));
                    counter++;
                    grids.get(counter).setLocation(x2, y2);
                    mainPanel.add(grids.get(counter), 6);
                    } 
                }   
            }
        }
    }
    public static void removeGrid(){
    for(Grid g:grids){
            g.setVisible(false);
            mainPanel.remove(g);
        }
    }
    public static void setHasMoved(boolean b) {
        hasMoved = b;
    }
    public static boolean getHasMoved() {
        return hasMoved;
    }
}
