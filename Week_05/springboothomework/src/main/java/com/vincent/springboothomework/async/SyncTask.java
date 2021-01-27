package com.vincent.springboothomework.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SyncTask extends  AbstractTask{


    @Override
    public void doTaskOne() {
        log.info("task one thread Name:{}",Thread.currentThread().getName());
        System.out.println("sync finish TaskOne");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doTaskTwo() {
        log.info("task two thread Name:{}",Thread.currentThread().getName());
        System.out.println("sync finish TaskTwo");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doTaskThree() {
        log.info("task three thread Name:{}",Thread.currentThread().getName());
        System.out.println("sync finish TaskThree");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
