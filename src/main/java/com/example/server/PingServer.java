package com.example.server;

import com.example.service.PingServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PingServer {
    private static final Logger LOGGER = Logger.getLogger(PingServer.class.getName());
    private Server server;

    public void startServer(){
        int port = 50051;
        try {
            server = ServerBuilder.forPort(port)
                    .addService(new PingServiceImpl())
                    .build()
                    .start();
            LOGGER.info("Server started on port 50051");

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                LOGGER.info("Clean server shutdown in case JVM was shutdown");
                try {
                    PingServer.this.stopServer();
                } catch (InterruptedException exception) {
                    LOGGER.log(Level.SEVERE, "Server shutdown interrupted", exception);
                }
            }));
        } catch (IOException exception) {
            LOGGER.log(Level.SEVERE, "Server did not start", exception);
        }
    }

    public void stopServer() throws InterruptedException {
        if(server!=null){
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    public void blockUntilShutdown() throws InterruptedException {
        if(server!=null){
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        PingServer userServer = new PingServer();
        userServer.startServer();
        userServer.blockUntilShutdown();
    }
}
