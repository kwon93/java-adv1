package thread.sync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BankAccountV5 implements BankAccount {
    private int balance = 0;
    private final Lock lock = new ReentrantLock();

    public BankAccountV5(int balance) {
        this.balance = balance;
    }

    @Override
    public boolean withDraw(int amount) { //synchronized 하나의 스레드만 실행가능하도록함
        log("거래 시작" + getClass().getSimpleName());

        if (!lock.tryLock()) {
            log("진입 실패 -> 이미 처리중인 작업이 있습니다.");
            return false;
        }

        try {
            log("검증 시작 [출금액] :" + amount + " [잔액] :" + balance);
            if (balance < amount) {
                log("검증 실패 [출금액] :" + amount + " [잔액] :" + balance);
                return false;
            }

            // 잔고가 출금액보다 많으면 true
            log("검증 완료 [출금액] :" + amount + " [잔액] :" + balance);
            sleep(1000);
            balance -= amount;
            log("출금 완료 [출금액] :" + amount + " [잔액] :" + balance);
        } finally {
            lock.unlock();
        }

        log("거래 종료");
        return true;
    }

    @Override
    public int getBalance() {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }
}
