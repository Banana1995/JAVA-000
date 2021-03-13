package com.vincent.springboothomework.waitnotify;

import lombok.SneakyThrows;

import java.util.concurrent.Semaphore;

public class Context {


    private static Semaphore A = new Semaphore(1);
    private static Semaphore B = new Semaphore(0);
    private static Semaphore C = new Semaphore(0);

    public static void main(String[] args) {
        String a = "123";
        print(a);
        System.out.println(a);
//        WaitNotifyLock lock = new WaitNotifyLock();
//        ThreadA at = new ThreadA();
//        at.setLock(lock);
//        at.start();
//        ThreadB bt = new ThreadB();
//        bt.setLock(lock);
//        bt.start();
//        ThreadA a = new ThreadA();
//        a.start();
        ThreadB b = new ThreadB();
        b.start();
        ThreadC c = new ThreadC();
        c.start();
    }

    static void  print(String a){
        System.out.println(a);
        a = "234";
        System.out.println("after is " + a);
    }

    static class ThreadA extends Thread {
        @SneakyThrows
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                A.acquire();
                System.out.println("A");
                B.release();
            }
        }
    }

    static class ThreadB extends Thread {
        @SneakyThrows
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                B.acquire();
                System.out.println("B");
                C.release();
            }
        }
    }

    static class ThreadC extends Thread {
        @SneakyThrows
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                C.acquire();
                System.out.println("C");
                A.release();
            }
        }
    }
}
