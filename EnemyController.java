public class EnemyController {
    int playerX, playerY, enemyX, enemyY;

    public static void moveBoomer(){
   
    }

    public static void moveCop(){
   
    }

    public static void moveRobot(){
   
    }

    public static void getClosestPlayer(){
        int[][] entities = GameController.getEntities();
        int[] pLocs = new int[8];
        int dMin = 0;
        int c = 0;
        for(int i=0; i<GameController.getPLocations().length;i++){
            pLocs[i] = Integer.parseInt(GameController.getPLocations()[i]);
        }


    }
}
