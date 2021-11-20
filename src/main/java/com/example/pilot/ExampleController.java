package com.example.pilot;

import java.util.concurrent.atomic.AtomicLong;

import com.example.pilot.Greeting;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import org.eclipse.microprofile.opentracing.Traced;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Traced
public class ExampleController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    @Counted
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "WorldChange19") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping("/greet")
    @Counted
    public Greeting greet(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
    
     @RequestMapping("/")
     @Timed
    String home() {
        return "Hello World from custom source!";
    }
}
