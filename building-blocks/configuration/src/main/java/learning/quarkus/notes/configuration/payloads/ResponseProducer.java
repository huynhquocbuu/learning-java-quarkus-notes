package learning.quarkus.notes.configuration.payloads;

import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestResponse;

public class ResponseProducer {
    public static <T> RestResponse<ResponseWrapper<T>> ok(T data){
        return RestResponse.ResponseBuilder
                .create(
                        RestResponse.Status.OK,
                        new ResponseWrapper<T>("success", "", "", data)
                )
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    public static <T> RestResponse<ResponseWrapper<T>> failAuth(){
        return RestResponse.ResponseBuilder
                .create(
                        RestResponse.Status.OK,
                        new ResponseWrapper<T>(
                                "fail",
                                "E-00",
                                "wrong username or password", null)
                )
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
