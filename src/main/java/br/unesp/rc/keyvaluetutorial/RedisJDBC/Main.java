package br.unesp.rc.keyvaluetutorial.RedisJDBC;

import br.unesp.rc.keyvaluetutorial.RedisJDBC.model.User;
import br.unesp.rc.keyvaluetutorial.RedisJDBC.repository.UserRepository;
import br.unesp.rc.keyvaluetutorial.RedisJDBC.utils.RedisClientManager;

public class Main {
    public static void main(String[] args) {
        
        UserRepository repository = new UserRepository();

        User user = new User("123", "Daniel", 22);
        repository.save(user);

        User userFound = repository.findById("123");
        System.out.println(userFound.getName());

        System.out.println("Exists? " + repository.exists("123"));

        repository.delete("123");
        System.out.println("Exists after deletion? " + repository.exists("123"));

        RedisClientManager.shutdown();
    }
}