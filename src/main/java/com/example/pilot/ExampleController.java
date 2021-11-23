package com.example.pilot;

import java.util.concurrent.atomic.AtomicLong;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
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
    @Timed(name = "getMPMetricsInfoTimed", description = "Metrics to monitor the times spent in getMPMetricsInfo method", unit = MetricUnits.SECONDS, absolute = true)
    @Counted(description = "counter of the getMPMetricsInfo method", absolute = true)
    public Greeting greet(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
    
     @RequestMapping("/")
     @Timed
    String home() {
        return "Hello World from custom source!";
    }



}
