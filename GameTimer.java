import java.util.Timer;
import java.util.TimerTask;

public class GameTimer {
    static Timer timer = new Timer();
    static int seconds = 0;

    public static void startTimer() {
        TimerTask task;
        task = new TimerTask() {
            private final int MAX_SECONDS = 5;
    
            @Override
            public void run() { 
                if (seconds < MAX_SECONDS) {
                    seconds++;
                    if(seconds%5==0){wait5();}
                } else {
                    // stop the timer
                    cancel();
                }
            }
        };
         timer.schedule(task, 0, 1000);

    }
    public static void wait5() {
        System.out.println();
    } 
}