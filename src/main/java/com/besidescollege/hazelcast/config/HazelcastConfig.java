package com.besidescollege.hazelcast.config;

import com.besidescollege.hazelcast.common.Constants;
import com.hazelcast.config.*;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;


public class HazelcastConfig {

    private static HazelcastInstance instance;


    public static Config getConfig() {
        Config config = new Config();
        config.setInstanceName("hazelcast-instance");

        config.getNetworkConfig().getRestApiConfig().setEnabled(true).enableAllGroups();
        
        setDiscoveryMethod(config);

        setMapConfig(config);

        return config;
    }

    private static void setMapConfig(Config config) {
        EvictionConfig evictionConfig = new EvictionConfig();
        evictionConfig.setEvictionPolicy(EvictionPolicy.LRU);
        evictionConfig.setSize(200);

        MapConfig mapConfig = new MapConfig();
        mapConfig.setName("configuration");
        mapConfig.setEvictionConfig(evictionConfig);
        mapConfig.setTimeToLiveSeconds(-1);
        //mapConfig.setNearCacheConfig(nearCacheConfig);
        config.addMapConfig(mapConfig);

        NearCacheConfig nearCacheConfig = new NearCacheConfig();
        nearCacheConfig.setInMemoryFormat(InMemoryFormat.OBJECT);
        nearCacheConfig.setInvalidateOnChange(true);
        nearCacheConfig.setLocalUpdatePolicy(NearCacheConfig.LocalUpdatePolicy.INVALIDATE);
        config.getMapConfig(Constants.distributedMap).setNearCacheConfig(nearCacheConfig);
    }

    private static void setDiscoveryMethod(Config config) {
        String env = System.getenv().get("environment");
        if ( "k8s".equals(env)) {
            String serviceName = System.getenv().get("service-name");
            JoinConfig joinConfig = config.getNetworkConfig().getJoin();
            joinConfig.getMulticastConfig().setEnabled(false);
            joinConfig.getTcpIpConfig().setEnabled(false);
            joinConfig.getKubernetesConfig().setEnabled(true).setProperty("service-name", serviceName)
                    .setProperty("namespace", "default");
        }
    }

    public static HazelcastInstance getInstance() {
        if (instance == null) {
            instance = Hazelcast.newHazelcastInstance(getConfig());
        }
        return instance;
    }
}
