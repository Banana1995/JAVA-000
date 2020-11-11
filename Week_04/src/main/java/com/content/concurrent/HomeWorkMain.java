package com.content.concurrent;

import java.util.concurrent.*;

public class HomeWorkMain {
    public static void main(String[] args) {


    }

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
    }

    private void asynmethod2() {
        long start = System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        System.out.println("方法2：使用线程池执行futuretask，使用future或futuretask获取值");
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
        int result = 0; //这是得到的返回值
        int taskresult = 0; //这是得到的返回值
        try {
            result = (Integer) callFuture.get();
            taskresult = (Integer) task.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        // 确保  拿到result 并输出
        System.out.println("future异步计算结果为：" + result);
        System.out.println("futuretask异步计算结果为：" + taskresult);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
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

    private void asynmethod4() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        System.out.println("方法4：用CompetableFuture获取值");

        int result = CompletableFuture.supplyAsync(HomeWorkMain::sum).get(); //这是得到的返回值
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2)
            return 1;
        return fibo(a - 1) + fibo(a - 2);
    }
}
