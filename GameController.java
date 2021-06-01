public class GameController {
    public static int setLocation(int c, String[] args) {
        if(args==null){return -1;}
        if(!args[0].equalsIgnoreCase("move") && !args[1].equalsIgnoreCase("to")){
            return -1;
        }
        int a = 1;
        for(int i=0; i<args.length; i++){//finds location of "to" so that 
            if(args[i].equals("to")){
                a = i;
            }
        }

        try{
            int y = Character.getNumericValue(args[2].charAt(1))*112;
            int x = (Character.getNumericValue(args[2].charAt(0))-9)*112;
            if (x >= 112 && y >= 112 && x < 1344 && y < 896) {
                GameRunner.setLocation(c, x, y+36);
            }
        } catch (Exception ex){
            System.out.println("NumberFormatException: " + ex);
        }
        return 1;
    }
}