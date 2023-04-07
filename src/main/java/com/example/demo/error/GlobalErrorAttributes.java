// package com.example.demo.error;

// import java.util.Date;
// import java.util.LinkedHashMap;
// import java.util.Map;

// import org.springframework.boot.web.error.ErrorAttributeOptions;
// import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
// import org.springframework.boot.web.reactive.error.ErrorAttributes;
// import org.springframework.stereotype.Component;
// import org.springframework.web.reactive.function.server.ServerRequest;
// import org.springframework.web.server.ServerWebExchange;

// import lombok.extern.slf4j.Slf4j;

// @Slf4j
// @Component
// // public class GlobalErrorAttributes implements ErrorAttributes {
// public class GlobalErrorAttributes extends DefaultErrorAttributes {
    
//     private static final String ERROR_INTERNAL_ATTRIBUTE = GlobalErrorAttributes.class.getName() + ".ERROR";

//     @Override
// 	public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
//         Map<String, Object> errorAttributes = new LinkedHashMap<>();
//         errorAttributes.put("timestamp", new Date());
//         errorAttributes.put("path", request.path());
//         errorAttributes.put("requestId", request.exchange().getRequest().getId());
//         Throwable error = getError(request);
//         if (error instanceof ServerException) {
//             ServerException e = ServerException.class.cast(error);
//             errorAttributes.put("status", e.getError().getStatus().value());
//             errorAttributes.put("error", e.getError().getStatus().getReasonPhrase());
//             errorAttributes.put("code", e.getError().getCode());
//             errorAttributes.put("message", e.getError().getMessage());
//         } else {
//             Error defaultError = Error.INTERNAL_SERVER_ERROR;
//             errorAttributes.put("status", defaultError.getStatus().value());
//             errorAttributes.put("error", defaultError.getStatus().getReasonPhrase());
//             errorAttributes.put("code", defaultError.getCode());
//             errorAttributes.put("message", defaultError.getMessage());
//         }
//         log.error(errorAttributes.toString());
//         return errorAttributes;
//     }

//     // @Override
//     // public Throwable getError(ServerRequest request) {
// 	// 	return (Throwable) request.attribute(ERROR_INTERNAL_ATTRIBUTE).orElseThrow(() -> new ServerException(Error.INTERNAL_SERVER_ERROR));
//     // }

//     // @Override
//     // public void storeErrorInformation(Throwable error, ServerWebExchange exchange) {
//     //     exchange.getAttributes().putIfAbsent(ERROR_INTERNAL_ATTRIBUTE, error);
//     // }
   

// }
