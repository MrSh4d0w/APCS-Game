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
        this.setLayout(null);
        setSize(new Dimension(464, 1080));

        textField = new JTextField(1);
        ActionListener l = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String txt = textField.getText();
                if(includes(txt, "move to")){
                    String[] args = parser("move to");
                    if(args==null){System.exit(0);}
                    try{
                        int y = Character.getNumericValue(args[0].charAt(1));
                        int x = (Character.getNumericValue(args[0].charAt(0)))-9;
                        GameRunner.setLocation(x*112, y*112);
                        System.out.println(x + " " + y);
                    } catch (Exception ex){
                        System.out.println("NumberFormatException");
                    }        
                }



                
                
                /*if (includes(txt, "move to")) {
                    int[] parsed = parse(txt);
                    if(parsed==null){System.exit(0);}
                    GameRunner.setLocation(parsed[0]*112, parsed[1]*112);    
                }*/ else if(txt.equalsIgnoreCase("exit") || txt.equalsIgnoreCase("close")) {
                    System.exit(0);
                }
                textField.setText("");
            }
        };
        textField.addActionListener(l);
        textField.setSize(463, 25);
        textField.setLocation(0, 0);
        this.add(textField);
    }
    
    public void paintComponent(Graphics g) {
        g.drawImage(decorations, 0, 0, null);
    }
    private boolean includes(String beg,String str) {
        if(str.length()==0) return false;
        if(beg.indexOf(str)>=0){
            return true;
        }
        return false;
    }

    private String[] parser(String command){
        String txt = textField.getText().replace(",", "");
        if(txt.indexOf(command) < -1){
            return null;
        }
        return txt.substring(command.length()).trim().split("\s");
    }

    public void actionPerformed(ActionEvent e) {/*gamer*/}
}