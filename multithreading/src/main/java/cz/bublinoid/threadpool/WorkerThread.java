package cz.bublinoid.threadpool;

import java.util.LinkedList;

public class WorkerThread extends Thread {

    private final LinkedList<Runnable> taskQueue;
    private final Object lock;

    public WorkerThread(LinkedList<Runnable> taskQueue, Object lock) {
        this.taskQueue = taskQueue;
        this.lock = lock;
    }

    @Override
    public void run() {
        while (true) {
            Runnable task;
            synchronized (lock) {
                while (taskQueue.isEmpty()) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                task = taskQueue.removeFirst();
            }
            task.run();
        }
    }

}
