package cz.bublinoid.threadpool;

import java.util.LinkedList;

public class ThreadPool {
    private final WorkerThread[] threads;
    private final LinkedList<Runnable> taskQueue;
    private final Object lock;
    private boolean isShutdown;

    public ThreadPool(int capacity) {
        this.threads = new WorkerThread[capacity];
        this.taskQueue = new LinkedList<>();
        this.lock = new Object();
        this.isShutdown = false;

        for (int i = 0; i < capacity; i++) {
            threads[i] = new WorkerThread(taskQueue, lock);
            threads[i].start();
        }
    }

    public void execute(Runnable task) {
        synchronized (lock) {
            if (isShutdown) {
                throw new IllegalStateException("ThreadPool is shutdown. No new tasks can be accepted.");
            }
            taskQueue.addLast(task);
            lock.notify();
        }
    }

    public void shutdown() {
        synchronized (lock) {
            isShutdown = true;

            lock.notifyAll();

            for (WorkerThread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
