package thread.executor.poolsize;

import thread.executor.ExecutorUtils;
import thread.executor.RunnableTask;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static thread.executor.ExecutorUtils.*;
import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class PoolSizeMainV1 {
    public static void main(String[] args) {
        ArrayBlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
        ThreadPoolExecutor es = new ThreadPoolExecutor(2, 4, 3000, TimeUnit.MILLISECONDS, workQueue);
        printState(es);

        es.execute(new RunnableTask("task-1"));
        printState(es, "task-1");
        es.execute(new RunnableTask("task-2"));
        printState(es, "task-2");
        es.execute(new RunnableTask("task-3"));
        printState(es, "task-3");
        es.execute(new RunnableTask("task-4"));
        printState(es, "task-4");
        es.execute(new RunnableTask("task-5"));
        printState(es, "task-5");
        es.execute(new RunnableTask("task-6"));
        printState(es, "task-6");

        try {
            es.execute(new RunnableTask("task-7"));
            printState(es, "task-7");
        } catch (RejectedExecutionException e) {
            log("task-7 실행거절 예외발생 : " + e);
        }

        sleep(3000);
        printState(es);
        sleep(3000);
        log("== maximum pool size 대기시간 초과");
        printState(es);

        es.close();
    }
}
