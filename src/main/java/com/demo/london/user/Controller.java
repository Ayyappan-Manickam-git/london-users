package com.demo.london.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users", produces = "application/json")
public class Controller {

    @Autowired
    private final UserService userService;

    @GetMapping(path = "/in-london", produces = "application/json")
    public Iterable<User> getUsersInLondon() {
        return userService.getUsersAroundLondon();
    }
}
