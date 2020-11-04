package com.weekthree.netty.action.nettyclient;

import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class NettyOkHttpClient {


    public FullHttpResponse doHttpReq() {
        FullHttpResponse fullHttpResponse = null;
        okhttp3.OkHttpClient client = new okhttp3.OkHttpClient();
        try {
            Request request = new Request.Builder().url("http://localhost:8801").build();
            Response response = client.newCall(request).execute();
            byte[] body = response.body().bytes();
            fullHttpResponse = new DefaultFullHttpResponse(HTTP_1_1, OK,Unpooled.wrappedBuffer(body));
            fullHttpResponse.content();
            fullHttpResponse.headers().set("Content-Type", "application/json");
            fullHttpResponse.headers().setInt("Content-Length", Integer.parseInt(response.headers().get("Content-Length")));

        } catch (Exception    e) {
            e.printStackTrace();
        } finally {
            client.clone();
        }
        return fullHttpResponse;
    }
}
