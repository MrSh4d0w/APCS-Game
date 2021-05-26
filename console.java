import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class console extends JPanel implements ActionListener{
    private static JTextField textField;
    private Image decorations;

    public console(String img) {
        this(new ImageIcon(img).getImage());
    } 

    public console(Image img) {
        this.decorations = img;
        setSize(new Dimension(464, 1080));

        textField = new JTextField(5);
        ActionListener l = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String txt = textField.getText();
                System.out.println(textField.getText());
                if (includes(txt, "go to")) {
                    int[] parsed = parse(txt);
                    GameRunner.setLocation(parsed[0], parsed[1]);
                    System.out.println("gamer");
                } else if(txt.equalsIgnoreCase("exit") 
                    || txt.equalsIgnoreCase("close")) {
                    System.exit(0);
                }
                textField.setText("");
            }
        };
        textField.addActionListener(l);
        textField.setSize(464, 25);
        this.add(textField);
    }
    
    public void paintComponent(Graphics g) {
        g.drawImage(decorations, 0, 0, null);
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
    public void actionPerformed(ActionEvent e) {/*gamer*/}
}