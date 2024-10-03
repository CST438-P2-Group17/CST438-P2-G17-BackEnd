package com.proj2g17.proj2g17.service;

import com.proj2g17.proj2g17.api.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class TestService {

    private List<Test> testList;

    public TestService() {
        testList = new ArrayList<>();

        Test test1= new Test(1, "Test 1");
        Test test2= new Test(2, "Test 2");
        Test test3= new Test(3, "Test 3");

        testList.addAll(Arrays.asList(test1,test2,test3));
    }

    public Optional<List<Test>> getTests() {
        Optional optional = Optional.empty();
        if(!testList.isEmpty()){
            optional=Optional.of(testList);
            return optional;
        }
        return optional;
    }
}
