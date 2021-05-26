import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class map extends JPanel implements ActionListener {
    private static JTextField textField;
    private Image img;
    private int[][] grid;

    public map(String img) {
        this(new ImageIcon(img).getImage());
    }//128(+-32/2), 135(38/2)      15, 8

    public map(Image img) {
        this.img = img;
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);

        textField = new JTextField(5);
        ActionListener l = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String txt = textField.getText();
                System.out.println(textField.getText());
                if (includes(txt, "go to")) {
                    int[] parsed = parse(txt);
                    GameRunner.setLocation(parsed[0], parsed[1]);
                    System.out.println("gamer");

                }
                textField.setText("");
            }
        };
        textField.addActionListener(l);
        textField.setSize(650, 25);
        textField.setLocation(0, 625);
        this.add(textField);
    }

    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("gamer");
    }

    private boolean includes(String beg,String str) {
        if(beg.indexOf(str)>=0){
            return true;
        }
        return false;
    }

    private int[] parse(String str){
        int[] ret = new int[str.length()];
        int counter = 0;
        str = str.replace(',', ' ');
        String[] arr = str.split("\s");
        for(int i=0;i<arr.length;i++){
            System.out.println(arr[i]);
            if(!arr[i].equals("") && Character.isDigit(arr[i].charAt(0))){
                ret[counter] = Integer.parseInt(arr[i]);
                counter++; 
            }
        }
        return ret;
    }
}