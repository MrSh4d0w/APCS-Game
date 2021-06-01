import javax.swing.*;
import java.awt.*;
// import java.awt.image.*;

public class GameRunner {
    private static map m;
    private static Player p1, p2, p3, p4;
    private static String[][] grid;
    private static console c;

    public static void createGrid() {
        grid = new String[13][9];
        int rowCoords = 0;
        int columnCoords = 0;
        for (int row = 0; row < 13; row++) {
            rowCoords = (row * 112);
            for (int column = 0; column < 9; column++) {
                columnCoords = (column * 112)+36;
                grid[row][column] = rowCoords + " " + columnCoords;
            }
        }
    }

    public static void main(String[] args) {
        createGrid();

        JFrame f = new JFrame();
        f.setSize(1920, 1080);
        f.setUndecorated(true);

        JLayeredPane mainPanel = new JLayeredPane();
        mainPanel.setSize(1920, 1080);
        mainPanel.setLayout(null);

        m = new map("images/Gameboard.png");// *Map
        m.setBounds(0, 0, 1456, 1080);
        mainPanel.add(m, -1);

        c = new console(new ImageIcon("images/console_backdrop.png").getImage());// *Console
        c.setBounds(1456, 0, 464, 1080);
        mainPanel.add(c, 0);

        p1 = new Player(100, "ASSAULT", 50, 20);// *Player
        p2 = new Player(100, "TANK", 50, 20);
        p3 = new Player(100, "SNIPER", 50, 20);
        p4 = new Player(100, "MELEE", 50, 20);
        
        p1.setSize(new Dimension(112, 112));
        p1.setLocation(grid[5][5]);
        p1.setOpaque(false);
        mainPanel.add(p1, 1);

        p2.setSize(new Dimension(112, 112));
        p2.setLocation(grid[6][5]);
        p2.setOpaque(false);
        mainPanel.add(p2, 1);

        p3.setSize(new Dimension(112, 112));
        p3.setLocation(grid[7][5]);
        p3.setOpaque(false);
        mainPanel.add(p3, 1);

        p4.setSize(new Dimension(112, 112));
        p4.setLocation(grid[8][5]);
        p4.setOpaque(false);
        mainPanel.add(p4, 1);

        f.getContentPane().add(mainPanel);
        f.setResizable(false);
        f.setVisible(true);
    }

    public static void setLocation(int x, int y) {
        if (x < 112 || y < 112 || x >= 1344 || y >= 896) {
        } else {
            switch(console.getTurn()){
                case 0:
                    p1.setLocation(x, y);
                    break;
                case 1:
                    p2.setLocation(x, y);
                    break;
                case 2:
                    p3.setLocation(x, y);
                    break;
                case 3:
                    p4.setLocation(x, y);
                    break;
            }
        }

    }

    public static void setLocation(String str) {
        int x;
        int y;
        String[] arr = str.split("\s");
        x = Integer.parseInt(arr[0]);
        y = Integer.parseInt(arr[1]);
        switch(console.getTurn()){
            case 0:
                p1.setLocation(x, y);
            case 1:
                p2.setLocation(x, y);
            case 2:
                p3.setLocation(x, y);
            case 3:
                p4.setLocation(x, y);
        }

    }
}