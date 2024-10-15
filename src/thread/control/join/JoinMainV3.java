package thread.control.join;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class JoinMainV2 {
    public static void main(String[] args) throws InterruptedException {

        log("start");
        JoinMainV1.SumTask task1 = new JoinMainV1.SumTask(1, 50);
        JoinMainV1.SumTask task2 = new JoinMainV1.SumTask(51, 100);
        Thread t1 = new Thread(task1, "thread-1");
        Thread t2 = new Thread(task2, "thread-2");

        t1.start();
        t2.start();

        //thread 가 종료될때까지 대기
        log("대기");
        t1.join();
        t2.join();


        log("task1: "+task1.result);
        log("task2: "+task2.result);
        int sumAll = task1.result + task2.result;
        log("sumAll: "+sumAll);

        log("end");
    }

    static class SumTask implements Runnable {
        int startValue;
        int endValue;
        int result;

        public SumTask(int startValue, int endValue) {
            this.startValue = startValue;
            this.endValue = endValue;
        }

        @Override
        public void run() {
            log("작업 시작");
            sleep(2000);
            int sum = 0;
            for (int i = startValue; i <= endValue; i++) {
                sum += i;
            }
            result = sum;
            log("작업 완료: " + result);
        }
    }
}
