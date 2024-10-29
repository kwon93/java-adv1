package thread.cas;

import java.util.concurrent.atomic.AtomicInteger;

import static util.MyLogger.log;

public class CasMainV2 {

    public static void main(String[] args) {

        AtomicInteger atomicInteger = new AtomicInteger(0);
        System.out.println("start value = " + atomicInteger.get());

        int res = incrementAndGet(atomicInteger);
        log("res1 = " + res);

        int res2 = incrementAndGet(atomicInteger);
        log("res2 = " + res2);

        incrementAndGet(atomicInteger);
    }

    private static int incrementAndGet(AtomicInteger atomicInteger) {
        int getValue;
        boolean result;
        do {

            getValue = atomicInteger.get();
            log("getValue: " + getValue);
            result = atomicInteger.compareAndSet(getValue, getValue + 1);
            log("result : " + result);

        } while (!result);
        return getValue + 1;
    }
}
