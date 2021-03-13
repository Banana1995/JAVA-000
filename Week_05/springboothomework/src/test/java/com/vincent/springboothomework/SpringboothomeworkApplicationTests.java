package com.vincent.springboothomework;

import com.vincent.springboothomework.async.AbstractTask;
import com.vincent.springboothomework.async.AsyncTask;
import com.vincent.springboothomework.async.SyncTask;
import com.vincent.springboothomework.lamdaaction.LamdaAction;
import com.vincent.springboothomework.model.Frank;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.*;

@SpringBootTest
@Slf4j
class SpringboothomeworkApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private SyncTask syncTask;
    @Autowired
    private AsyncTask asyncTask;

    @Test
    void executorPoolTest() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<String> res = executor.submit(() -> {
            System.out.println("fhh33");
            return "ssss2232";
        });
        String s = res.get();
        System.out.println(s);
    }

    @Test
    void asyncTaskExcute() {
        AbstractTask.stringThreadLocal.set("hello async");
        long start = System.currentTimeMillis();
        asyncTask.doTaskOne();
        asyncTask.doTaskTwo();
        asyncTask.doTaskThree();
        long end = System.currentTimeMillis();
        log.info("--------async task invoke over ,cost time :{}", end - start);
    }


    @Test
    void syncTaskExcute() {
        AbstractTask.stringThreadLocal.set("hello sync");
        long start = System.currentTimeMillis();
        syncTask.doTaskOne();
        syncTask.doTaskTwo();
        syncTask.doTaskThree();
        long end = System.currentTimeMillis();
        log.info("--------sync task excute over ,cost time :{}", end - start);
    }

    @Autowired
    private LamdaAction lamdaAction;

    @Test
    void lamdaActionTest() {
        lamdaAction.lamdaCheck(123);
    }

    @Test
    void handleTest() {
        int res = finallyTest();
        System.out.println(res);
    }

    int finallyTest() {
        try {
            Thread.sleep(123);
            return 1;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            touchFrank();
            System.out.println("happen null");
        }
        return 3;
    }
    private Frank frank;

    void touchFrank() {
        frank.getAge();
    }

}
