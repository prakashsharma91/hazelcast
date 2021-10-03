package com.besidescollege.hazelcast.subscriber;

import com.besidescollege.hazelcast.common.Constants;
import com.besidescollege.hazelcast.service.DistributedService;
import com.hazelcast.topic.ITopic;
import com.hazelcast.topic.Message;
import com.hazelcast.topic.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MyTopicSubscriber implements MessageListener<String> {

    @Autowired
    DistributedService distributedService;

    @PostConstruct
    void postConstruct(){
        ITopic<String> topic = distributedService.getTopic(Constants.MY_TOPIC);
        topic.addMessageListener(this);
    }

    @Override
    public void onMessage(Message<String> message) {
        System.out.println(message);
    }
}
