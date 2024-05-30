package cz.bublinoid.threadpool;

import java.util.LinkedList;

public class ThreadPool {
    private final WorkerThread[] threads;
    private final LinkedList<Runnable> taskQueue;
    private final Object lock;
    private volatile boolean isShutdown;

    public ThreadPool(int capacity) {
        this.threads = new WorkerThread[capacity];
        this.taskQueue = new LinkedList<>();
        this.lock = new Object();
        this.isShutdown = false;

        for (int i = 0; i < capacity; i++) {
            threads[i] = new WorkerThread(taskQueue, lock, this);
            threads[i].start();
        }
    }

    public synchronized void execute(Runnable task) {
        synchronized (lock) {
            if (isShutdown) {
                throw new IllegalStateException("ThreadPool is shutdown. No new tasks can be accepted.");
            }
            taskQueue.addLast(task);
            lock.notify();
        }
    }

    public synchronized void shutdown() {
        synchronized (lock) {
            isShutdown = true;
            for (WorkerThread thread : threads) {
                thread.interrupt();
            }
            lock.notifyAll();
        }
    }

    public boolean isShutdown() {
        return isShutdown;
    }
}
