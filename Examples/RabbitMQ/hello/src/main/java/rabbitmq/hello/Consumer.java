package rabbitmq.hello;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class Consumer implements Closeable {

    private final String destination;
    private Channel channel;
    private MessagingOptions messagingOptions;

    public Consumer(
            String host,
            String destination
    ) throws Exception
    { 
        this(host, destination, MessagingOptions.getDefault());
    }

    public Consumer(
            String host,
            String destination,
            MessagingOptions messagingOptions
    ) throws Exception
    {

        CreateChannel(host, destination, messagingOptions);

        this.destination = destination;
        this.messagingOptions = messagingOptions;
    }
    
    private void CreateChannel(String host, String destination, MessagingOptions options) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost(host);

        channel = factory.newConnection().createChannel();

        channel.queueDeclare(destination, false, false, false, null);
    }

    public Future<String> receive() throws Exception {
        
        CompletableFuture<String> futureMessage = new CompletableFuture<String>();

        DeliverCallback deliverCallback = (consumerTag, message) -> {

            String msg = new String(message.getBody(), "UTF-8");

            futureMessage.complete(msg);

        };
        
        channel.basicConsume(destination, true, deliverCallback, consumerTag -> {
        });

        return futureMessage;
    }

    @Override
    public void close() throws IOException {
       
        if (messagingOptions.deleteQueueOnClose)
            channel.queueDelete(destination);

        channel.getConnection().close();
        
    }

}
