import java.util.ArrayList;

public class GameController {
    public static int setLocation(int c, String[] args) { // Used to set the location of a Player object. Method is run when user types "move to" followed by a point into the console.
        if(args==null){return -1;}//return -1 if the command does not start with "move to" (edge case where nothing after "move")
        if(!args[0].equalsIgnoreCase("move") && !args[1].equalsIgnoreCase("to")){return -1;}//returns -1 if the command does not start with "move to"
        String[] loc = GameRunner.getP(c).getLoc().split(" "); // Splits the cords of the Player object "c" from a string into two ints. So "112 148" would turn into X = 112; Y = 148
        int oldX = Integer.parseInt(loc[0]);
        int oldY = Integer.parseInt(loc[1]);
        int speed = GameRunner.getP(c).getSpeed();

        
        
        try{
            int[][] idk = GameController.getEntities(); // Gets the location of each all walls, all Player objects and all Enemy objects and puts them into an 2d array of ints. 
            int y = Character.getNumericValue(args[2].charAt(1))*112; // Parses the X and Y value from the Args variable from the gameboard cords to numerical cords. Ex. A1 turns into 112 and 148.
            int x = (Character.getNumericValue(args[2].charAt(0))-9)*112;
            if(idk[x/112][y/112]>0 || idk[x/112][y/112] == -1){return -3;} // return -3 if the location is currently being occupied by another Player or Enemy object, or a wall. 
            else if(Math.abs(x-oldX)>(112*speed)||Math.abs(y-oldY)>(36+112*speed)){return -2;}//return -2 if location is not valid
            if(console.getHasMoved()){return -4;}
            if (x >= 112 && y >= 112 && x < 1344 && y < 896) { // Checks to see if the location inputted is within the limits of the gameboard.
                GameRunner.setLocation(c, x, y+36);
                GameRunner.removeGrid();
                GameRunner.drawGrid();
            }
        } catch (Exception ex){
            System.out.println("NumberFormatException: " + ex);
            return -5; // Returns only if the user has inputted a position that doesn't exist.
        }
        return 1;
    }

    public static int setLocationE(int c, int x, int y) { // Does similar things as the previous method, but the values are not from what the user entered but are generated automatically with an algorithm. See EnemyController class for more info.
        String[] loc = GameRunner.getE(c).getLoc().split(" ");
        int oldX = Integer.parseInt(loc[0]);
        int oldY = Integer.parseInt(loc[1]);
        int speed = GameRunner.getE(c).getSpeed();

        try{
            int[][] idk = GameController.getEntities();
            if(idk[x/112][y/112]>0 || idk[x/112][y/112] == -1){return -3;} else
            if(Math.abs(x-oldX)>(112*speed)||Math.abs(y-oldY)>(36+112*speed)){return -2;}//return -2 if location is not valid
            if (x >= 112 && y >= 112 && x < 1344 && y < 896) {
                GameRunner.setLocationE(c, x, y);
                GameRunner.removeGrid();
                GameRunner.drawGrid();
            }
        } catch (Exception ex){
            System.out.println("NumberFormatException: " + ex);
            return -5;
        }
        return 1;
    }

    public static int[][] getEntities(){ // Returns an int array of all entities on the board. An entity can be a Player object, Enemy Object or wall (not an object). 
        int[][] returnArr = new int[13][9];
        String[][] stringGrid = createGrid();
        String [] playerLocations = getPLocations();
        String [] enemyLocations = getELocations();
        String [] wallLocations = null;;
        // Makes sure the right wallLocations are used depending on the current level.
        if(Level.getCurrentLevel()==1){wallLocations = Level.getLevel1Cords();} 
        else if(Level.getCurrentLevel()==2){wallLocations = Level.getLevel2Cords();}
        else if(Level.getCurrentLevel()==3){wallLocations = Level.getLevel3Cords();}
        // Traverses the entirety of returnArr until it is full.
        for(int i = 0; i < returnArr.length; i++){
            for(int k = 0; k < returnArr[0].length; k++){
                for(String s:playerLocations){ // Traverses the playerLocations. If any equal any position on the grid, it makes that position in the returnArr 1.
                    if(s.equals(stringGrid[i][k])){returnArr[i][k] = 1;}
                } for(int j=0; j<enemyLocations.length;j++){ // Similar as previous comment, but each enemy object is given its own number from 2-7
                    if(enemyLocations[j].substring(2).equals(stringGrid[i][k])){returnArr[i][k] = j+2;}
                } for(String s:wallLocations){ // if there is a wall, sets that location in the 2d to -1.  
                    if(s.equals(stringGrid[i][k])){returnArr[i][k] = -1;}
                }
            }
        }
        return returnArr;//b2, e5
    }

    public static int getPosition(int posX, int posY){ // Gets what number is in the inputted values.
        int[][] grid = getEntities();
        return grid[posX][posY];
    }

    public static String[] getPLocations(){ // get all of the locations of all of the Player objects. 
        ArrayList<String> arr = new ArrayList<String>();
        for(int i=0; i<4; i++){
            if(GameRunner.getP(i).getAlive()){arr.add(GameRunner.getP(i).getLoc());}
        }
        String[] ret = new String[arr.size()];
        for(int i=0;i<arr.size();i++){
            ret[i] = arr.get(i);
        }
        return ret;
    }

    public static String[] getELocations(){ // gets all of the locations of all of the Enemy objects. 
        int numEnemies = 3;
        if (Level.getCurrentLevel() == 1){numEnemies = 3;} // Makes sure it method is getting the right positions for the right level since some enemies don't show on each level. 
        if (Level.getCurrentLevel() == 2){numEnemies = 4;}
        if (Level.getCurrentLevel() == 3){numEnemies = 5;}
        ArrayList<String> arr = new ArrayList<String>();
        for(int i=0;i<numEnemies;i++){
            if(GameRunner.getE(i).getAlive()){ arr.add((i+1) + " " + GameRunner.getE(i).getLoc());}
        }
        String[] ret = new String[arr.size()];
        for(int i=0;i<arr.size();i++){
            ret[i] = arr.get(i);
            // System.out.println(ret[i]);
        }
        return ret;
    }

    public static String[][] createGrid() { // Creates a 13x9 grid for the game. Although the gameboard is a technically a 11x7 grid, the 
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


    public static int attack(int c, String[] args, String text){ // Similar concept to the setPosition method at the top of this class. 
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
            if(console.getHasAttacked()){return -4;}
            if(!LineOfSight.canAttack(oldX, oldY, x, y)) {return -5;} // If character doesn't have Line of Sight to target return fail state of -5
            if(idk[x/112][y/112]==1){return -3;} else//if there is someone at new x and y then return fail state of -3
            if (x >= 112 && y >= 112 && x < 1344 && y < 896) {//checks if within bounds
                if(GameRunner.getP(c).getPClass().equals("MELEE") && Math.abs(x-oldX)>112 && Math.abs(y-oldY)>112){ //if the char is melee. !!!!! DOESN'T WORK !!!!!
                    return -2;//if enemy is too far away from char return fail state of -2
                }
                int rand = (int)(Math.random()*100+1);//gen random number of 1-100
                if(rand<acc){//if random number is lower than accuracy: attack
                    switch(GameRunner.getP(c).getPClass()){
                        case "ASSAULT":
                            enemyAt(x, y).setHP(enemyAt(x, y).getHP()-10);
                            console.insert("You attacked an enemy at "+ enemyAt(x,y).getPos() + " and did 10 damage!", text);
                            if(enemyAt(x, y).getHP() <= 0){enemyAt(x,y).setAlive(false);}
                            break;
                        case "MELEE":
                            enemyAt(x, y).setHP(enemyAt(x, y).getHP()-20);
                            console.insert("You attacked an enemy at "+ enemyAt(x,y).getPos() + " and did 20 damage!", text);
                            if(enemyAt(x, y).getHP() <= 0){enemyAt(x,y).setAlive(false);}
                            break;
                        case "SNIPER":
                            enemyAt(x, y).setHP(enemyAt(x, y).getHP()-30);
                            console.insert("You attacked an enemy at "+ enemyAt(x,y).getPos() + " and did 30 damage!", text);
                            if(enemyAt(x, y).getHP() <= 0){enemyAt(x,y).setAlive(false);}
                            break;
                        case "TANK":
                            enemyAt(x, y).setHP(enemyAt(x, y).getHP()-20);
                            console.insert("You attacked an enemy at "+ enemyAt(x,y).getPos() + " and did 20 damage!", text);
                            if(enemyAt(x, y).getHP() <= 0){enemyAt(x,y).setAlive(false);}
                            break;
                    }
                } else {return -6;}
            }
        } catch (Exception ex){
            System.out.println("NumberFormatException: " + ex);
        }
        return 1;
    }


    public static Enemy enemyAt(int x, int y){ // returns which Enemy object is at the inputted location.
        int[][] totalGrid = GameController.getEntities();
        for(int i=0;i<totalGrid.length;i++){
            for(int k=0;k<totalGrid[0].length;k++){
                int x2 = (i*112);
                int y2 = (k*112+36);

                if((x2==x) && (y2==y) && totalGrid[i][k]>1){
                    return GameRunner.getE(totalGrid[i][k]-2);//totalGrid[i][k]-2
                }
            }
        }
        return null;
    }

    public static int playerAt(int x, int y){  // returns which an int of which player is at the inputted location.
        int[][] totalGrid = GameController.getEntities();
        ArrayList<Integer> pLocs = new ArrayList<Integer>();
        String[] playerLocations = GameController.getPLocations();
        for(int j=0; j<playerLocations.length;j++){
            String[] tempArr = playerLocations[j].split(" ");            
            int xx = Integer.parseInt(tempArr[0]);
            int yy = Integer.parseInt(tempArr[1]);

            pLocs.add(xx);
            pLocs.add(yy);
        }
        for(int i=0;i<totalGrid.length;i++){
            for(int k=0;k<totalGrid[0].length;k++){
                int x2 = (i*112);
                int y2 = (k*112+36);

                if((x2==x) && (y2==y) && totalGrid[i][k]==1){
                    int index = pLocs.indexOf(x)/2;
                    return index;//totalGrid[i][k]-2
                }
            }
        }
        return -1;
    }
    
    public static boolean canContinue() { // Checks to see if all of the enemies are dead in each level. 
        if(Level.getCurrentLevel() == 1) {
            if (GameRunner.e1.getHP()<=0 && GameRunner.e2.getHP()<= 0 && GameRunner.e3.getHP()<=0){return true;}
        } if(Level.getCurrentLevel() == 2) {
            if (GameRunner.e1.getHP()<=0 && GameRunner.e2.getHP()<= 0 && GameRunner.e3.getHP()<=0 && GameRunner.e4.getHP()<=0){return true;}
        } if(Level.getCurrentLevel() == 3) {
            if (GameRunner.e1.getHP()<=0 && GameRunner.e2.getHP()<= 0 && GameRunner.e3.getHP()<=0&& GameRunner.e4.getHP()<=0 && GameRunner.e5.getHP()<=0){return true;}
        }
        return false;
    }
    public static String letterParser(int i) { // Returns the letter that corresponds with the inputted int. Basically it helps to turn the position 11 (not eleven, but One One) into A1.
        switch(i){
            case 1:return "A";
            case 2:return "B";
            case 3:return "C";
            case 4:return "D";
            case 5:return "E";
            case 6:return "F";
            case 7:return "G";
            case 8:return "H";
            case 9:return "I";
            case 10:return "J";
            case 11:return "K";
            case 12:return "L";
            default:return null;
        }
    } 
    
}