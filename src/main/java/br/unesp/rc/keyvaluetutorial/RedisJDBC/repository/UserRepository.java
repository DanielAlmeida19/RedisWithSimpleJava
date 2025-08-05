package br.unesp.rc.keyvaluetutorial.RedisJDBC.repository;

import java.util.Map;

import br.unesp.rc.keyvaluetutorial.RedisJDBC.model.User;
import br.unesp.rc.keyvaluetutorial.RedisJDBC.utils.RedisClientManager;
import io.lettuce.core.api.sync.RedisCommands;

public class UserRepository {
    private RedisCommands<String, String> redisCommands;

    public UserRepository(){
        this.redisCommands = RedisClientManager.getConnection().sync();
    }

    public void save(User user) {
        String key = "user:" + user.getId();
        redisCommands.hset(key, Map.of(
            "id", user.getId(),
            "name", user.getName(),
            "age", String.valueOf(user.getAge())
        ));
    }

    public User findById(String id) {
        String key = "user:" + id;
        Map<String, String> data = redisCommands.hgetall(key);
        if (data == null || data.isEmpty()) return null;

        return new User(
            data.get("id"),
            data.get("name"),
            Integer.parseInt(data.get("age"))
        );
    }

    public void delete(String id){
        redisCommands.del("user:" + id);
    }

    public boolean exists(String id) {
        return redisCommands.exists("user:" + id) > 0;
    }
}
