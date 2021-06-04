import javax.swing.*;
import java.awt.*;
import java.util.*;
// import java.awt.image.*;

public class GameRunner {
    private static JFrame f;
    private static map m;
    private static Player p1, p2, p3, p4;
    private static String[][] grid;
    private static console c;
    private static JLayeredPane mainPanel;
    private static ArrayList<Grid> grids;

    

    public static void main(String[] args) {
        grid = GameController.createGrid();

        f = new JFrame();
        f.setSize(1920, 1080);
        f.setUndecorated(true);

        mainPanel = new JLayeredPane();
        mainPanel.setSize(1920, 1080);
        mainPanel.setLayout(null);

        m = new map("images/Gameboard.png");// *Map
        m.setBounds(0, 0, 1456, 1080);
        mainPanel.add(m, -1);

        c = new console(new ImageIcon("images/console_backdrop.png").getImage());// *Console
        c.setBounds(1456, 0, 464, 1080);
        mainPanel.add(c, 0);

        p1 = new Player(100, "ASSAULT", 3, 20);// *Player
        p2 = new Player(100, "TANK", 2, 20);
        p3 = new Player(100, "SNIPER", 4, 20);
        p4 = new Player(100, "MELEE", 5, 20);
        
        p1.setSize(new Dimension(112, 112));
        p1.setLocation(grid[5][5]);
        p1.setOpaque(false);
        mainPanel.add(p1, 1);

        p2.setSize(new Dimension(112, 112));
        p2.setLocation(grid[6][5]);
        p2.setOpaque(false);
        mainPanel.add(p2, 2);

        p3.setSize(new Dimension(112, 112));
        p3.setLocation(grid[7][5]);
        p3.setOpaque(false);
        mainPanel.add(p3, 3);

        p4.setSize(new Dimension(112, 112));
        p4.setLocation(grid[8][5]);
        p4.setOpaque(false);
        mainPanel.add(p4, 4);

        drawGrid();
        f.getContentPane().add(mainPanel);
        f.setResizable(false);
        f.setVisible(true);
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
                if(x==x2 && y==y2){
                    grids.add(new Grid("gold"));
                    counter++;
                    grids.get(counter).setLocation(x2, y2);
                    mainPanel.add(grids.get(counter), 5);
                }else if(Math.abs(x-x2)<=(112*speed)&&Math.abs(y-y2)<(36+(112*speed))){
                    grids.add(new Grid ("green"));
                    counter++;
                    grids.get(counter).setLocation(x2, y2);
                    mainPanel.add(grids.get(counter), 5);
                }
            }
        }
    }

    public static void removeGrid(){   
    for(Grid g:grids)
        {
            g.setVisible(false);
            mainPanel.remove(g);
        }
    }
}
