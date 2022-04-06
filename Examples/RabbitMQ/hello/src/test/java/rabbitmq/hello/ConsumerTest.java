package rabbitmq.hello;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

public class ConsumerTest {
    
    
    @Test(expected = Test.None.class)
    public void shouldConsumerReceiveMessage() throws Exception
    {
        MessagingOptions options = 
            new MessagingOptions.Builder().setDeleteQueueOnClose(true).build();

        String destination = RandomStringUtils.randomAlphanumeric(20);

        try (
                Producer producer = new Producer("localhost", destination);
                Consumer consumer = new Consumer("localhost", destination, options)
            )
        {
            
            String strMsg = RandomStringUtils.randomAlphanumeric(10);
            
            producer.send(strMsg);

            Future<String> msg = consumer.receive();

            String message = msg.get(5, TimeUnit.SECONDS);

            assertEquals(strMsg, message);
        }

    }
}
