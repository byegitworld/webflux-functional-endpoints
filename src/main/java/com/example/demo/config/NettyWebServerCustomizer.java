package com.example.demo.config;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

import brave.internal.Nullable;
import reactor.netty.http.server.logging.AccessLog;

@Component
public class NettyWebServerCustomizer implements WebServerFactoryCustomizer<NettyReactiveWebServerFactory> {
    private static final String LOG_FORMAT = "{} {} {} {} {} {} {} {} {} {} {} {} {}";
    private static final String MISSING = "-";
    private static final String REFERER = "Referer";
    private static final String USER_AGENT = "User-Agent";

    @Override
    public void customize(NettyReactiveWebServerFactory factory) {
        factory.addServerCustomizers(httpServer -> 
            httpServer.accessLog(true, 
                x -> AccessLog.create(LOG_FORMAT,
                    applyAddress(x.connectionInformation().remoteAddress()),    
                    x.connectionInformation().connectionRemoteAddress().toString(),
                    x.user(),
                    x.accessDateTime(),
                    x.method(),
                    x.uri(),
                    x.protocol(),
                    x.status(),
                    x.contentLength() > -1 ? x.contentLength() : MISSING,
                    x.duration(),
                    x.requestHeader(REFERER) != null ? x.requestHeader(REFERER) : MISSING,
                    x.requestHeader(USER_AGENT) != null ? x.requestHeader(USER_AGENT) : MISSING,
                    x.connectionInformation().toString()
                )
            )
        );
    }

    private static String applyAddress(@Nullable SocketAddress socketAddress) {
		return socketAddress instanceof InetSocketAddress ? ((InetSocketAddress) socketAddress).getHostString() : MISSING;
	}

}