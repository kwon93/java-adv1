package thread.control.interrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV2 {
    public static void main(String[] args) {

        MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");
        thread.start();

        sleep(4000);
        log("작업 중단 지시 thread.interrupt");
        thread.interrupt();
        log("interrupt");


    }

    static class MyTask implements Runnable {


        @Override
        public void run() {
            try {
                while (true) {
                    log("작업중");
                    Thread.sleep(3000);
                }
            } catch (InterruptedException e) {
                log("work thread interrupt2 : " + Thread.currentThread().isInterrupted());
                log("interrupt message : " + e.getMessage());
            }
            log("자원 정리");
            log("자원 종료");
        }
    }
}
