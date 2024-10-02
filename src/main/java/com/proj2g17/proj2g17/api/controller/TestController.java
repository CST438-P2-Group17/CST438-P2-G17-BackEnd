package com.proj2g17.proj2g17.api.controller;

import com.proj2g17.proj2g17.api.model.Test;
import com.proj2g17.proj2g17.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class TestController {

    private TestService testService;

    @Autowired
    public TestController(TestService testService){
        this.testService=testService;
    }

    @GetMapping("/test")
    public List<Test> getAllTests(){

            Optional<List<Test>> tests = testService.getTests();
            if (tests.isPresent()) {
                return tests.get();
            } else {
                throw new RuntimeException("No tests found");
            }

    }
}
