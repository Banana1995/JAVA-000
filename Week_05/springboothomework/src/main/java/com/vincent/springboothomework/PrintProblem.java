package com.vincent.springboothomework;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PrintProblem {

    private static class innerHolder {
        private static final PrintProblem sigleton = new PrintProblem();
    }

    public PrintProblem getInstance() {
        return innerHolder.sigleton;
    }

    private PrintProblem() {
    }

    private static ReentrantLock lock = new ReentrantLock();
    private static AtomicInteger count = new AtomicInteger();
    private static Condition eCondition;
    private static Condition fCondition;
    private static Condition gCondition;

    public static void main(String[] args) {
        eCondition = lock.newCondition();
        fCondition = lock.newCondition();
        gCondition = lock.newCondition();
        ThreadE ethread = new ThreadE();
        ethread.start();
        ThreadF fthread = new ThreadF();
        fthread.start();
        ThreadG gthread = new ThreadG();
        gthread.start();
    }

    static class ThreadE extends Thread {
        @Override
        public void run() {
            while (true) {
                lock.lock();
                int val = count.incrementAndGet();
                System.out.println("e print : " + val);
                fCondition.signal();
                try {
                    eCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class ThreadF extends Thread {
        @Override
        public void run() {
            while (true) {
                lock.lock();
                int val = count.incrementAndGet();
                System.out.println("f print : " + val);
                gCondition.signal();
                try {
                    fCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    static class ThreadG extends Thread {
        @Override
        public void run() {
            while (true) {
                lock.lock();
                int val = count.incrementAndGet();
                System.out.println("g print : " + val);
                eCondition.signal();
                try {
                    gCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
