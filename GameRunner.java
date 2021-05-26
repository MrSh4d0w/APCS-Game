import javax.swing.*;
import java.awt.*;
public class GameRunner{
    private static map m;
    private static Player p;
    private static String[][] grid;
    private static console c;

    public static void createGrid() {
        grid = new String[8][12];
        int rowCoords = 0;
        int columnCoords = 0;
        for (int row = 0; row < 8; row++) {
            rowCoords = (row*112);
            for (int column = 0; column < 12; column++) {
                columnCoords = (column*112);
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
  

        m = new map("images/grid.png");//*Map
        m.setBounds(0, 0, 1456, 1008);
        mainPanel.add(m, -1);

        c = new console("");//*Console
        c.setBounds(1456,0, 464, 1080);
        mainPanel.add(c, 0);

        p = new Player(100, "ASSAULT", 50, 20);//*Player
        p.setSize(new Dimension(112, 112));
        p.setLocation(grid[1][2]);
        mainPanel.add(p, 0);
        

        

        f.add(mainPanel);
        f.setResizable(false);
        f.setVisible(true);
    }

    public static void setLocation(int x, int y){
        p.setLocation(x, y);
    }
    public static void setLocation(String str){
        int x;
        int y;
        String[] arr = str.split("\s");
        x = Integer.parseInt(arr[0]);
        y = Integer.parseInt(arr[1]);
        p.setLocation(x, y);
    }
}