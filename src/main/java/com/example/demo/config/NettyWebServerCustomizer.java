package com.example.demo.config;

import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

import reactor.netty.http.server.logging.AccessLog;

@Component
public class NettyWebServerCustomizer implements WebServerFactoryCustomizer<NettyReactiveWebServerFactory> {
    private static final String LOG_FORMAT = "{} {} {} {} {} {}";
    private static final String MISSING = "-";
    private static final String USER_AGENT = "User-Agent";

    @Override
    public void customize(NettyReactiveWebServerFactory factory) {
        factory.addServerCustomizers(httpServer -> 
            httpServer.accessLog(true, 
                log -> AccessLog.create(LOG_FORMAT,
                    log.protocol(),
                    log.status(),
                    log.connectionInformation().toString(),
                    log.contentLength() > -1 ? log.contentLength() : MISSING,
                    log.duration(),
                    log.requestHeader(USER_AGENT) != null ? log.requestHeader(USER_AGENT) : MISSING
                )
            )
        );
    }

}