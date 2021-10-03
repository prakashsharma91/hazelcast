package com.besidescollege.hazelcast.controller;

import com.besidescollege.hazelcast.service.HazelcastExecutorService;
import com.besidescollege.hazelcast.service.HazelcastNearCacheService;
import com.besidescollege.hazelcast.service.HazelcastService;
import com.hazelcast.map.IMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HazelcastExecutorController {

    private static final Logger LOGGER = LogManager.getLogger(HazelcastExecutorController.class);

    @Autowired
    private HazelcastExecutorService hazelcastExecutorService;

    @PostMapping(value = "/task")
    public String createData(@RequestParam String task) {
        hazelcastExecutorService.submitTask(task);

        return "Success";
    }
}
