package br.unesp.rc.keyvaluetutorial.RedisJDBC;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

public class Main {
    public static void main(String[] args) {
        RedisClient redisClient = RedisClient.create("redis://localhost:6379");
        StatefulRedisConnection<String, String> connection = redisClient.connect();

        System.out.println("Connected to Redis");

        RedisCommands<String, String> commands = connection.sync();
        commands.set("Name", "Daniel");

        String value = commands.get("Name");

        System.out.println(value);

        connection.close();
        redisClient.shutdown();
    }
}