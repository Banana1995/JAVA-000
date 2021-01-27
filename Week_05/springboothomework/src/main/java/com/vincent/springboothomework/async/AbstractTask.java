package com.vincent.springboothomework.async;

public abstract class AbstractTask {
    public static ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();

    public abstract void doTaskOne();

    public abstract void doTaskTwo();

    public abstract void doTaskThree();

}
