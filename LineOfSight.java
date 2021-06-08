import java.util.*;

public class LineOfSight {
    public static boolean canAttack(int startX, int startY, int endX, int endY) {
        ArrayList<String> list = drawLine((startX/112)-1, (startY/112)-1, (endX/112)-1, (endY/112)-1);
        String[] levelPositions = null;

        if(Level.getCurrentLevel() == 1){levelPositions = Level.getLevel1Pos();}
        if(Level.getCurrentLevel() == 2){levelPositions = Level.getLevel2Pos();}
        if(Level.getCurrentLevel() == 3){levelPositions = Level.getLevel3Pos();}
        
        String[] linePos = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            linePos[i] = list.get(i);
        }
        for(int i = 0; i < levelPositions.length; i++) {
            for(int f = 0; f < linePos.length-1; f++) {
                if (levelPositions[i].equals(linePos[f])) {return false;}
            }   
        }
        return true;
    } 
    
    public static ArrayList<String> drawLine(int x1, int y1, int x2, int y2) { // Bresenham's Algorithm. I sorta understand this, but since its widely available online, stealing it should be ok, I think. -N
        // delta of exact value and rounded value of the dependent variable
        ArrayList<String> cords = new ArrayList<String>();
        int d = 0;
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int dx2 = 2 * dx; // slope scaling factors to
        int dy2 = 2 * dy; // avoid floating point
        int ix = x1 < x2 ? 1 : -1; // increment direction
        int iy = y1 < y2 ? 1 : -1;
        int x = x1;
        int y = y1;

        if (dx >= dy) {
            while (true) {
                cords.add(x + " " + y);
                if (x == x2)
                    break;
                x += ix;
                d += dy2;
                if (d > dx) {
                    y += iy;
                    d -= dx2;
                }
            }
        } else {
            while (true) {
                cords.add(x + " " + y);
                if (y == y2)
                    break;
                y += iy;
                d += dx2;
                if (d > dy) {
                    x += ix;
                    d -= dy2;
                }
            }
        }
        return cords;
    }
}

/*
e1wallnullnullnullnullnull
nullwallnullwallwallwallnull
nullnullnullnulle2nullnull
wallwalle3wallwallnullnull
nullnullnullnullnullnullnull
nullnullnullnullnullnullnull
nullnullwallnullnullnullnull
nullnullwallnullnullnullnull
nullnullwallwallwallwallnull
nullnullnullnullnullp1p2
nullnullnullnullwallp3p4
 */