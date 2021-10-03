package com.besidescollege.hazelcast.service;

import com.hazelcast.map.IMap;

public interface HazelcastService {

    public String createData(String key, String value);

    public String getDataByKey(String key);

    public IMap<String, String> getData();

    public String update(String key, String value);

    public String deleteData(String key);
}
