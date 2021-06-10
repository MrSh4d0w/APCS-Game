import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
// import java.util.*;
// import java.awt.image.*;

public class console extends JPanel implements ActionListener{
    private static JTextField textField; // Creates a text field where the user can input commands.
    private Image decorations;
    private static JTextArea textArea; // Displays the text of previous commands. 
    private JLabel label;
    // private JPanel background;
    private Color notBlack;
    private Color notWhite;
    private static int turn; // Controls whose turn it is currently.
    private static boolean hasMoved, hasAttacked; // Used to determine if Player objects have moved and/or attacked yet.

    public console(String img) {
        this(new ImageIcon(img).getImage());
    } 

    public console(Image img) { // Creates the console seen on the right side of the game screen.
        notBlack = new Color(20,12,28);
        notWhite = Color.WHITE;

        this.decorations = img;
        this.setLayout(null);
        this.setSize(new Dimension(464, 1080));
        
        textArea = new JTextArea(5, 20);
            textAreaInitializer();
        label = new JLabel(">", JLabel.LEFT); // Puts a ">" at the beginning of the textbox
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
        String[] text = parser();
        

        int state;
        switch(text[0]){ // switch to see what the user has inputted.
            case "exit":
                System.exit(0);
                break;
            case "close":
                System.exit(0);
                break;
            case "move":
                state = GameController.setLocation(turn, text);
                if(state ==-1){break;}
                else if(state == -2){insert("That location is too far away", txt);break;}
                else if(state == -3){insert("That location is obstructed", txt);break;}
                else if(state == -4){insert("You have already moved this character", txt);break;}
                else if(state == -5){insert("That location is not valid", txt);break;}
                console.setHasMoved(true); // Prevents from moving again
                GameRunner.removeGrid(); // Once the player has moved, this method, and drawGrid, basically redraw the grid so the green and gold squares are aligned properly.
                GameRunner.drawGrid();
                insert("Moved to " + text[2], txt); // Outputs to the Textarea that the user has moved to the position they inputted.
                break;
            case "attack":
                state = GameController.attack(turn, text, txt);
                if(state == -3){insert("There is no enemy there", txt);} else
                if(state == -4){insert("You have already attacked with this character", txt);break;} else
                if(state == -5){insert("That location is obstructed", txt);break;} else
                if(state == -1){insert("That position does not exist", txt);break;} else{
                    setHasAttacked(true); // Prevents from attacking again
                    if(state == -6){insert("You tried to attack an enemy, but you missed", txt);break;}
                }
                checkAliveEnts();
                break;
            case "next":
                nextTurn(txt);
                break;
            case "help": // Outputs a list of commands the user can input.
                insert("\nMove To - Moves the currently selected character to specified position\n\nTarget - Targets a specific position\n\nAttack - Attack position that you targeted\n\nInfo - Get stats about the currently selected character\n\nNext Turn - Goes to the next turn\n\nClose - Closes the game\n\nHelp - Displays this message\n\nClear - Clears the console", txt);
                break;
            case "clear": // clears the console textarea.
                textArea.setText(null);
                break;
            case "setEnemyLoc": // for testing purposes. 
                GameRunner.getE(0).setLocation(112, 112);
                insert("Set Enemy Loc to 112, 112", txt);
                break;
            case "continue": // Goes to the next level is all enemies in the current level are dead.
                if(!GameController.canContinue()){insert("There are still enemies alive", txt);} 
                else {
                    turn = 0;
                    if(Level.getCurrentLevel() == 1) {
                        insert("Continued to level 2", txt);
                        Level.setCurrentLevel(Level.getCurrentLevel()+1);
                        GameRunner.level2();
                        GameRunner.removeGrid();
                        GameRunner.drawGrid();
                        break;
                    }
                    if(Level.getCurrentLevel() == 2) {
                        insert("Continued to level 3", txt);
                        Level.setCurrentLevel(Level.getCurrentLevel()+1);
                        GameRunner.level3();
                        GameRunner.removeGrid();
                        GameRunner.drawGrid();
                        break;
                    }
                    if(Level.getCurrentLevel() == 3) {
                        insert("Good job, you won!", txt);
                        GameRunner.win();
                        break;
                    }
                }
                System.out.println(Level.getCurrentLevel());
                break;
            case "info":
                insert(info(), txt); // See info method below.
                // + "\nRobot2-HP: " + GameRunner.getE(3).getHP() + " Pos: " + GameRunner.getE(3).getLoc() +
                // "\nBoomer-HP: " + GameRunner.getE(4).getHP() + " Pos: " + GameRunner.getE(4).getLoc());
                break;
            case "moveEnemies": // for testing purposes, maybe.
                EnemyController.boomerAction(4);
                EnemyController.copAction(1);
                insert("", txt);
                break;
            default: // Outputs if the user doesn't input a valid command, or incorrectly inputs a command.
                insert("That is not a command. If you need help, type \"help\"", txt);
                break;
            }
        textField.setText("");
    }

    private void nextTurn(String txt) {
        turn++;
        if(turn!=4 && !GameRunner.getP(turn).getAlive()) {nextTurn(txt);return;}//recursive statement to skip dead player's turns
        console.setHasMoved(false); // Allows the next character to move
        console.setHasAttacked(false); // Allows the next character to attack
        insert("Turn is now: " + (turn + 1), txt); // Outputs the NEW turn. +1 is added for aesthetics. 
        if(turn==4){
            EnemyController.attack();
            textField.setText("");
        } // if all of the characters have their turn in the round, it resets the turns back to 0.
        else{
            GameRunner.removeGrid(); // Redraws grid.
            GameRunner.drawGrid();
        }
        if(turn==4){turn=-1;nextTurn(txt);}//recursive statement to 
    }
    // Used the insert text in the textArea. Format is as follows.
    // > (Command the user the inputted)
    // (Response to command, if any)    
    public static void insert(String msg, String txt){ 
        textArea.insert("\n> " + txt, 0);
        textArea.insert("\n" + msg + "\n", txt.length()+3);
    }

    public static void insertMsg(String msg){
        textArea.insert("\n" + msg + "\n", 0);
    }
    // Gets rid of commas and spaces. 
    private String[] parser(){
        String txt = textField.getText().replace(",", "");
        return txt.trim().split("\s");
    }
    private void labelInitializer() { // Sets aesthetics of the JLabel.
        label.setForeground(notWhite);
        label.setBackground(notBlack);
        label.setSize(20, 30);
        label.setOpaque(true);
        label.setLocation(0, 0);
    }
    private void textFieldInitializer() { // Sets aesthetics of the textField
        textField.setBackground(notBlack);
        textField.setForeground(notWhite);
        textField.addActionListener(this);
        textField.setSize(444, 30);
        textField.setLocation(20, 0);
        textField.setCaretColor(notWhite);
        textField.setBorder(BorderFactory.createLineBorder(notBlack));
    }
    private void textAreaInitializer() { // Sets aesthetics of the textArea
        
        textArea.setSize(464, 1060);
        textArea.setBackground(notBlack);
        textArea.setEditable(false);      
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setForeground(notWhite);
        textArea.setLocation(0, 25);
    }   
    private void setFonts() { // Sets font to the custom font "RulerGold". This font is used for everything in the console area. 
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
    // These four are used to see/set if the current character has moved/attacked or not.
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
    // Prints the info all Player and Enemy objects in the current level into the console.
    private static String info() { // for info command
        // l2 and l3 exist so that enemies that aren't in level don't appear in the info command. 
        Player p1 = GameRunner.getP(0);
        String player1Info = "";
        if(p1.getAlive()){player1Info = "Assault:  HP: "+p1.getHP()+"    Location: "+p1.getPos();}

        Player p2 = GameRunner.getP(1);
        String player2Info = "";
        if(p2.getAlive()){player2Info = "\nTank:     HP: "+p2.getHP()+"    Location: "+p2.getPos();}
 
        Player p3 = GameRunner.getP(2);
        String player3Info = "";
        if(p3.getAlive()){player3Info = "\nSniper:   HP: "+p3.getHP()+"    Location: "+p3.getPos();}

        Player p4 = GameRunner.getP(3);
        String player4Info = "";
        if(p4.getAlive()){player4Info = "\nMelee:     HP: "+p4.getHP()+"    Location: "+p4.getPos();}

        Enemy e1 = GameRunner.getE(0);
        String Cop1Info = "";
        if(e1.getAlive()){player4Info = "\nCop1:      HP: "+e1.getHP()+"    Location: "+e1.getPos();}        
        
        String l2 = "";
        String l3 = "";
        if(Level.getCurrentLevel()==2 || Level.getCurrentLevel()==3){l2 = "\nRobot2  HP: " + GameRunner.getE(3).getHP() + " Pos: " + GameRunner.getE(3).getPos();}
        if(Level.getCurrentLevel()==3){l3 = "\nBoomer  HP: " + GameRunner.getE(4).getHP() + " Pos: " + GameRunner.getE(4).getPos();}
        // Gives how much health, Accuracy, and Speed each Player object has. Also gives how much health and the location on the gameboard (Ex. A1) of each Enemy object
        return "Assault:  HP: " + GameRunner.getP(0).getHP() + "    Location: " + GameRunner.getP(0).getPos() +  
            "\nTank:     HP: " + GameRunner.getP(1).getHP() + "    Location: " + GameRunner.getP(1).getPos() + 
            "\nSniper:   HP: " + GameRunner.getP(2).getHP() + "    Location: " + GameRunner.getP(2).getPos() + 
            "\nMelee:     HP: " + GameRunner.getP(3).getHP() + "    Location: " + GameRunner.getP(3).getPos() + '\n' +
            "\nCop1:      HP: " + GameRunner.getE(0).getHP() + "     Location: " + GameRunner.getE(0).getPos() +
            "\nCop2:      HP: " + GameRunner.getE(1).getHP() + "     Location: " + GameRunner.getE(1).getPos() +
            "\nRobot1:   HP: " + GameRunner.getE(2).getHP() + "     Location: " + GameRunner.getE(2).getPos() + l2 + l3;
    }

    // private static void setTurn(int t){turn = t;}
    public static int getTurn(){return turn;}
    public static void checkAliveEnts() {
        for(int i = 0; i < 4; i++) {
            if(!GameRunner.getP(i).getAlive()){GameRunner.getP(i).setVisible(false);}
        }
    }
    public static void playerDied(Player p) {
        insertMsg("Player " + p.getClass() + " has died");
    }
    public static void enemyDied(Enemy e) {
        insertMsg("Enemy " + e.getClass() + " has died");
    }


}
