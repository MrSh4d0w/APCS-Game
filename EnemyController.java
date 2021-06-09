import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class EnemyController {
    private static int playerX, playerY, enemyX, enemyY;
    private static int seconds = 0;
    private static boolean CopFail, RobFail;
    private static String enemy;
    private static int damageCop, damageRob;
    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    public static void boomerAction(int c){
        String[] enemyLoc = GameRunner.getE(c).getLoc().split(" ");
        enemyX = Integer.parseInt(enemyLoc[0]);
        enemyY = Integer.parseInt(enemyLoc[1]);
        getClosestPlayer();

        if(Math.abs(enemyX-playerX)<=112 && Math.abs(enemyY-playerY)<=112){
            attackTimer(c);
        } else {
            if(enemyY-playerY>0){GameController.setLocationE(c, enemyX, (enemyY-112));}
            else if(enemyY-playerY<0){GameController.setLocationE(c, enemyX, enemyY+112);}//move left
            if(enemyX-playerX>0){GameController.setLocationE(c, enemyX-112, enemyY);}//move down
            else if (enemyX-playerX<0){GameController.setLocationE(c, enemyX+112, enemyY);}//move up
        }

        // System.out.println(playerX + " " + playerY + " " + enemyX + " " + enemyY);
   //move to closest player's y pos then x pos then blow up when 1 square away
    }
    public static void copAction(int c){
        String[] enemyLoc = GameRunner.getE(c).getLoc().split(" ");
        enemyX = Integer.parseInt(enemyLoc[0]);
        enemyY = Integer.parseInt(enemyLoc[1]);
        getClosestPlayer();

        if(Math.abs(enemyX-playerX)<=112*3 || Math.abs(enemyY-playerY)<=112*3){
            if(enemyY-playerY<0){GameController.setLocationE(c, enemyX, enemyY+112);}//move right
            else if(enemyY-playerY>0){GameController.setLocationE(c, enemyX, enemyY-112);}//move left
            else if(enemyY-playerY == 0){GameController.setLocationE(c, enemyX, enemyY-112);}

            if(enemyX-playerX<0){GameController.setLocationE(c, enemyX-112, enemyY);}//move down
            else if(enemyX-playerX>0){GameController.setLocationE(c, enemyX+112, enemyY);}//move up
            else if(enemyX-playerX == 0){GameController.setLocationE(c, enemyX+112, enemyY);}
        }
        attackTimer(c);
   //stay x grids away from player and shoot
    }
    public static void robotAction(int c){attackTimer(c);}
    public static void attackTimer(int e){
        /*
        For this next section, we had an issue where we needed to wait a certain amount of time,
        but everything we tried made the game hang for that amount of time. So, instead, I (Alex),
        though of using Threads. Threads are a way for a program to do multiple things asynchronously
        (simultaneously). The way the following code works is that it creates a new thread and uses 
        a lambda expression to instantiate it to whatever code we need. The lambda expression format
        is a short way of creating a single use function where (parameters) -> {code} is the format.
        Threads and asynchronous coding is difficult but we lucked out and managed to implement a 
        simple thread to fit our purposes exactly. Also, we didn't know how threads until tuesday the week the project was due. Sorry.
        */
        AttackTimer thread = new AttackTimer(e);
        thread.start();
    }

    static void failureState(){ // Output if the enemy either fails or succeeds at hitting a Player object.
        if(RobFail && damageRob==0){
                    RobFail = true;
                    damageRob = 0;
                    if(!LineOfSight.canAttack(enemyX, enemyY, playerX, playerY)){
                        console.insertMsg("The rob has failed to hit you because of an obstruction");
                    } else {
                        console.insertMsg("The rob has failed to hit you!");
                    }
                    
        } else if (!RobFail && damageRob!=0){
                    console.insertMsg("The " + enemy + " has hit you! You have taken " + damageRob + " damage!");        } else if(CopFail && damageCop!=0){
                    RobFail = true;
                    damageRob = 0;
                        if(!LineOfSight.canAttack(enemyX, enemyY, playerX, playerY)){
                        console.insertMsg("The cop has failed to hit you because of an obstruction");
                    } else {
                        console.insertMsg("The cop has failed to hit you!");
                    }
                    
        } else if (CopFail && damageCop==0){
                    console.insertMsg("The cop has failed to hit you");
                    CopFail = true;
                    damageCop = 0;
        } else if(!CopFail && damageCop!=0){
                    console.insertMsg("The cop has hit you! You have taken " + damageCop + " damage!");
                    CopFail = true;
                    damageCop = 0;
        }
    }


    static synchronized void a(int c){
        switch (c){
            case 0:
                copAttack();
                break;
            case 1:
                robotAttack();
                break;
            case 2:
                boomerAttack();
                break;
        }
    }




    
    static void boomerAttack() { // Attack for Boomer. 
        console.insertMsg("The boomer at position " + GameController.letterParser(enemyX/112) + (enemyY-36)/112 + " is attacking a player at " + GameController.letterParser(playerX/112) + (playerY-36)/112);
        int c = GameController.playerAt(playerX, playerY);
        GameRunner.getP(c).setHP(GameRunner.getP(c).getHP()-100); // Takes current HP of Player object it is attacking and takes 100 HP away from it, instantly killing it
    }
    static void copAttack() { // Attack for Cop objects
        console.insertMsg("The cop at position " + GameController.letterParser(enemyX/112) + (enemyY-36)/112 + " is attacking a player at " + GameController.letterParser(playerX/112) + (playerY-36)/112);
        int c = GameController.playerAt(playerX, playerY);
        
        if(LineOfSight.canAttack(enemyX, enemyY, playerX, playerY)){
            int rand = (int)(Math.random()*100+1);
            
            if(rand>50){ // Randomization for attack. 50/50 chance of the enemy attacking you and hitting you.
                CopFail = false;
                damageCop = rand/10;
            } else {
                CopFail = true;
                enemy = "Cop";
                damageCop = 0;
            }
            
            
        }
        
        long cTime2 = System.currentTimeMillis()+2000 ;//creates value of the current time in milliseconds 
        while (System.currentTimeMillis() != cTime2){System.out.println("g");};
        EnemyController.failureState();
        System.out.println("it works hopefully");
    }
    static void robotAttack() { // Attack for robot objects
        console.insertMsg("The robot at position " + GameController.letterParser(enemyX/112) + (enemyY-36)/112 + " is attacking a player at " + GameController.letterParser(playerX/112) + (playerY-36)/112);
        int c = GameController.playerAt(playerX, playerY);
        
        if(LineOfSight.canAttack(enemyX, enemyY, playerX, playerY)){
            int rand = (int)(Math.random()*100+1);
            
            if(rand>50){ // Randomization for attack. 50/50 chance of the enemy attacking you and hitting you.
                RobFail = false;
                enemy = "Robot";
                damageRob = (rand/10)+5;
            } else {
                RobFail = true;
                enemy = "Robot";
                damageRob = 0;
            }
            
            
        }
        
        long cTime = System.currentTimeMillis()+2000 ;//creates value of the current time in milliseconds 
        while (System.currentTimeMillis() != cTime){System.out.println("g");};
        EnemyController.failureState();
        System.out.println("it works hopefully");
    }



    public static void getClosestPlayer(){ // Gets the closest player from the Enemy object it is being run on. 
        ArrayList<Integer> pLocs = new ArrayList<Integer>();
        String[] playerLocations = GameController.getPLocations();
        for(int i=0; i<playerLocations.length;i++){
            String[] tempArr = playerLocations[i].split(" ");            
            int x = Integer.parseInt(tempArr[0]);
            int y = Integer.parseInt(tempArr[1]);

            pLocs.add(x);
            pLocs.add(y);
        } // Puts all of the player locations into an arraylist. Each index in the arraylist is EITHER an X or Y value. 

        int yMin = Integer.MAX_VALUE; // I don't know what this does LMAO.
        int yIndex = 0;
        for(int i=1; i<8;i+=2){
            if(Math.abs(pLocs.get(i)-enemyY) < yMin){
                yMin = Math.abs(pLocs.get(i)-enemyY);
                yIndex = i;
            }
        }
        int xMin = Integer.MAX_VALUE;
        int xIndex = 0;
        for(int i=0; i<8;i+=2){
            if(Math.abs(pLocs.get(i)-enemyX) < xMin){
                xMin = Math.abs(pLocs.get(i)-enemyX);
                xIndex = i;
            }
        }

        if(pLocs.get(xIndex)<pLocs.get(yIndex)){
            playerX = pLocs.get(xIndex);
            playerY = pLocs.get(xIndex+1);
        }
        else if(pLocs.get(yIndex)<pLocs.get(xIndex)){
            playerX = pLocs.get(yIndex-1);
            playerY = pLocs.get(yIndex);
        }
        else if(pLocs.get(yIndex).equals(pLocs.get(xIndex))){
            playerX = pLocs.get(xIndex);
            playerY = pLocs.get(xIndex+1);
        }

        System.out.println(playerX + " " + playerY + " " + enemyX + " " + enemyY);
    }

    public static void attack(){
        copAction(0);
        copAction(1);
        robotAction(2);
        if(Level.getCurrentLevel()==2 || Level.getCurrentLevel()==3) {robotAction(3);}
        if(Level.getCurrentLevel()==3){boomerAction(4);}
    }
}
