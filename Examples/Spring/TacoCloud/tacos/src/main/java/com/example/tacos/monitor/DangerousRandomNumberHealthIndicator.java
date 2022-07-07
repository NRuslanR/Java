package com.example.tacos.monitor;

import java.util.Random;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Service;

@Service
public class DangerousRandomNumberHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        
        Random random = new Random();

        int randomNumber = random.nextInt(1, 100);

        return 
            randomNumber < 50 ?
                Health
                    .outOfService()
                    .withDetail("reason", "As turned out, random number was failed !")
                    .withDetail("randomNumber", randomNumber)
                    .build()

                :

                Health
                    .up()
                    .withDetail("reason", "Yeah ! Random number was fortunated !")
                    .withDetail("randomNumber", randomNumber)
                    .build();

    }
    
}
