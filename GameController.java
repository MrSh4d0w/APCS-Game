public class GameController {
    public static int setLocation(int c, String[] args) {
        if(args==null){return -1;}//return -1 if the command does not start with "move to" (edge case where nothing after "move")
        if(!args[0].equalsIgnoreCase("move") && !args[1].equalsIgnoreCase("to")){return -1;}//returns -1 if the command does not start with "move to"
        String[] loc = GameRunner.getP(c).getLoc().split(" ");
        int oldX = Integer.parseInt(loc[0]);
        int oldY = Integer.parseInt(loc[1]);
        int speed = GameRunner.getP(c).getSpeed();

        try{
            int y = Character.getNumericValue(args[2].charAt(1))*112;
            int x = (Character.getNumericValue(args[2].charAt(0))-9)*112;
            if(Math.abs(x-oldX)>(112*speed)||Math.abs(y-oldY)>(36+112*speed)){return -2;}//return -2 if location is not valid
            if (x >= 112 && y >= 112 && x < 1344 && y < 896) {
                GameRunner.setLocation(c, x, y+36);
            }
        } catch (Exception ex){
            System.out.println("NumberFormatException: " + ex);
        }
        return 1;
    }

    public int[][] getGrid(){
        int[][] ret = new int[13][9];

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
        
        
        return ret;
    }

    public int getPosition(int posX, int posY){
        int[][] grid = getGrid();
        return grid[posX][posY];
    }
}