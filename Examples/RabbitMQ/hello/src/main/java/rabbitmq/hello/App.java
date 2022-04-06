package rabbitmq.hello;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        System.out.println("Hello World!");

        try(
            Producer producer = new Producer("localhost", "helloworld");
            Consumer consumer = new Consumer("localhost", "helloworld")
        )
        {
            producer.send("Test Message");

            Future<String> futureMessage = consumer.receive();

            String message = futureMessage.get();

            System.out.println(message);
        }
    }
}
