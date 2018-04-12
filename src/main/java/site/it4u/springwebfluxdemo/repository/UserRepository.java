package site.it4u.springwebfluxdemo.repository;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import site.it4u.springwebfluxdemo.entity.User;

import java.util.Arrays;
import java.util.List;

@Repository
public class UserRepository {

    private final List<User> userList = Arrays.asList(new User(1L, "zhangsan"), new User(2L, "lisi"));

    public Mono<User> getUserById(String id) {
        return Mono.justOrEmpty(userList.stream().filter(u -> u.getId().equals(Long.valueOf(id))).findFirst().orElse(null));
    }

    public Flux<User> getUsers() {
        return Flux.fromIterable(userList);
    }
}
