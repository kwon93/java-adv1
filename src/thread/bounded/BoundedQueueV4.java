package thread.bounded;

import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BoundedQueueV3 implements BoundedQueue {

    private final Queue<String> queue = new ArrayDeque<>();
    private final int max;

    public BoundedQueueV3(int max) {
        this.max = max;
    }

    @Override
    public synchronized void put(String data) {
        while (queue.size() == max) {
            log("[put] queue 가 가득찼다. 생산자 대기");
            try {
                wait();
                log("[put] 생산자 깨어남");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        queue.offer(data);
        notify();
        log("[take] 생산자 데이터 생산, notify() 호출");
    }

    @Override
    public synchronized String take() {
        while (queue.isEmpty()) {
            log("[take] queue 에 data 가 없음, 소비자 대기");
            try {
                wait();
                log("[take] 소비자 깨어남");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        String data = queue.poll();
        log("[take] 소비자 데이터 획득, notify() 호출");
        notify();
        return data;
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
