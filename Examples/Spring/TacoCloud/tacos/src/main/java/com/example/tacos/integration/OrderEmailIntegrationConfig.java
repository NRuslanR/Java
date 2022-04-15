package com.example.tacos.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.mail.dsl.Mail;

@Configuration
@EnableConfigurationProperties(EmailProperties.class)
public class OrderEmailIntegrationConfig {
    
    @Bean
    @Autowired
    public IntegrationFlow orderEmailFlow(
        EmailProperties emailProperties,
        EmailToOrderTransformer emailToOrderTransformer,
        OrderSubmitMessageHandler orderSubmitMessageHandler
    )
    {
        return 
            IntegrationFlows.from(
                Mail.imapInboundAdapter(
                    emailProperties.getImapUrl()),
                    e -> e.poller(
                            Pollers.fixedDelay(emailProperties.getPollRate())
                            .maxMessagesPerPoll(1)
                        )
            )
            .transform(emailToOrderTransformer)
            .handle(orderSubmitMessageHandler)
            .get();
    }
}
