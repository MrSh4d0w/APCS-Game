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
        
        




        JLayeredPane gamePane = new JLayeredPane();//makes new JLayeredPane (only one)
        gamePane.setPreferredSize(new Dimension(1456, 1008));//sets size of JLayeredPane
        // layeredPane.setLayout(null);

        JLayeredPane textPane = new JLayeredPane();
        textPane.setPreferredSize(new Dimension(464, 1080));
        textPane.setBackground(new Color(25, 25, 25));
        textPane.setLocation(1009, 0);        


        m = new map("images/grid.png");
        c = new console("images/house.png");
        textPane.add(c);

        

        p = new Player(100, "ASSAULT", 50, 20);
        p.setBounds(0, 0, 112, 112);
        gamePane.add(p, 0);
        p.setLocation(100, 100);

        

        f.add(m);
        f.add(c);
        f.add(textPane);
        f.add(gamePane);
        f.setResizable(false);
        f.setVisible(true);
    }

    public static void setLocation(int x, int y){
        p.setLocation(x, y);
    }
    public static void setLocation(String str){
        int x;
        int y;
        int counter = 0;
        String[] arr = str.split("\s");
        x = Integer.parseInt(arr[0]);
        y = Integer.parseInt(arr[1]);
        p.setLocation(x, y);
    }
}