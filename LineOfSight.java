import java.util.*;

public class LineOfSight {

    public static boolean canAttack(int startX, int startY, int endX, int endY) {
        ArrayList<String> list = drawLine((startX/112)-1, (startY/112)-1, (endX/112)-1, (endY/112)-1);
        if(Level.getCurrentLevel() == 1){String[] levelPositions = Level.getLevel1Pos();}
        if(Level.getCurrentLevel() == 2){String[] levelPositions = Level.getLevel2Pos();}
        if(Level.getCurrentLevel() == 3){String[] levelPositions = Level.getLevel3Pos();}
        
        String[] levelPositions = Level.getLevel1Pos();
        String[] linePos = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            linePos[i] = list.get(i);
        }
        for(int i = 0; i < levelPositions.length; i++) {
            for(int f = 0; f < linePos.length-1; f++) {
                if (levelPositions[i].equals(linePos[f])) {
                    return false;
                }
            }   
        }
        return true;
        

        /* ArrayList<String> list = drawLine((startX/112)-1, (startY/112)-1, (endX/112)-1, (endY/112)-1);      
        String[][] level1Pos = Level.getLevel1Pos();
        System.out.println(level1Pos[7][4]);
        for(int i = 0; i < list.size(); i++) {
            String[] arr = list.get(i).split(" ");
            int x = Integer.parseInt(arr[i]);
            int y = Integer.parseInt(arr[i+1]);
            if(level1Pos[x][y] == "wall") {
                return false;
            } 
        }        
        return true; */
    } 
    
    public static ArrayList<String> drawLine(int x1, int y1, int x2, int y2) {
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

    // public static boolean isObstructed(ArrayList<String> lineCords) {
    //     for (String s : lineCords) {
    //         for (int i = 0; i < 16; i++) {
    //             if (s.equals(Level.getLevel1Cords()[i])) {
    //                 return true;
    //             }
    //             if (s.equals(Level.getLevel1MidCords()[i])) {
    //                 return true;
    //             }
    //             System.out.println(Level.getLevel1MidCords()[i]);
    //         }
    //     }
    //     return false;
    // }
}

/*
e1  null null wall null null null null null null null
wall wall null wall null null null null null null null 
null null null e3   null null wall wall wall null null
null wall null wall null null null null wall null null
null wall e2   wall null null null p1   wall null wall
null wall null null null null null null wall null p3
null null null null null null null null null p2   p4
 */