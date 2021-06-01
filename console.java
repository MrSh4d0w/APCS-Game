import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.awt.image.*;

public class console extends JPanel implements ActionListener{
    private static JTextField textField;
    private Image decorations;
    private JTextArea textArea;
    private JLabel label;
    private JPanel background;
    private Color notBlack;
    private Color notWhite;
    public static int turn;
    public console(String img) {
        this(new ImageIcon(img).getImage());
    } 

    public console(Image img) {
        // notBlack = new Color(20,12,28);
        notBlack = Color.BLACK;
        // notWhite = new Color(102,106,105);
        // notWhite = new Color(140,140,140);
        notWhite = Color.WHITE;

        this.decorations = img;
        this.setLayout(null);
        this.setSize(new Dimension(464, 1080));
        
        textArea = new JTextArea(5, 20);
            textAreaInitializer();
        label = new JLabel(">", JLabel.LEFT);
            labelInitializer();
        textField = new JTextField(1);
            textFieldInitializer();
        setFonts();     
        turn = 0;
        
        this.add(textField);
        this.add(textArea);
        this.add(label);
    }

    public void paintComponent(Graphics g) {
        g.drawImage(decorations, 0, 0, null);
    }
    public void actionPerformed(ActionEvent e) {
        String txt = textField.getText();
            textArea.insert(" "+txt, 0);
            
            if(includes(txt, "move to")){
                String[] args = parser("move to");
                if(args==null){System.exit(0);}
                try{
                    // if(args[0].length()>2){textArea.insert("That's not a valid location", 3);}
                    int y = Character.getNumericValue(args[0].charAt(1));
                    int x = (Character.getNumericValue(args[0].charAt(0)))-9;
                    GameRunner.setLocation(x*112, (y*112)+36);
                    System.out.println(x + " " + y);
                } catch (Exception ex){
                    System.out.println("NumberFormatException");
                }        
            }
            if(includes(txt, "help")){
                textArea.insert("\ngo fuck yourself\n", 5);
            }
            if(txt.equalsIgnoreCase("exit") || txt.equalsIgnoreCase("close")) {
                System.exit(0);
            }
            if(txt.equalsIgnoreCase("next turn")){
                if(turn == 3){turn = 0;
                } else {turn++;}
            }
            if(includes(txt, "clear")){textArea.setText(null);} else {textArea.insert("\n>", 0);}
            textField.setText("");
    }
    
    private String[] parser(String command){
        String txt = textField.getText().replace(",", "");
        if(txt.indexOf(command) < -1){
            return null;
        }
        return txt.substring(command.length()).trim().split("\s");
    }
    private void labelInitializer() {
        label.setForeground(notWhite);
        label.setBackground(notBlack);
        label.setSize(20, 30);
        label.setOpaque(true);
        label.setLocation(0, 0);
    }
    private void textFieldInitializer() {
        textField.setBackground(notBlack);
        textField.setForeground(notWhite);
        textField.addActionListener(this);
        textField.setSize(444, 30);
        textField.setLocation(20, 0);
        textField.setCaretColor(notWhite);
        textField.setBorder(BorderFactory.createLineBorder(notBlack));
    }
    private void textAreaInitializer() {
        
        textArea.setSize(464, 1060);
        textArea.setBackground(notBlack);
        textArea.setEditable(false);      
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setForeground(notWhite);
        textArea.setLocation(0, 25);
    }   
    private void setFonts() {
        try{
            Font rulerGold = Font.createFont(Font.TRUETYPE_FONT, new File("RulerGold.ttf")).deriveFont(32f);
            textArea.setFont(rulerGold);
            textField.setFont(rulerGold);
            label.setFont(rulerGold);
        } catch (IOException e) {
            e.printStackTrace();
        } catch(FontFormatException e) {
            e.printStackTrace();
        }
    }
    private boolean includes(String beg,String str) {
        if(str.length()==0) return false;
        if(beg.toLowerCase().indexOf(str.toLowerCase())>=0){
            return true;
        }
        return false;
    }
    private static void setTurn(int t){turn = t;}
    public static int getTurn(){return turn;}

}