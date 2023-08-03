/*
This example is based on [1].

Resources:
[1] https://www.youtube.com/watch?v=aS9SQITRocc
*/

package com.example.demo;

import java.util.Arrays;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ApplicationContext apc = SpringApplication.run(DemoApplication.class, args);
		Arrays.stream(apc.getBeanDefinitionNames()).forEach(System.out::println);
	}

}
