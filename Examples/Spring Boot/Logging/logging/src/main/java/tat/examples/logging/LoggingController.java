package tat.examples.logging;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

@RestController
@Log4j2
public class LoggingController {
    
    @GetMapping("/logging")
    public void checkLogging()
    {
        final var msg = "TEST LOGGING";

        log.info(msg);
        log.error(msg);
        log.warn(msg);
        log.debug(msg);
        log.trace(msg);

    }
}
