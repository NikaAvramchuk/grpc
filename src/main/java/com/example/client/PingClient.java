package com.example.client;

import com.example.rpc.services.PingRequest;
import com.example.rpc.services.PingResponse;
import com.example.rpc.services.PingServiceGrpc;
import com.example.server.PingServer;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.logging.Logger;

public class PingClient {
    private static final Logger LOGGER = Logger.getLogger(PingClient.class.getName());

    public static void main(String[] args) {
        LOGGER.info("Response from server: " + new PingClient().sendPing());
    }

    public String sendPing() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("127.0.0.1", 50051)
                .usePlaintext()
                .build();

        PingServiceGrpc.PingServiceBlockingStub stub = PingServiceGrpc.newBlockingStub(channel);
        PingRequest request = PingRequest.newBuilder()
                .setMessage("Ping!")
                .build();

        PingResponse response = stub.getPing(request);
        channel.shutdown();

        return response.getMessage();
    }

}
