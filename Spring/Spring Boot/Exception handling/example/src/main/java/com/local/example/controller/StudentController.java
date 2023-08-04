/*
This example is based on [1].

Resources:
[1] https://www.youtube.com/watch?v=PzK4ZXa2Tbc
*/
package com.local.example.controller;

import java.util.List;

import com.local.example.exception.ApiRequestException;
import com.local.example.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

    @GetMapping("/list")
    public String getStudent() {
        //throw new IllegalStateException("Students list couldn't be retrieved.");
        return "We made it to the students list.";
    }

    @GetMapping("/info")
    public List<Student> getInfo() {
        //throw new IllegalStateException("Information about students is restricted to school personnel.");
        //We could handle the commented exception, but it could be a problem since it is built-in,
        //which could cause a lot of trouble, so the purpose of this example is to handle customized one.
        throw new ApiRequestException("Information about the students is restricted");
    }
}
