package com.besidescollege.hazelcast.controller;

import com.besidescollege.hazelcast.service.HazelcastNearCacheService;
import com.besidescollege.hazelcast.service.HazelcastService;
import com.hazelcast.map.IMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HazelcastCRUDController {

    private static final Logger LOGGER = LogManager.getLogger(HazelcastCRUDController.class);

    @Autowired
    private HazelcastService hazelcastService;
    @Autowired
    private HazelcastNearCacheService hazelcastNearCacheService;

    @PostMapping(value = "/create")
    public String createData(@RequestParam String key, @RequestParam String value) {
        long startTime = System.nanoTime();
        hazelcastService.createData(key, value);
        long endTime = System.nanoTime();
        System.out.println(endTime - startTime);  //divide by 1000000 to get milliseconds.

        startTime = System.nanoTime();
        hazelcastNearCacheService.createData(key, value);
        endTime = System.nanoTime();
        System.out.println(endTime - startTime);

        return "Success";
    }

    @GetMapping(value = "/getByKey")
    public String getDataByKey(@RequestParam String key) {
        long startTime = System.nanoTime();
        hazelcastService.getDataByKey(key);
        long endTime = System.nanoTime();
        System.out.println(endTime - startTime);  //divide by 1000000 to get milliseconds.

        startTime = System.nanoTime();
        hazelcastNearCacheService.getDataByKey(key);
        endTime = System.nanoTime();
        System.out.println(endTime - startTime);

        return hazelcastService.getDataByKey(key);
    }


    @GetMapping(value = "/get")
    public IMap<String, String> getData() {
        long startTime = System.nanoTime();
        hazelcastService.getData();
        long endTime = System.nanoTime();
        System.out.println(endTime - startTime);  //divide by 1000000 to get milliseconds.

        startTime = System.nanoTime();
        hazelcastNearCacheService.getData();
        endTime = System.nanoTime();
        System.out.println(endTime - startTime);

        return hazelcastService.getData();
    }


    @PutMapping(value = "/update")
    public String updateData(@RequestParam String key, @RequestParam String value) {
        long startTime = System.nanoTime();
        hazelcastService.update(key, value);
        long endTime = System.nanoTime();
        System.out.println(endTime - startTime);  //divide by 1000000 to get milliseconds.

        startTime = System.nanoTime();
        hazelcastNearCacheService.update(key, value);
        endTime = System.nanoTime();
        System.out.println(endTime - startTime);

        return "Success";
    }


    @DeleteMapping(value = "/delete")
    public String deleteData(@RequestParam String key) {
        long startTime = System.nanoTime();
        hazelcastService.deleteData(key);
        long endTime = System.nanoTime();
        System.out.println(endTime - startTime);  //divide by 1000000 to get milliseconds.

        startTime = System.nanoTime();
        hazelcastNearCacheService.deleteData(key);
        endTime = System.nanoTime();
        System.out.println(endTime - startTime);
        return "Success";
    }
}
