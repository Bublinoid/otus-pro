package cz.bublinoid.threadpool;

import java.util.List;

public class WorkerThread extends Thread {

    private final List<Runnable> taskQueue;
    private final Object lock;
    private final ThreadPool threadPool;

    public WorkerThread(List<Runnable> taskQueue, Object lock, ThreadPool threadPool) {
        this.taskQueue = taskQueue;
        this.lock = lock;
        this.threadPool = threadPool;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Runnable task;
                synchronized (lock) {
                    while (taskQueue.isEmpty() && !threadPool.isShutdown()) {
                        lock.wait();
                    }
                    if (threadPool.isShutdown() && taskQueue.isEmpty()) {
                        break;
                    }
                    task = taskQueue.remove(0);
                }
                try {
                    task.run();
                } catch (RuntimeException e) {
                    System.err.println("Thread pool encountered an issue: " + e.getMessage());
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
