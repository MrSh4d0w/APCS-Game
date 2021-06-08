import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class EnemyController {
    private static int playerX, playerY, enemyX, enemyY;
    private static Timer timer = new Timer();
    private static int seconds = 0;

    public static void boomerAction(int c){
        String[] enemyLoc = GameRunner.getE(c).getLoc().split(" ");
        enemyX = Integer.parseInt(enemyLoc[0]);
        enemyY = Integer.parseInt(enemyLoc[1]);
        getClosestPlayer();

        if(Math.abs(enemyX-playerX)<=112 && Math.abs(enemyY-playerY)<=112){
            boomerAttack();
        } else {
            if(enemyY-playerY>0){GameController.setLocationE(c, enemyX, (enemyY-112));}
            else if(enemyY-playerY<0){GameController.setLocationE(c, enemyX, enemyY+112);}//move left
            if(enemyX-playerX>0){GameController.setLocationE(c, enemyX-112, enemyY);}//move down
            else if (enemyX-playerX<0){GameController.setLocationE(c, enemyX+112, enemyY);}//move up
        }

        // System.out.println(playerX + " " + playerY + " " + enemyX + " " + enemyY);
   //move to closest player's y pos then x pos then blow up when 1 square away
    }
    public static void attackTimer(int e){
        TimerTask task;
        task = new TimerTask() {
            private final int MAX_SECONDS = 500;
    
            @Override
            public void run() { 
                System.out.println("seconds" + seconds);
                if (seconds < MAX_SECONDS) {
                    if(seconds==2){
                        switch(e) {
                            case 0: copAttack();break;
                            case 1: copAttack();break;
                            case 2: robotAttack();break;
                            case 3: robotAttack();break;
                            case 4: boomerAttack();break;
                        }
                    }         
                    seconds++;
                } else {cancel();}
            }

        };
        timer.schedule(task, 0, 1000);
    }
    
    private static void boomerAttack() {
        console.insert("Boomer is attacking a player at " + GameController.letterParser(playerX/112) + (playerY-36)/112);
        int c = GameController.playerAt(playerX, playerY);
        GameRunner.getP(c).setHP(GameRunner.getP(c).getHP()-100);
    }
    private static void copAttack() {
        console.insert("Boomer is attacking a player at " + GameController.letterParser(playerX/112) + (playerY-36)/112);
        int c = GameController.playerAt(playerX, playerY);
        GameRunner.getP(c).setHP(GameRunner.getP(c).getHP()-100);
    }
    private static void robotAttack() {
        console.insert("Boomer is attacking a player at " + GameController.letterParser(playerX/112) + (playerY-36)/112);
        int c = GameController.playerAt(playerX, playerY);
        GameRunner.getP(c).setHP(GameRunner.getP(c).getHP()-100);
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
        copAttack();
   //stay x grids away from player and shoot
    }

    public static void robotAction(){robotAttack();}

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

    public static void wait(int s) {
        TimerTask task;
        task = new TimerTask() {
            private final int MAX_SECONDS = s;
    
            @Override
            public void run() { 
                System.out.println("seconds" + seconds);
                if (seconds < MAX_SECONDS) {seconds++;} else {cancel();}
            }
        };
        timer.schedule(task, 0, 1000);
    }
}
