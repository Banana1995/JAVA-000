package com.vincent.springboothomework.waitnotify;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadB extends Thread {
    private ReentrantLock lock;

    public ReentrantLock getLock() {
        return lock;
    }

    public void setLock(ReentrantLock lock) {
        this.lock = lock;
    }


    @Override
    public void run() {
        while (true) {
            lock.lock();
            Condition condition = lock.newCondition();
//            condition.await();
//            condition.signal();
            System.out.println("thread b");
            lock.unlock();
//            lock.notify();
//            try {
//                lock.wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
}
