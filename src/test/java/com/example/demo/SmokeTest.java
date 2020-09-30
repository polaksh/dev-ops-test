package com.example.demo;


import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.GreetingController;
import org.junit.Test;
import org.hamcrest.core;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmokeTest {

    @Autowired
    private GreetingController controller;

    @Test
    public void contexLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void failingTest() throws Exception {
        assertThat("bar", is("foo"));
    }
}