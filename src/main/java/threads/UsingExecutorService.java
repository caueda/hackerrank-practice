package threads;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class UsingExecutorService {
    public static void main(String[] args) throws InterruptedException {
        final var executorService = Executors.newScheduledThreadPool(2);
        executorService.scheduleAtFixedRate(() -> {
            System.out.println(Thread.currentThread().getName() +" - Date " + new Date().toString());
        }, 0, 800, TimeUnit.MILLISECONDS);
        executorService.scheduleAtFixedRate(() -> {
            System.out.println(Thread.currentThread().getName() +" - Date " + new Date().toString());
        }, 0, 800, TimeUnit.MILLISECONDS);
        TimeUnit.SECONDS.sleep(10);
    }
}
