package site.it4u.springwebfluxdemo.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import site.it4u.springwebfluxdemo.entity.User;
import site.it4u.springwebfluxdemo.repository.UserRepository;

@Service
public class UserHandler {

    @Autowired
    private UserRepository userRepository;

    public Mono<ServerResponse> handleGetUsers(ServerRequest request) {
        return ServerResponse.ok().body(userRepository.getUsers(), User.class);
    }

    public Mono<ServerResponse> handleGetUserById(ServerRequest request) {
        return userRepository.getUserById(request.pathVariable("id"))
                .flatMap(u -> ServerResponse.ok().body(Mono.just(u), User.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
