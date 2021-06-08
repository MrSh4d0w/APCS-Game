import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
// import java.util.*;
// import java.awt.image.*;

public class console extends JPanel implements ActionListener{
    private static JTextField textField;
    private Image decorations;
    private static JTextArea textArea;
    private JLabel label;
    // private JPanel background;
    private Color notBlack;
    private Color notWhite;
    private static int turn;
    private static boolean hasMoved, hasAttacked;

    public console(String img) {
        this(new ImageIcon(img).getImage());
    } 

    public console(Image img) {
        notBlack = new Color(20,12,28);
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
        textArea.insert(""+txt, 0);
        String[] text = parser();
        

        int state;
        switch(text[0]){
            case "exit":
                System.exit(0);
                break;
            case "close":
                System.exit(0);
                break;
            case "move":
                state = GameController.setLocation(turn, text);
                if(state ==-1){break;}
                else if(state == -2){insert("That location is too far away");break;}
                else if(state == -3){insert("That location is obstructed");break;}
                else if(state == -4){insert("You have already moved this character");break;}
                else if(state == -5){insert("That location is not valid");break;}
                console.setHasMoved(true); // Prevents from moving again
                GameRunner.removeGrid();
                GameRunner.drawGrid();
                insert("Moved to " + text[2]);
                break;
            case "attack":
                state = GameController.attack(turn, text);
                if(state == -4){insert("You have already attacked with this character");break;}
                if(state == -5){insert("That location is obstructed");break;}
                console.setHasAttacked(true);
                insert("Attacked " + text[1]);
                break;
            case "next":
                if(text.length<=1 || !text[1].equalsIgnoreCase("turn")){insert("That is not a command. If you need help, type \"help\"");break;}
                if(turn==3){turn=0;}
                else{turn++;}
                console.setHasMoved(false);
                console.setHasAttacked(false);
                insert("Turn is now: " + (turn + 1));
                GameRunner.removeGrid();
                GameRunner.drawGrid();
                break;
            case "help":
                insert("\nMove To - Moves the currently selected character to specified position\n\nTarget - Targets a specific position\n\nAttack - Attack position that you targeted\n\nInfo - Get stats about the currently selected character\n\nNext Turn - Goes to the next turn\n\nClose - Closes the game\n\nHelp - Displays this message\n\nClear - Clears the console");
                break;
            case "clear":
                textArea.setText(null);
                break;
            case "setEnemyLoc":
                GameRunner.getE(0).setLocation(112, 112);
                insert("Set Enemy Loc to 112, 112");
                break;
            case "continue":
                if(!GameController.canContinue()){insert("There are still enemies alive");} 
                else {
                    if(Level.getCurrentLevel() == 1) {
                        insert("Continued to level 2");
                        Level.setCurrentLevel(Level.getCurrentLevel()+1);
                        GameRunner.level2();
                        GameRunner.removeGrid();
                        GameRunner.drawGrid();
                        break;
                    }
                    if(Level.getCurrentLevel() == 2) {
                        insert("Continued to level 3");
                        Level.setCurrentLevel(Level.getCurrentLevel()+1);
                        GameRunner.level3();
                        GameRunner.removeGrid();
                        GameRunner.drawGrid();
                        break;
                    }
                    if(Level.getCurrentLevel() == 3) {
                        insert("Good job, you won!");
                        GameRunner.win();
                        break;
                    }
                }
                System.out.println(Level.getCurrentLevel());
                break;
            case "info":
                insert(info());
                // + "\nRobot2-HP: " + GameRunner.getE(3).getHP() + " Pos: " + GameRunner.getE(3).getLoc() +
                // "\nBoomer-HP: " + GameRunner.getE(4).getHP() + " Pos: " + GameRunner.getE(4).getLoc());
                break;
            case "moveEnemies":
                EnemyController.boomerAction(4);
                EnemyController.copAction(1);
                insert("gamer");
                break;
            default:
                insert("That is not a command. If you need help, type \"help\"");
                break;
            }
        textField.setText("");
    }

    
    
    public static void insert(String msg){
        String[] arr = textArea.getText().split(">");
        if(arr.length==1){
            textArea.insert("\n" + msg + "\n\n", arr[0].length());
        } else {
        textArea.insert(msg + "\n\n", arr[0].length());}
        textArea.insert("\n> ", 0);
    }

    public static void insertMsg(String msg){
        String[] arr = textArea.getText().split(">");
        if(arr.length==1){
            textArea.insert("\n" + msg + "\n\n", arr[0].length());
        } else {
        textArea.insert(msg + "\n\n", arr[0].length());}
        textArea.insert("\n> ", 0);
    }

    private String[] parser(){
        String txt = textField.getText().replace(",", "");
        return txt.trim().split("\s");
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

    public static void setHasMoved(boolean b) {
        hasMoved = b;
    }
    public static boolean getHasMoved() {
        return hasMoved;
    }
    public static void setHasAttacked(boolean b) {
        hasAttacked = b;
    }
    public static boolean getHasAttacked() {
        return hasAttacked;
    }

    private static String info() { // for info command
        
        String l2 = "";
        String l3 = "";
        if(Level.getCurrentLevel()==2 || Level.getCurrentLevel()==3){l2 = "\nRobot2-HP: " + GameRunner.getE(3).getHP() + " Pos: " + GameRunner.getE(3).getPos();}
        if(Level.getCurrentLevel()==3){l3 = "\nBoomer-HP: " + GameRunner.getE(4).getHP() + " Pos: " + GameRunner.getE(4).getPos();}
        
        return "Red-HP: " + GameRunner.getP(0).getHP() + " Acc: " + GameRunner.getP(0).getAcc() + " Spd: " + GameRunner.getP(0).getSpeed() + 
            "\nGreen-HP: " + GameRunner.getP(1).getHP() + " Acc: " + GameRunner.getP(1).getAcc() + " Spd: " + GameRunner.getP(1).getSpeed() + 
            "\nPink-HP: " + GameRunner.getP(2).getHP() + " Acc: " + GameRunner.getP(2).getAcc() + " Spd: " + GameRunner.getP(2).getSpeed() + 
            "\nWhite-HP: " + GameRunner.getP(3).getHP() + " Acc: " + GameRunner.getP(3).getAcc() + " Spd: " + GameRunner.getP(3).getSpeed() + '\n' +
            "\nCop1-HP: " + GameRunner.getE(0).getHP() + " Pos: " + GameRunner.getE(0).getPos() +
            "\nCop2-HP: " + GameRunner.getE(1).getHP() + " Pos: " + GameRunner.getE(1).getPos() +
            "\nRobot1-HP: " + GameRunner.getE(2).getHP() + " Pos: " + GameRunner.getE(2).getPos() + l2 + l3;
    }

    // private static void setTurn(int t){turn = t;}
    public static int getTurn(){return turn;}

}
