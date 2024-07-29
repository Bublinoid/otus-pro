package cz.bublinoid;

import cz.bublinoid.threadpool.ThreadPool;

public class Main {
    public static void main(String[] args) {

        ThreadPool pool = new ThreadPool(5);

        for (int i = 0; i < 10; i++) {
            int taskNumber = i;
            pool.execute(() -> {
                System.out.println("Task " + taskNumber + " is being executed by thread: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        pool.shutdown();
    }
}