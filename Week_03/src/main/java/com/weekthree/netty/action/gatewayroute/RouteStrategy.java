package com.weekthree.netty.action.gatewayroute;

public interface RouteStrategy {

    String getBackEndUri();

    void regesterBackendServer(String backendUri);
}
