package com.vincent.springboothomework;

import com.vincent.springboothomework.async.AsyncTask;
import com.vincent.springboothomework.async.SyncTask;
import com.vincent.springboothomework.lamdaaction.LamdaAction;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    void asyncTaskExcute() {

        long start = System.currentTimeMillis();
        asyncTask.doTaskOne();
        asyncTask.doTaskTwo();
        asyncTask.doTaskThree();
        long end = System.currentTimeMillis();
        log.info("--------async task invoke over ,cost time :{}", end - start);
    }


    @Test
    void syncTaskExcute() {
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
    void lamdaActionTest(){
        lamdaAction.lamdaCheck(123);
    }

}
