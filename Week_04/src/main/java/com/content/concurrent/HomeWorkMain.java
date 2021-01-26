package com.content.concurrent;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class HomeWorkMain {
//    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        HomeWorkMain homeWorkMain = new HomeWorkMain();
//        homeWorkMain.asynmethod1();
//        homeWorkMain.asynmethod2();
//        homeWorkMain.asynmethod3();
//        homeWorkMain.asynmethod4();
//    }

    private void asynmethod1() {
        long start = System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        System.out.println("方法1：使用线程池执行callable，用future获取值");
        // 异步执行 下面方法
        Callable callable = new Callable() {
            @Override
            public Object call() throws Exception {
                return sum();
            }
        };

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future callFuture = executorService.submit(callable);
        int result = 0; //这是得到的返回值
        try {
            result = (Integer) callFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
        executorService.shutdown();
    }

    private void asynmethod2() {
        long start = System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        System.out.println("方法2：使用线程池执行futuretask，使用futuretask获取值");
        // 异步执行 下面方法
        Callable callable = new Callable() {
            @Override
            public Object call() throws Exception {
                return sum();
            }
        };
        FutureTask task = new FutureTask(callable);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future callFuture = executorService.submit(task);
        int taskresult = 0; //这是得到的返回值
        try {
            taskresult = (Integer) task.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        // 确保  拿到result 并输出
        System.out.println("futuretask异步计算结果为：" + taskresult);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
        executorService.shutdown();
    }

    private void asynmethod3() {
        long start = System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        System.out.println("方法3：用thread执行Future task，通过future task获取值");
        // 异步执行 下面方法
        Callable callable = new Callable() {
            @Override
            public Object call() throws Exception {
                return sum();
            }
        };
        FutureTask task = new FutureTask(callable);
        Thread thread = new Thread(task);
        thread.start();
        int result = 0; //这是得到的返回值
        try {
            result = (Integer) task.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }

//    private void asynmethod4() throws ExecutionException, InterruptedException {
//        long start = System.currentTimeMillis();
//        // 在这里创建一个线程或线程池，
//        System.out.println("方法4：用CompetableFuture获取值");
//
//        int result = CompletableFuture.supplyAsync(HomeWorkMain::sum).get(); //这是得到的返回值
//        // 确保  拿到result 并输出
//        System.out.println("异步计算结果为：" + result);
//        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
//    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2)
            return 1;
        return fibo(a - 1) + fibo(a - 2);
    }

    public static void main(String[] args) {
        HomeWorkMain homeWorkMain = new HomeWorkMain();
        homeWorkMain.tre();
    }

    private void tre() {
        Lock lock = new ReentrantLock();
        Object aa = new Object();
        Object oo = new Object();
        MyThread thread1 = new MyThread("thread1 -- ");
        thread1.setOo(oo, aa,lock);
        thread1.start();
        try {
            lock.lock();
            while (true) {
                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        synchronized (aa) {
            for (int i = 0; i < 100; i++) {
                if (i == 20) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + "aa -- " + i);
            }
            synchronized (oo) {
                for (int i = 0; i < 30; i++) {
                    System.out.println(Thread.currentThread().getName() + "oo -- " + i);
                }
            }
        }

    }

    static class MyThread extends Thread {
        private String name;
        private Object oo;
        private Object aa;
        private Lock lock;

        public void setOo(Object oo, Object aa, Lock lock) {
            this.oo = oo;
            this.aa = aa;
            this.lock = lock;
        }

        public MyThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {

            try {
                lock.lock();
                System.out.println("my thread get lock");
            } finally {
                lock.unlock();
            }
            synchronized (oo) {
                for (int i = 0; i < 100; i++) {
                    System.out.println(name + i);
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (aa) {
                    for (int i = 0; i < 100; i++) {
                        System.out.println(name + "aa: " + i);
                    }
                }
            }

        }
    }
}
