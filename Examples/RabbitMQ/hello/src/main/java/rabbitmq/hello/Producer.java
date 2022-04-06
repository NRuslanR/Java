package rabbitmq.hello;

import java.io.Closeable;
import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;

public class Producer implements Closeable {

    private final String destination;
    private Channel channel;
    private MessagingOptions messagingOptions;

    public Producer(String host, String destination) throws Exception
    {
        this(host, destination, MessagingOptions.getDefault());
    }

    public Producer(String host, String destination, MessagingOptions messagingOptions) throws Exception
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

    public void send(String message) throws Exception
    {
        channel.basicPublish("", destination, null, message.getBytes());
    }

    @Override
    public void close() throws IOException {
        
        if (messagingOptions.deleteQueueOnClose)
            channel.queueDelete(destination);

        channel.getConnection().close();
        
    }
}
