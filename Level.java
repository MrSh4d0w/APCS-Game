import java.util.*;

public class Level {
    private static int currentLevel;
    private static boolean gameOver;
    
    public Level(int c) {
        currentLevel = c;
    }
    public static String[] getLevel1Cords() {
        String[] ret = new String[16]; // Yes this is inefficient, but it works.
        ret[0] = "448 148";
        ret[1] = "112 260";
        ret[2] = "224 260";
        ret[3] = "448 260";
        ret[4] = "784 372";
        ret[5] = "896 372";
        ret[6] = "1008 372";
        ret[7] = "224 484";
        ret[8] = "448 484";
        ret[9] = "1008 484";
        ret[10] = "224 596";
        ret[11] = "448 596";
        ret[12] = "1008 596";
        ret[13] = "1232 596";
        ret[14] = "224 708";
        ret[15] = "1008 708";
        return ret;
    }
    public static String[] getLevel2Cords() {
        String[] ret = new String[20]; // Yes this is inefficient, but it works.
        ret[0] = "336 148";
        ret[1] = "448 148";
        ret[2] = "896 148";
        ret[3] = "1008 148";
        ret[4] = "560 260";
        ret[5] = "784 260";
        ret[6] = "112 372";
        ret[7] = "224 372";
        ret[8] = "336 372";
        ret[9] = "1008 372";
        ret[10] = "1120 372";
        ret[11] = "1232 372";
        ret[12] = "336 484";
        ret[13] = "1008 484";
        ret[14] = "112 708";
        ret[15] = "224 708";
        ret[16] = "336 708";
        ret[17] = "1008 708";
        ret[18] = "1120 708";
        ret[19] = "1232 372";
        return ret;
    }
    public static String[] getLevel3Cords() {
        String[] ret = new String[11]; // Yes this is inefficient, but it works.
        ret[0] = "224 148";
        ret[1] = "448 260";
        ret[2] = "560 260";
        ret[3] = "896 260";
        ret[4] = "224 372";
        ret[5] = "1120 372";
        ret[6] = "1120 596";
        ret[7] = "224 708";
        ret[8] = "336 708";
        ret[9] = "784 708";
        ret[10] = "784 820";
        return ret;
    }
    
    public static int getCurrentLevel() {return currentLevel;}
    public static void setCurrentLevel(int i){currentLevel = i;}

}
