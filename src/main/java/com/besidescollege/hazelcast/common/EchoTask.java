package com.besidescollege.hazelcast.common;

import java.io.Serializable;
import java.util.concurrent.Callable;

public class EchoTask implements Callable, Serializable {

    private final String msg;

    public EchoTask( String msg ) {
        this.msg = msg;
    }

    @Override
    public Object call() throws Exception {
        try {
            Thread.sleep( 2000 );
        } catch ( InterruptedException e ) {
        }
        System.out.println( "echo:" + msg );
        return null;
    }
}