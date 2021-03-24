package com.weekthree.netty.action.gatewayroute;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RoundRobinStrategy extends AbstractStrategy {

    private AtomicInteger count = new AtomicInteger(0);

    @Override
    public String getNextUrl(List<String> backendUriList) {
        int nextIndex = count.get() % backendUriList.size();
        count.incrementAndGet();
        return backendUriList.get(nextIndex);
    }
}
