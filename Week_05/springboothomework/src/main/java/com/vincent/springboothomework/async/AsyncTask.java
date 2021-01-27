package com.vincent.springboothomework.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@Async
public class AsyncTask extends AbstractTask{

    @Override
    public void doTaskOne() {

        log.info("task one thread Name:{}",Thread.currentThread().getName());
        System.out.println("async finish TaskOne");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doTaskTwo() {

        log.info("task two thread Name:{}",Thread.currentThread().getName());
        System.out.println("async finish Tasktwo");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doTaskThree() {

        log.info("task three thread Name:{}",Thread.currentThread().getName());
        System.out.println("async finish TaskThree");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
