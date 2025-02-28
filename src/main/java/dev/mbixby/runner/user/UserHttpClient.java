package dev.mbixby.runner.user;

import org.springframework.web.service.annotation.GetExchange;

import java.util.List;
// contracts for methods that we want
public interface UserHttpClient {

    @GetExchange("/users")
    List<User> findAll();

    @GetExchange("/{id}")
    User findById(Integer id);

}