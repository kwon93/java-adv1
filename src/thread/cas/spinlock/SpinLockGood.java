package thread.cas.spinlock;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class SpinLockBad {
    private volatile boolean lock = false;

    public void lock() {
        log("lock 획득 시도");
        while (true) {
            if (!lock) { //원자적이지않은 락 사용 여부
                sleep(100); // 문제 상황 확인용, 스레드 대기
                lock = true; //원자적이지않은 락의 값 변경
                break;
            } else {
                // lock 획득할 때 까지 spin 대기
                log("lock 획득 실패 = 스핀 대기 ");
            }
        }
        log("lock 획득 완료");
    }

    public void unlock() {
        lock = false;
        log("lock 반납 완료");
    }
}
