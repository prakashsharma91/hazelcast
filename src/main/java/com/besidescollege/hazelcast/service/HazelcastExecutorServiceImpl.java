package com.besidescollege.hazelcast.service;

import com.besidescollege.hazelcast.common.EchoTask;
import com.hazelcast.core.IExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HazelcastExecutorServiceImpl implements   HazelcastExecutorService{

    @Autowired
    private DistributedService distributedService;

    @Override
    public void submitTask(String task) {
        IExecutorService executorService = distributedService.getInstance().getExecutorService( "executorService" );
        executorService.submit( new EchoTask( task) );
        executorService.submitToAllMembers( new EchoTask( task+"-all") );
    }
}
