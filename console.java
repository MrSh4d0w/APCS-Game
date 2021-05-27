import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class console extends JPanel implements ActionListener{
    private static JTextField textField;
    private Image decorations;
    private JTextArea textArea;

    public console(String img) {
        this(new ImageIcon(img).getImage());
    } 

    public console(Image img) {
        this.decorations = img;
        this.setLayout(null);
        setSize(new Dimension(464, 1080));

        textArea = new JTextArea(5, 20);
        textAreaInitializer();
        
        textField = new JTextField(1);
        ActionListener l = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String txt = textField.getText();
                textArea.insert(txt, 0);
                
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
                if(includes(txt, "help")){
                    textArea.insert(": go fuck yourself\n", 4);
                }
                if(includes(txt, "clear")){textArea.setText(null);}
                else if(txt.equalsIgnoreCase("exit") || txt.equalsIgnoreCase("close")) {
                    System.exit(0);
                }
                textArea.insert("\n\n", 0);
                textField.setText("");
            }
        };
        
        setFonts();
        textFieldInitializer(l);
        this.add(textField);
        this.add(textArea);
    }

   
    
    public void paintComponent(Graphics g) {
        g.drawImage(decorations, 0, 0, null);
    }
    private boolean includes(String beg,String str) {
        if(str.length()==0) return false;
        if(beg.toLowerCase().indexOf(str.toLowerCase())>=0){
            return true;
        }
        return false;
    }

    

    public void actionPerformed(ActionEvent e) {/*gamer*/}
    
    private String[] parser(String command){
        String txt = textField.getText().replace(",", "");
        if(txt.indexOf(command) < -1){
            return null;
        }
        return txt.substring(command.length()).trim().split("\s");
    }
    private void textFieldInitializer(ActionListener l) {
        textField.setBackground(Color.BLACK);
        textField.setForeground(Color.WHITE);
        textField.addActionListener(l);
        textField.setSize(463, 30);
        textField.setLocation(0, 0);
    }
    private void textAreaInitializer() {
        textArea.setSize(464, 1060);
        textArea.setBackground(Color.BLACK);
        textArea.setEditable(false);      
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setForeground(Color.WHITE);
        textArea.setLocation(0, 25);
    }
    private void setFonts() {
        try{
            Font rulerGold = Font.createFont(Font.TRUETYPE_FONT, new File("RulerGold.ttf")).deriveFont(32f);
            textArea.setFont(rulerGold);
            textField.setFont(rulerGold);
        } catch (IOException e) {
            e.printStackTrace();
        } catch(FontFormatException e) {
            e.printStackTrace();
        }
    }
}