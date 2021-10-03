package com.besidescollege.hazelcast.controller;

import com.besidescollege.hazelcast.service.HazelcastTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HazelcastTopicController {

    @Autowired
    HazelcastTopicService hazelcastTopicService;

    @PostMapping(value = "/publish")
    public String createData(@RequestParam String task) {
        hazelcastTopicService.publish(task);
        return "Success";
    }
}
