package com.weekthree.netty.action.gatewayroute;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStrategy implements RouteStrategy {
    private List<String> backendUriList = new ArrayList<>();

    @Override
    public void regesterBackendServer(String backendUri) {
        backendUriList.add(backendUri);
    }

    @Override
    public String getBackEndUri() {
        return getNextUrl(backendUriList);
    }

    public abstract String getNextUrl(List<String> backendUriList);

}
