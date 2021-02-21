package com.vincent.springboothomework.future;

import java.util.concurrent.CompletableFuture;

public class FutureContext {


    public static void main(String[] args) {

        CompletableFuture cffromsina = CompletableFuture.supplyAsync(() -> queryCode("sina", "https://sina.com"));
        CompletableFuture cffrom163 = CompletableFuture.supplyAsync(() -> queryCode("163", "https://163.com"));
        cffromsina.thenAccept(System.out::println);
        cffrom163.thenAccept(System.out::println);

    }


    static String queryCode(String name, String url) {
        System.out.println(name);
        System.out.println(url);
        return ("600" + name);
    }
}
