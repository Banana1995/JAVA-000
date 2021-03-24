package com.weekthree.netty.action.gatewayserver;

import com.weekthree.netty.action.filter.HeadNameFilter;
import com.weekthree.netty.action.gatewayroute.RouteStrategy;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.util.ReferenceCountUtil;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * 网关的handler 在此类中处理过滤、路由等操作
 */
public class GatewayHandler extends ChannelInboundHandlerAdapter {
    private static Logger logger = LoggerFactory.getLogger(GatewayHandler.class);

    private HeadNameFilter headNameFilter;

    private RouteStrategy routeStrategy;

    public void setRouteStrategy(RouteStrategy routeStrategy) {
        this.routeStrategy = routeStrategy;
    }

    public void setHeadNameFilter(HeadNameFilter headNameFilter) {
        this.headNameFilter = headNameFilter;
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        logger.info("channelRead流量接口请求开始");
        try {
            FullHttpRequest fullRequest = (FullHttpRequest) msg;
            headNameFilter.filter(fullRequest, ctx);
            logger.info("request name is " + fullRequest.headers().get("name"));
            //收到请求后，网关转发请求到后端服务
            FullHttpResponse response = doHttpReq(fullRequest);
            ctx.write(response);
//            NettyHttpClient nettyHttpClient = new NettyHttpClient();
//            nettyHttpClient.dohttpget(ctx);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ctx.flush();
            ctx.close();
            ReferenceCountUtil.release(msg);
        }
        logger.info("channelRead流量接口请求结束");
    }

    /**
     * 向后请求backend server
     * 此处实现路由策略，过滤策略等操作
     *
     * @param fullRequest
     * @return
     */
    public FullHttpResponse doHttpReq(FullHttpRequest fullRequest) {
        FullHttpResponse fullHttpResponse = null;
        okhttp3.OkHttpClient client = new okhttp3.OkHttpClient();
        try {
            String originUri = fullRequest.uri();
            //路由策略,将原始的uri路由到backend的uri上去
            String backendUri = routeStrategy.getBackEndUri();
            Request request = new Request.Builder().url(backendUri).build();
            Response response = client.newCall(request).execute();
            byte[] body = response.body().bytes();
            fullHttpResponse = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(body));
            fullHttpResponse.content();
            fullHttpResponse.headers().set("Content-Type", "application/json");
            fullHttpResponse.headers().setInt("Content-Length", Integer.parseInt(response.headers().get("Content-Length")));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.clone();
        }
        return fullHttpResponse;
    }
}
