package org.grpc_learning;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: TodoService.proto")
public final class TodoItemServiceGrpc {

  private TodoItemServiceGrpc() {}

  public static final String SERVICE_NAME = "org.grpc_learning.TodoItemService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.grpc_learning.CreateTodoItemRequest,
      org.grpc_learning.CreateTodoItemResponse> getCreateTodoItemMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "createTodoItem",
      requestType = org.grpc_learning.CreateTodoItemRequest.class,
      responseType = org.grpc_learning.CreateTodoItemResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.grpc_learning.CreateTodoItemRequest,
      org.grpc_learning.CreateTodoItemResponse> getCreateTodoItemMethod() {
    io.grpc.MethodDescriptor<org.grpc_learning.CreateTodoItemRequest, org.grpc_learning.CreateTodoItemResponse> getCreateTodoItemMethod;
    if ((getCreateTodoItemMethod = TodoItemServiceGrpc.getCreateTodoItemMethod) == null) {
      synchronized (TodoItemServiceGrpc.class) {
        if ((getCreateTodoItemMethod = TodoItemServiceGrpc.getCreateTodoItemMethod) == null) {
          TodoItemServiceGrpc.getCreateTodoItemMethod = getCreateTodoItemMethod = 
              io.grpc.MethodDescriptor.<org.grpc_learning.CreateTodoItemRequest, org.grpc_learning.CreateTodoItemResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "org.grpc_learning.TodoItemService", "createTodoItem"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.grpc_learning.CreateTodoItemRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.grpc_learning.CreateTodoItemResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new TodoItemServiceMethodDescriptorSupplier("createTodoItem"))
                  .build();
          }
        }
     }
     return getCreateTodoItemMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TodoItemServiceStub newStub(io.grpc.Channel channel) {
    return new TodoItemServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TodoItemServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new TodoItemServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static TodoItemServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new TodoItemServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class TodoItemServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void createTodoItem(org.grpc_learning.CreateTodoItemRequest request,
        io.grpc.stub.StreamObserver<org.grpc_learning.CreateTodoItemResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateTodoItemMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateTodoItemMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                org.grpc_learning.CreateTodoItemRequest,
                org.grpc_learning.CreateTodoItemResponse>(
                  this, METHODID_CREATE_TODO_ITEM)))
          .build();
    }
  }

  /**
   */
  public static final class TodoItemServiceStub extends io.grpc.stub.AbstractStub<TodoItemServiceStub> {
    private TodoItemServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TodoItemServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TodoItemServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TodoItemServiceStub(channel, callOptions);
    }

    /**
     */
    public void createTodoItem(org.grpc_learning.CreateTodoItemRequest request,
        io.grpc.stub.StreamObserver<org.grpc_learning.CreateTodoItemResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateTodoItemMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class TodoItemServiceBlockingStub extends io.grpc.stub.AbstractStub<TodoItemServiceBlockingStub> {
    private TodoItemServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TodoItemServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TodoItemServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TodoItemServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public org.grpc_learning.CreateTodoItemResponse createTodoItem(org.grpc_learning.CreateTodoItemRequest request) {
      return blockingUnaryCall(
          getChannel(), getCreateTodoItemMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class TodoItemServiceFutureStub extends io.grpc.stub.AbstractStub<TodoItemServiceFutureStub> {
    private TodoItemServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TodoItemServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TodoItemServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TodoItemServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.grpc_learning.CreateTodoItemResponse> createTodoItem(
        org.grpc_learning.CreateTodoItemRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateTodoItemMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_TODO_ITEM = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final TodoItemServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(TodoItemServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CREATE_TODO_ITEM:
          serviceImpl.createTodoItem((org.grpc_learning.CreateTodoItemRequest) request,
              (io.grpc.stub.StreamObserver<org.grpc_learning.CreateTodoItemResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class TodoItemServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    TodoItemServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.grpc_learning.TodoService.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("TodoItemService");
    }
  }

  private static final class TodoItemServiceFileDescriptorSupplier
      extends TodoItemServiceBaseDescriptorSupplier {
    TodoItemServiceFileDescriptorSupplier() {}
  }

  private static final class TodoItemServiceMethodDescriptorSupplier
      extends TodoItemServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    TodoItemServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (TodoItemServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new TodoItemServiceFileDescriptorSupplier())
              .addMethod(getCreateTodoItemMethod())
              .build();
        }
      }
    }
    return result;
  }
}
