package org.grpc_learning;

import java.util.logging.Logger;

import org.grpc_learning.TodoItemServiceGrpc.TodoItemServiceBlockingStub;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;

public class GrpcExample 
{
    private static final Logger logger = Logger.getLogger("GrpcExample");

    public static void main( String[] args ) throws Exception
    {
        String grpcMode = args.length >= 2 ? args[0].toLowerCase() : "server";
        int port = Integer.parseInt(args[1]);

        if (grpcMode == "server")
            runAsServer(port);

        else if (grpcMode == "client")
            runAsClient(port);          
    }

    private static void runAsClient(int port) {

        ManagedChannel channel = 
            ManagedChannelBuilder
                .forAddress("localhost", port)
                .usePlaintext()
                .build();
                
        TodoItemServiceBlockingStub serviceClient = 
                TodoItemServiceGrpc.newBlockingStub(channel);
            
        CreateTodoItemResponse response =
            serviceClient.createTodoItem(
                CreateTodoItemRequest
                    .newBuilder()
                    .setName("Test To-Do Item")
                    .setDescription("Description").build()
                );

        TodoItemDto createdTodoItem = response.getCreatedTodoItem();
            
        logger.info(
            String.format(
                "New To-Do item created: [%s, %s, %s, %s, %s]", 
                createdTodoItem.getId(),
                createdTodoItem.getName(),
                createdTodoItem.getDescription(),
                response.getCreatedAt().toString(),
                createdTodoItem.getStatus().toString()
            )
        );

    }

    private static void runAsServer(int port) throws Exception {

        Server server =
            ServerBuilder
                .forPort(8089)
                .addService(new TodoItemServiceImpl())
                .build();
                
        server.start();

        logger.info("GRPC Server started for incoming requests");

        server.awaitTermination();

    }
}
