package com.example.demo.error;

import java.util.Map;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes{
    
    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        Map<String, Object> map = super.getErrorAttributes(request, options);
        Throwable throwable = getError(request);
        if (throwable instanceof ServerException) {
            ServerException e = ServerException.class.cast(throwable);
            map.put("status", e.getError().getStatus().value());
            map.put("error", e.getError().getStatus().getReasonPhrase());
            map.put("code", e.getError().getCode());
            map.put("message", e.getError().getMessage());
        }
        return map;
    }

}
