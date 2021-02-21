package com.vincent.springboothomework.waitnotify;

public class ThreadA extends Thread {
    private WaitNotifyLock lock;

    public WaitNotifyLock getLock() {
        return lock;
    }

    public void setLock(WaitNotifyLock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (lock) {
                System.out.println("thread a");

                lock.notify();
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
