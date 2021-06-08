import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class EnemyController {
    private static int playerX, playerY, enemyX, enemyY;
    private static Timer timer = new Timer();
    private static int seconds = 0;
    private static boolean failState;
    private static String enemy;
    private static int damage;

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
        TimerTask task;
        task = new TimerTask() {
            private final int MAX_SECONDS = 5;
    
            @Override
            public void run() { 
                System.out.println("seconds" + seconds);
                if (seconds <= MAX_SECONDS) {
                    if(seconds==0){
                        switch(e) {
                            case 0: 
                                copAttack();
                                break;
                            case 1: 
                                copAttack();
                                break;
                            case 2: 
                                robotAttack();
                                break;
                            case 3: 
                                robotAttack();
                                break;
                            case 4: 
                                boomerAttack();
                                break;
                        }
                    }
                    if(seconds==5){
                        failureState();
                    }
                    seconds++;
                } else {cancel();}
            }

        };
        timer.schedule(task, 0, 1000);
    }

    private static void failureState(){
        if(!failState){
            console.insertMsg("The " + enemy + " has failed in hitting you!");
        } else {
            console.insertMsg("The " + enemy + " has hit you! You have taken " + damage + " damage!");
        }
    }
    
    private static void boomerAttack() {
        console.insertMsg("Boomer is attacking a player at " + GameController.letterParser(playerX/112) + (playerY-36)/112);
        int c = GameController.playerAt(playerX, playerY);
        GameRunner.getP(c).setHP(GameRunner.getP(c).getHP()-100);
    }
    private static void copAttack() {
        console.insertMsg("Cop is attacking a player at " + GameController.letterParser(playerX/112) + (playerY-36)/112);
        int c = GameController.playerAt(playerX, playerY);
        int rand = (int)(Math.random()*100+1);
        if(rand>50){
            failState = false;
            enemy = "Cop";
            damage = 10;
        } else {
            failState = true;
            enemy = "Cop";
            damage = 0;
        }
        GameRunner.getP(c).setHP(GameRunner.getP(c).getHP()-100);
    }
    private static void robotAttack() {
        console.insertMsg("Robot is attacking a player at " + GameController.letterParser(playerX/112) + (playerY-36)/112);
        int c = GameController.playerAt(playerX, playerY);
        int rand = (int)(Math.random()*100+1);
        if(rand>50){}
        GameRunner.getP(c).setHP(GameRunner.getP(c).getHP()-100);
    }



    public static void getClosestPlayer(){
        ArrayList<Integer> pLocs = new ArrayList<Integer>();
        String[] playerLocations = GameController.getPLocations();
        for(int i=0; i<playerLocations.length;i++){
            String[] tempArr = playerLocations[i].split(" ");            
            int x = Integer.parseInt(tempArr[0]);
            int y = Integer.parseInt(tempArr[1]);

            pLocs.add(x);
            pLocs.add(y);
        }

        int yMin = Integer.MAX_VALUE;
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
}
