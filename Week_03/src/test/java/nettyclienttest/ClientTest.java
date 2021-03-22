package nettyclienttest;

import com.weekthree.netty.action.backendserver.NettyBackEndServer;
import com.weekthree.netty.action.gatewayserver.NettyGatewayServer;
import com.weekthree.netty.action.nettyclient.NettyHttpClient;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.*;

public class ClientTest {

    @Before
    public void startGatewayAndServer() {
        ExecutorService service = new ThreadPoolExecutor(2, 4, 60L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100), Executors.defaultThreadFactory());
//        NettyBackEndServer backEndServer = new NettyBackEndServer(8802);
//        service.execute(backEndServer::run);
        NettyGatewayServer gatewayServer = new NettyGatewayServer();
        service.execute(gatewayServer::run);
    }

    @Test
    public void clientTest() {
        NettyHttpClient nettyHttpClient = new NettyHttpClient();
        nettyHttpClient.dohttpget();
    }


}
