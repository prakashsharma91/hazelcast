package com.besidescollege.hazelcast.service;


import com.besidescollege.hazelcast.common.Constants;
import com.hazelcast.map.IMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HazelcastServiceImpl implements  HazelcastService{

    @Autowired
    private DistributedService distributedService;

    @Override
    public String createData(String key, String value) {
        IMap<String, String> map = distributedService.getMap(Constants.distributedMap);
        map.put(key, value);
        return "Data is stored.";
    }

    @Override
    public String getDataByKey(String key) {
        IMap<String, String> map = distributedService.getMap(Constants.distributedMap);
        return map.get(key);
    }

    @Override
    public IMap<String, String> getData() {
        return distributedService.getMap(Constants.distributedMap);
    }

    @Override
    public String update(String key, String value) {
        IMap<String, String> map = distributedService.getMap(Constants.distributedMap);
        map.set(key, value);
        return "Data is stored.";
    }

    @Override
    public String deleteData(String key) {
        IMap<String, String> map = distributedService.getMap(Constants.distributedMap);
        return map.remove(key);
    }
}
