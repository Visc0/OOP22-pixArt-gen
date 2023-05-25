package it.unibo.pixArt.model.timer;

public class TimerThread extends Thread{

    private final GameTimer timer;
    private final Runnable callback;
    private final Runnable remainingTime;
    private final long TIME_SLEEP = 0;

    public TimerThread(final GameTimer timer, final Runnable callback, final Runnable remainingTime){
        this.timer = timer;
        this.callback = callback;
        this.remainingTime = remainingTime;
    }

    public void run(){
        while (this.timer.isRunning()){
            remainingTime.run();

            try {
                Thread.sleep(TIME_SLEEP);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        callback.run();
    }
    
}
