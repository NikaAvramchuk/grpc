package com.example.service;

import com.example.rpc.services.PingRequest;
import com.example.rpc.services.PingResponse;
import com.example.rpc.services.PingServiceGrpc;
import io.grpc.stub.StreamObserver;

import java.util.logging.Logger;

public class PingServiceImpl extends PingServiceGrpc.PingServiceImplBase {
    private static final Logger LOGGER = Logger.getLogger(PingServiceImpl.class.getName());
    @Override
    public void getPing(PingRequest request, StreamObserver<PingResponse> responseObserver) {
        if("Ping!".equals(request.getMessage())) {
            LOGGER.info("Ping message received, preparing response");
            PingResponse pingResponse =
                    PingResponse.newBuilder()
                            .setMessage("Pong").build();

            responseObserver.onNext(pingResponse);
            responseObserver.onCompleted();
        }

    }
}
