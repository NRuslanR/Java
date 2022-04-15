package com.example.tacos.integration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "taco.email")
public class EmailProperties {
    
    private String host;
    private String mailbox;
    private String username;
    private String password;
    private long pollRate;

    public String getImapUrl()
    {
        return String.format("imaps://%s:%s@%s/%s", username, password, host, mailbox);
    }
}
