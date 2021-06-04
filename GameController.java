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
            if(idk[x/112][y/112]==1){return -3;} else
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
        int[][] returnArr = new int[13][9];
        String[][] stringGrid = createGrid();
        String [] playerLocations = getLocations();


        for(int i = 0; i < returnArr.length; i++){
            for(int k = 0; k < returnArr[0].length; k++){
                if(playerLocations[0].equals(stringGrid[i][k])){
                    // System.out.println("1: " + k + " " + i);
                    returnArr[i][k] = 1;
                }
                if(playerLocations[1].equals(stringGrid[i][k])){
                    // System.out.println("2: " + k + " " + i);
                    returnArr[i][k] = 1;
                }
                if(playerLocations[2].equals(stringGrid[i][k])){
                    // System.out.println("3: " + k + " " + i);
                    returnArr[i][k] = 1;
                }
                if(playerLocations[3].equals(stringGrid[i][k])){
                    // System.out.println("4: " + k + " " + i);
                    returnArr[i][k] = 1;
                }
            }
        }
        return returnArr;//b2, e5
    }

    public static int getPosition(int posX, int posY){
        int[][] grid = getEntities();
        return grid[posX][posY];
    }

    public static String[] getLocations(){
        String[] ret = new String[4];
        for(int i=0; i<4; i++){
            ret[i] = GameRunner.getP(i).getLoc();
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

    
}