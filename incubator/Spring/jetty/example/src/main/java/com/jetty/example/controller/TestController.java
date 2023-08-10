/*
The purpose of this example is to change Tomcat as embedded web server, which comes by default
with the spring-boot-starter-web dependency and use Jetty webserver instead. But just for
demonstration purposes only. See [1].

Resources:
[1] https://www.youtube.com/watch?v=bqGZbEQiSGI
*/

package com.jetty.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/test")
    public String testController() {
        return "This GET REST API call is working. But now using Jetty and on port 8083.";
    }

}
