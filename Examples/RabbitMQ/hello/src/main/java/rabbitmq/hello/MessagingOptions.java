package rabbitmq.hello;

public class MessagingOptions {
    
    public boolean deleteQueueOnClose;

    public static class Builder
    {
        private MessagingOptions options;

        private MessagingOptions createOrGetCurrentOptions()
        {
            if (options == null)
                options = new MessagingOptions();

            return options;
        }

        Builder setDeleteQueueOnClose(boolean value)
        {
            createOrGetCurrentOptions().deleteQueueOnClose = value;

            return this;
        }

        public MessagingOptions build()
        {
            MessagingOptions builtOptions = options;

            options = null;

            return builtOptions;
        }
    }

    public static MessagingOptions getDefault()
    {
        return new Builder().setDeleteQueueOnClose(false).build();
    }
}