package com.besidescollege.hazelcast.service;

import com.besidescollege.hazelcast.config.HazelcastConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.hazelcast.topic.ITopic;
import org.springframework.stereotype.Component;

@Component("distributedService")
public class DistributedService {

    private HazelcastInstance instance;

    private DistributedService(){
        instance = HazelcastConfig.getInstance();
    }

    public IMap<String, String> getMap(String s) {
        return instance.getMap(s);
    }

    public HazelcastInstance getInstance(){
        return instance;
    }

    public ITopic<String> getTopic(String topic) {
        return instance.getTopic(topic);
    }
}
