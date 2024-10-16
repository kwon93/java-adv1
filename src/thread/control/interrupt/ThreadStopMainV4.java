package thread.control.interrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV3 {
    public static void main(String[] args) {

        MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");
        thread.start();

        sleep(500);
        log("작업 중단 지시 thread.interrupt");
        thread.interrupt();
        log("work thread interrupt1 : " + Thread.currentThread().isInterrupted());
        log("interrupt");


    }

    static class MyTask implements Runnable {

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                log("작업중");
            }
            log("work thread interrupt2 : " + Thread.currentThread().isInterrupted()); //interrupt 상태 유지가 됨
            log("자원 정리");
            log("자원 종료");
        }
    }
}
