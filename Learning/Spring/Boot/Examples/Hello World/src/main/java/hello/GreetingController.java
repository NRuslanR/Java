/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import java.util.concurrent.atomic.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ruslan
 */
@RestController
public class GreetingController {
    
    private AtomicLong idGenerator;
    private String greetingTemplate;
    
    public GreetingController()
    {
        idGenerator = new AtomicLong(0);
        greetingTemplate = "Hello, %s !";
    }
    
    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public Greeting greeting(
            @RequestParam(
                    value = "name", required = false, defaultValue = "World"
            ) String name
    )    
    {
        return 
                new Greeting(
                        idGenerator.incrementAndGet(), 
                        String.format(greetingTemplate, name)
                );
    }
}
