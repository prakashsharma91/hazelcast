package com.besidescollege.hazelcast.service;

import com.besidescollege.hazelcast.common.Constants;
import com.hazelcast.topic.ITopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HazelcastTopicServiceImpl implements HazelcastTopicService {

    @Autowired
    DistributedService distributedService;

    @Override
    public void publish(String message) {
        ITopic<String> topic = distributedService.getTopic(Constants.MY_TOPIC);
        topic.publish("message");
    }
}
