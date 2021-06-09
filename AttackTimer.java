public class AttackTimer extends Thread{
    private int e;

    AttackTimer(int e){
        this.e = e;
    }

    public void run() {
        long cTime = System.currentTimeMillis()+2000 ;//creates value of the current time in milliseconds 
                switch(e) {//switch statement to check to see which enemy is attacking and run their respective methods
                    case 0: 
                        EnemyController.copAttack();
                        break;
                    case 1: 
                        EnemyController.copAttack();
                        break;
                    case 2: 
                        EnemyController.robotAttack();
                        break;
                    case 3: 
                        EnemyController.robotAttack();
                        break;
                    case 4: 
                        EnemyController.boomerAttack();
                        break;
                }
            while (System.currentTimeMillis() != cTime){System.out.println("g");};
            EnemyController.failureState();
            System.out.println("it works hopefully");
    }
}
