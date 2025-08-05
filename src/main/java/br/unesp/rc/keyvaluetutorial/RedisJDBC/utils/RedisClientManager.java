package br.unesp.rc.keyvaluetutorial.RedisJDBC.utils;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;

public class RedisClientManager {
    
    private static final RedisClient redisClient = RedisClient.create("redis://localhost:6379");
    private static final StatefulRedisConnection<String, String> connection = redisClient.connect();
    
    public static StatefulRedisConnection<String, String> getConnection(){
        return connection;
    }

    public static void shutdown(){
        connection.close();
        redisClient.shutdown();
    }
}
