public class AttackTimer extends Thread{
    private int e;

    AttackTimer(int e){
        this.e = e;
    }

    public void run() {
        
                switch(e) {//switch statement to check to see which enemy is attacking and run their respective methods
                    case 0: 
                        EnemyController.a(0);
                        break;
                    case 1: 
                        EnemyController.a(0);
                        break;
                    case 2: 
                        EnemyController.a(1);
                        break;
                    case 3: 
                        EnemyController.a(1);
                        break;
                    case 4: 
                        EnemyController.a(2);
                        break;
                }
    }
}
