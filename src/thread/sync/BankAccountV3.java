package thread.sync;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BankAccountV2 implements BankAccount {
    private int balance = 0;

    public BankAccountV2(int initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public synchronized boolean withDraw(int amount) { //synchronized 하나의 스레드만 실행가능하도록함
        log("거래 시작" + getClass().getSimpleName());

        // 잔고가 출금액 보다 적으면 false
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
        log("거래 종료");
        return true;
    }

    @Override
    public synchronized int getBalance() {
        return balance;
    }
}
