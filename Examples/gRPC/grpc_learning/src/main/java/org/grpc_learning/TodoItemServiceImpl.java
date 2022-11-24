package org.grpc_learning;

import java.time.Instant;
import java.util.logging.Logger;
import java.util.random.RandomGenerator;

import org.grpc_learning.TodoItemServiceGrpc.TodoItemServiceImplBase;

import com.google.protobuf.Timestamp;

import io.grpc.stub.StreamObserver;

public class TodoItemServiceImpl extends TodoItemServiceImplBase {

    private static final Logger logger = Logger.getLogger("GrpcExample");

    @Override
    public void createTodoItem(
        CreateTodoItemRequest request,
        StreamObserver<CreateTodoItemResponse> responseObserver
    ) 
    {
        logger.info("Incoming create todo item request arrived");

        CreateTodoItemResponse response =
                createTodoItemResponse(request);

        responseObserver.onNext(response);
        responseObserver.onCompleted();

        logger.info("todo item was successfully created and returned");
    }
    
    private CreateTodoItemResponse createTodoItemResponse(CreateTodoItemRequest request) 
    {
        return 
            CreateTodoItemResponse
                .newBuilder()
                .setCreatedAt(
                    Timestamp
                        .newBuilder()
                        .setSeconds(Instant.now().getEpochSecond())
                        .setNanos(Instant.now().getNano())
                        .build()
                    )
                .setCreatedTodoItem(createTodoItemDto(request))
                .build();
    }

    private TodoItemDto createTodoItemDto(CreateTodoItemRequest request)
    {
        return 
            TodoItemDto
                .newBuilder()
                .setId(RandomGenerator.getDefault().nextLong(1, 1000))
                .setName(request.getName())
                .setDescription(request.getDescription())
                .setStatus(TodoItemStatus.Performing)
                .build();
    }
}
