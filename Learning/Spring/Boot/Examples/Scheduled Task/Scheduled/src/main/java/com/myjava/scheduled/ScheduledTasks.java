/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myjava.scheduled;

import static java.lang.System.out;
import java.time.*;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 *
 * @author ruslan
 */
@EnableScheduling
public class ScheduledTasks {
    
    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime()
    {
        out.println("The time now is " + LocalDateTime.now());
    }
}
