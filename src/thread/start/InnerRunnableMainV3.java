package thread.start;

import static util.MyLogger.log;

public class InnerRunnableMainV2 {
    public static void main(String[] args) {

        log("main() start");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                log("my run2()");
            }
        });
        thread.start();

        log("main() end");
    }

}
