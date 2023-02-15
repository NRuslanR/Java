package org.example.spring_aop.aspects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingAspect {
    
    private final Logger logger;

    public LoggingAspect()
    {
        logger = LoggerFactory.getLogger(LoggingAspect.class);
    }

    public void afterReturn(Object returnValue)
    {
        logger.info(String.format("RETURN VALUE: %s", returnValue));
    }
}
