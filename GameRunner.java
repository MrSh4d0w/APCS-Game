
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
    private static Player p1, p2, p3, p4; // Creates player variables
    public static Enemy e1, e2, e3, e4, e5; // Creates enemy variables
    private static String[][] grid; // grid for the game.
    private static console c; // Console
    private static JLayeredPane mainPanel;
    private static ArrayList<Grid> grids;

    public static void main(String[] args) {
        grid = GameController.createGrid(); // Creates a 13x9 grid.

        f = new JFrame();
        f.setSize(1920, 1080);
        f.setUndecorated(true);

        mainPanel = new JLayeredPane();
        mainPanel.setSize(1920, 1080);
        mainPanel.setLayout(null);

        c = new console(new ImageIcon("images/console_backdrop.png").getImage());// *Console
        c.setBounds(1456, 0, 464, 1080);
        mainPanel.add(c, 0);

        setL(new Level(1)); // Creates a Level object which is mainly used to get the currentLevel and
                          // setCurrentLevel.
        level1(); // Starts level1.
        console.setHasMoved(false);
        console.setHasAttacked(false);

        GameRunner.drawGrid();
        f.getContentPane().add(mainPanel);
        f.setResizable(false);
        f.setVisible(true);
        GameController.getEntities();
    }

    /**
     * @return the l
     */
    public static Level getL() {
        return l;
    }

    /**
     * @param l the l to set
     */
    public static void setL(Level l) {
        GameRunner.l = l;
    }

    public static void level1() { // Creates the enemy and player objects for level 1.

        m = new map("images/Level1.png");// *Map
        m.setBounds(0, 0, 1456, 1080);
        mainPanel.add(m, -1);

        p1 = new Player(100, "ASSAULT", 2, 75, true);// *Player Objects
        p2 = new Player(100, "TANK", 2, 60, true);
        p3 = new Player(100, "SNIPER", 2, 90, true);
        p4 = new Player(100, "MELEE", 4, 0, true);

        p1.setSize(new Dimension(112, 112));
        p1.setLocation(grid[10][6]); // Puts the player object on the grid.
        p1.setOpaque(false);
        mainPanel.add(p1, 1); // Adds the Player object to a JLayeredPane which is used to layer several
                              // images on top of each other while preserving their transparency.

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

        e1 = new Enemy(0, "COP", 3, 20, true); // *Enemy objects
        e2 = new Enemy(0, "COP2", 3, 20, true);
        e3 = new Enemy(20, "ROBOT", 3, 20, true);

        e1.setSize(new Dimension(112, 112));
        e1.setLocation(grid[1][1]);
        e1.setOpaque(false);
        mainPanel.add(e1, 5);

        e2.setSize(new Dimension(112, 112));
        e2.setLocation(grid[3][5]);
        e2.setOpaque(false);
        mainPanel.add(e2, 5);

        e3.setSize(new Dimension(112, 112));
        e3.setLocation(grid[4][3]);
        e3.setOpaque(false);
        mainPanel.add(e3, 5);
    }

    public static void level2() {
        m.setMap("images/Level2.png"); // Changes the map to the level 2 map.
        p1.setLocation(grid[3][7]);
        p1.newLevel();
        p2.setLocation(grid[2][7]);
        p2.newLevel();
        p3.setLocation(grid[9][7]);
        p3.newLevel();
        p4.setLocation(grid[10][7]);
        p4.newLevel();

        e1.setLocation(grid[6][1]);
        e1.newLevel();
        e2.setLocation(grid[6][4]);
        e2.newLevel();
        e3.setLocation(grid[4][3]);
        e3.newLevel();

        e4 = new Enemy(20, "ROBOT2", 3, 20, true); // Initializes a new enemy object, the second robot which only appears in
                                            // this level and the next.
        e4.setSize(new Dimension(112, 112));
        e4.setLocation(grid[8][3]);
        e4.setOpaque(false);
        mainPanel.add(e4, 5);
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

        e5 = new Enemy(0, "BOOMER", 3, 20, true); // Initializes a new enemy object, the boomer which only appears in this
                                            // level.
        e5.setSize(new Dimension(112, 112));
        e5.setLocation(grid[1][7]);
        e5.setOpaque(false);
        mainPanel.add(e5, 5);
    }

    public static void win() {
        m.setMap("images/Win.png"); // Displays a separate map if the player wins.
        mainPanel.moveToFront(m);
    }

    public static void lose() {
        m.setMap("images/lose.png"); // Displays a separate map if the player loses.
        mainPanel.moveToFront(m);
    }

    public static void setLocation(int c, int x, int y) { // Primarily used, as you feed raw numbers, the actual pixel X
                                                          // and Y cords.
        if (x < 112 || y < 112 || x >= 1344 || y >= 896) {
        } else {
            switch (c) {
                case 0:
                    p1.setLocation(x, y);
                    p1.setLoc(x, y);
                    break;
                case 1:
                    p2.setLocation(x, y);
                    p2.setLoc(x, y);
                    break;
                case 2:
                    p3.setLocation(x, y);
                    p3.setLoc(x, y);
                    break;
                case 3:
                    p4.setLocation(x, y);
                    p4.setLoc(x, y);
                    break;
            }
        }

    }

    // On line 60, setLocation (which is in the Player class) is called on p1 with
    // the parameter of the grid. This method, still in Player class, calls the
    // second
    // setLocation (in GameRunner) method with parameters of the grid and itself.
    // That method, in GameRunner, then parses the data, then feeds it into the
    // component setLocation.
    // This is done like this in order to increase readability when instantiating
    // the objects rather than efficiency of code

    public static void setLocationE(int c, int x, int y) {
        if (x < 112 || y < 112 || x >= 1344 || y >= 896) {
        } else {
            switch (c) {
                case 0:
                    e1.setLocation(x, y);
                    e1.setLoc(x, y);
                    break;
                case 1:
                    e2.setLocation(x, y);
                    e2.setLoc(x, y);
                    break;
                case 2:
                    e3.setLocation(x, y);
                    e3.setLoc(x, y);
                    break;
                case 3:
                    e4.setLocation(x, y);
                    e4.setLoc(x, y);
                    break;
                case 4:
                    e5.setLocation(x, y);
                    e5.setLoc(x, y);
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

        switch (p.getPClass()) {
            case "ASSAULT":
                p1.setLocation(x, y);
                p1.setLoc(x, y);
                break;
            case "TANK":
                p2.setLocation(x, y);
                p2.setLoc(x, y);
                break;
            case "SNIPER":
                p3.setLocation(x, y);
                p3.setLoc(x, y);
                break;
            case "MELEE":
                p4.setLocation(x, y);
                p4.setLoc(x, y);
                break;
        }
    }

    public static void setLocation(String str, Enemy p) {
        int x;
        int y;

        String[] arr = str.split("\s");
        x = Integer.parseInt(arr[0]);
        y = Integer.parseInt(arr[1]);

        switch (p.getEClass()) {
            case "COP":
                e1.setLocation(x, y);
                e1.setLoc(x, y);
                break;
            case "COP2":
                e2.setLocation(x, y);
                e2.setLoc(x, y);
                break;
            case "ROBOT":
                e3.setLocation(x, y);
                e3.setLoc(x, y);
                break;
            case "ROBOT2":
                e4.setLocation(x, y);
                e4.setLoc(x, y);
                break;
            case "BOOMER":
                e5.setLocation(x, y);
                e5.setLoc(x, y);
                break;
        }
    }

    public static Player getP(int c) { // Returns the player object from the inputted int.
        switch (c) {
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

    public static Enemy getE(int c) { // Returns the Enemy object from the inputted int.
        switch (c) {
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

    public static void drawGrid() { // Draws the grid. This includes the green and gold squares that surround the
                                    // Player object whose turn it is.
        if (console.getTurn() == 4) {return;}
        String[][] g = GameController.createGrid();
        String[] loc = GameRunner.getP(console.getTurn()).getLoc().split(" ");
        int x = Integer.parseInt(loc[0]);
        int y = Integer.parseInt(loc[1]);
        int speed = GameRunner.getP(console.getTurn()).getSpeed();
        grids = new ArrayList<Grid>();
        int counter = -1;

        for (int i = 1; i < g.length - 1; i++) {
            for (int j = 1; j < g[0].length - 1; j++) {
                

                String[] gLoc = g[i][j].split(" ");
                int x2 = Integer.parseInt(gLoc[0]);
                int y2 = Integer.parseInt(gLoc[1]);
                int[][] idk = GameController.getEntities();

                if (x == x2 && y == y2) { // Checks to if x=x2 and y=y2. If true then that is the position of the
                                            // currently selected Player object. It then puts a gold box on the square
                                            // that the Player object is at.
                    grids.add(new Grid("gold"));
                    counter++;
                    grids.get(counter).setLocation(x2, y2);
                    mainPanel.add(grids.get(counter), 6);
                } else if (idk[i][j] == 1 || idk[i][j] == -1) {
                } else if (idk[i][j] > 1) { // Uses the array from getEntities. In this case, anything greater than 1 would be any of the enemy object. This puts a red box on the square that the Enemy object is on.
                    grids.add(new Grid("red"));
                    counter++;
                    grids.get(counter).setLocation(x2, y2);
                    mainPanel.add(grids.get(counter), 6);
                    // Puts a green box on the squares around the currently selected characters that
                    // are within the speed. For example, if the speed is 2, green boxes will be
                    // places 2 spaces in each direction from the character. Doesn't apply to walls,
                    // other players or enemy objects.
                } else if (Math.abs(x - x2) <= (112 * speed) && Math.abs(y - y2) < (36 + (112 * speed))) {
                    if (!console.getHasMoved()) {
                        grids.add(new Grid("green"));
                        counter++;
                        grids.get(counter).setLocation(x2, y2);
                        mainPanel.add(grids.get(counter), 6);
                    }
                }
                
            }
        }
    }

    public static void removeGrid() { // removes the grid, the one with the green, red, and gold squares.
        if(grids == null){return;}
        for (Grid g : grids) {
            g.setVisible(false);
            mainPanel.remove(g);
        }
    }
}
