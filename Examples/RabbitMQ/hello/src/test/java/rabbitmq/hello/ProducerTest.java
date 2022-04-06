package rabbitmq.hello;

import org.junit.Test;

public class ProducerTest {

    @Test(expected = Test.None.class)
    public void shouldProducerSendMessage() throws Exception
    {
        MessagingOptions options =
            new MessagingOptions.Builder().setDeleteQueueOnClose(true).build();

        try(Producer producer = new Producer("localhost", "helloworld", options))
        {
            producer.send("msg");
        }
    }
    
}
