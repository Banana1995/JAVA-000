package nettyclienttest;

import com.weekthree.netty.action.backendserver.NettyBackEndServer;
import com.weekthree.netty.action.gatewayroute.RoundRobinStrategy;
import com.weekthree.netty.action.gatewayroute.RouteStrategy;
import com.weekthree.netty.action.gatewayserver.NettyGatewayServer;
import com.weekthree.netty.action.nettyclient.NettyHttpClient;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.*;

public class ClientTest {

//    @Before
    public void startGatewayAndServer() {
//        ExecutorService service = new ThreadPoolExecutor(2, 4, 60L, TimeUnit.SECONDS,
//                new ArrayBlockingQueue<>(100), Executors.defaultThreadFactory());
//
//        RouteStrategy strategy = new RoundRobinStrategy();
//        for (int i = 8085; i <= 8088; i++) {
//            NettyBackEndServer backEndServer = new NettyBackEndServer(i);
//            String backendUri = "http:localhost:" + i;
//            strategy.regesterBackendServer(backendUri);
//            service.execute(backEndServer::run);
//        }
//
//        NettyGatewayServer gatewayServer = new NettyGatewayServer();
//        gatewayServer.setRouteStrategy(strategy);
//        service.execute(gatewayServer::run);
    }

    @lombok.SneakyThrows
    @Test
    public void clientTest() {
        NettyHttpClient nettyHttpClient = new NettyHttpClient();
//        Thread.sleep(10000);
        for (int i = 0; i < 10; i++) {
            nettyHttpClient.dohttpget();
        }
    }


}
