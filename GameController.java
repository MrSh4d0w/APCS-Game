import java.util.ArrayList;

public class GameController {
    public static int setLocation(int c, String[] args) {
        if(args==null){return -1;}//return -1 if the command does not start with "move to" (edge case where nothing after "move")
        if(!args[0].equalsIgnoreCase("move") && !args[1].equalsIgnoreCase("to")){return -1;}//returns -1 if the command does not start with "move to"
        String[] loc = GameRunner.getP(c).getLoc().split(" ");
        int oldX = Integer.parseInt(loc[0]);
        int oldY = Integer.parseInt(loc[1]);
        int speed = GameRunner.getP(c).getSpeed();

        
        
        try{
            int[][] idk = GameController.getEntities();
            int y = Character.getNumericValue(args[2].charAt(1))*112;
            int x = (Character.getNumericValue(args[2].charAt(0))-9)*112;
            if(idk[x/112][y/112]>0){return -3;} else
            if(Math.abs(x-oldX)>(112*speed)||Math.abs(y-oldY)>(36+112*speed)){return -2;}//return -2 if location is not valid
            if (x >= 112 && y >= 112 && x < 1344 && y < 896) {
                GameRunner.setLocation(c, x, y+36);
                GameRunner.removeGrid();
                GameRunner.drawGrid();
            }
        } catch (Exception ex){
            System.out.println("NumberFormatException: " + ex);
        }
        return 1;
    }

    public static int[][] getEntities(){
        int c = 2;
        int[][] returnArr = new int[13][9];
        String[][] stringGrid = createGrid();
        String [] playerLocations = getPLocations();
        String [] enemyLocations = getELocations();


        for(int i = 0; i < returnArr.length; i++){
            for(int k = 0; k < returnArr[0].length; k++){
                for(String s:playerLocations){
                    if(s.equals(stringGrid[i][k])){
                        returnArr[i][k] = 1;
                    }
                }
                for(String s:enemyLocations){
                    if(s.equals(stringGrid[i][k])){
                        returnArr[i][k] = 2;
                        c++;
                        System.out.println(i + " " + k);
                    }
                }
            }
        }
        return returnArr;//b2, e5
    }

    public static int getPosition(int posX, int posY){
        int[][] grid = getEntities();
        return grid[posX][posY];
    }

    public static String[] getPLocations(){
        ArrayList<String> arr = new ArrayList<String>();
        for(int i=0; i<4; i++){
            arr.add(GameRunner.getP(i).getLoc());
        }
        String[] ret = new String[arr.size()];
        for(int i=0;i<arr.size();i++){
            ret[i] = arr.get(i);
        }
        return ret;
    }

    public static String[] getELocations(){
        ArrayList<String> arr = new ArrayList<String>();
        for(int i=0;i<5;i++){
            arr.add(GameRunner.getE(i).getLoc());
        }
        String[] ret = new String[arr.size()];
        for(int i=0;i<arr.size();i++){
            ret[i] = arr.get(i);
            // System.out.println(ret[i]);
        }
        return ret;
    }

    public static String[][] createGrid() {
        String[][] grid = new String[13][9];
        int rowCoords = 0;
        int columnCoords = 0;
        for (int row = 0; row < 13; row++) {
            rowCoords = (row * 112);
            for (int column = 0; column < 9; column++) {
                columnCoords = (column * 112)+36;
                grid[row][column] = rowCoords + " " + columnCoords;
            }
        }
        return grid;
    }


    public static int attack(int c, String[] args){
        if(args==null){return -1;}//return -1 if the command does not start with "move to" (edge case where nothing after "move")
        if(!args[0].equalsIgnoreCase("attack") || args[1].length()>2){return -1;}//returns -1 if the command does not start with "move to"
        
        String[] loc = GameRunner.getP(c).getLoc().split(" ");
        int oldX = Integer.parseInt(loc[0]);
        int oldY = Integer.parseInt(loc[1]);
        int acc = GameRunner.getP(c).getAcc();

        try{
            int[][] idk = GameController.getEntities();
            int y = Character.getNumericValue(args[1].charAt(1))*112+36;
            int x = (Character.getNumericValue(args[1].charAt(0))-9)*112;
            if(idk[x/112][y/112]==1){return -3;} else//if there is someone at new x and y then return fail state of -3
            if (x >= 112 && y >= 112 && x < 1344 && y < 896) {//checks if within bounds
                if(GameRunner.getP(c).getPClass().equals("MELEE")){//if the char is melee
                    if(Math.abs(x-oldX)>112 && Math.abs(y-oldY)>112){return -2;}//if enemy is too far away from char return fail state of -2
                }
                int rand = (int)(Math.random()*100+1);//gen random number of 1-100
                if(rand<acc){//if random number is lower than accuracy: attack
                    switch(GameRunner.getP(c).getPClass()){
                        case "ASSAULT":
                            enemyAt(x, y).setHP(enemyAt(x, y).getHP()-10);
                            break;
                        case "MELEE":
                            enemyAt(x, y).setHP(enemyAt(x, y).getHP()-20);
                            break;
                        case "SNIPER":
                        enemyAt(x, y).setHP(enemyAt(x, y).getHP()-30);
                            break;
                        case "TANK":
                            enemyAt(x, y).setHP(enemyAt(x, y).getHP()-20);
                            break;
                    }
                }
            }
        } catch (Exception ex){
            System.out.println("NumberFormatException: " + ex);
        }
        return 1;
    }


    public static Enemy enemyAt(int x, int y){
        int[][] totalGrid = GameController.getEntities();
        for(int i=0;i<totalGrid.length;i++){
            for(int k=0;k<totalGrid[0].length;k++){
                int x2 = (i*112);
                int y2 = (k*112+36);

                if((x2==x) && (y2==y) && totalGrid[i][k]>1){
                    return GameRunner.getE(0);//totalGrid[i][k]-2
                }
            }
        }
        return null;
    } 
    
}