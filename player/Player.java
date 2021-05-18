public class Player {
    private int HP, locX, locY;
    public enum status {NONE,FROZEN,BURNING}
    public enum direction {UP,DOWN,LEFT,RIGHT}
    private status s;

    public Player(int HP, int locX, int locY, status s) {
        this.HP = HP;
        this.locX = locX;
        this.locY = locY;
        this.s = s;
    }

    private int getLocationX(){return locX;}// returns location of Player object x
    private int getLocationY(){return locY;}// returns location of Player object y
    private void setLocationX(int x){locX = x;}// sets location of Player object x
    private void setLocationY(int y){locY = y;}// sets location of Player object y

    private int getHP(){return HP;}// returns HP of Player object
    private void setHP(int HP){this.HP = HP;}// sets HP of Player object
    private void removeHP(int x){HP-=x;}// removes x HP of Player object
    private void addHP(int x){HP+=x;}// adds x HP of Player object

    private status getStatus(){return s;}// returns status of Player object
    private void setStatus(status s){this.s = s;}

    public void move(direction dir, int amnt){}// move char [amnt] tiles in [dir] direction
}