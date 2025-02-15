package org.dacss.projectinitai.services;

import org.dacss.projectinitai.annotations.Bridge;
import org.springframework.stereotype.Service;

@Service
@Bridge("hello-world-service")
public class HelloWorldService {


    public String sayHello(String name) {
        if (name.isEmpty()) {
            // This is a backend log message
            System.out.println("Hello stranger from the hello world service backend - debug");
            // This is a frontend log message
            return "Hello stranger";
        } else {
            // This is a backend log message
            System.out.println("Hello " + name + " from the hello world service backend - debug");
            // This is a frontend log message
            return "Hello " + name;
        }
    }
}
