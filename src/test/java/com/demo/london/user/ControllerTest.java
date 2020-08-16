package com.demo.london.user;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = Controller.class)
class ControllerTest {

    @Autowired
    private Controller controller;

    @MockBean
    private UserService service;

    @Mock
    private User user;

    @Test
    void getUsersInLondon() {
        var result = Set.of(user);
        when(service.getUsersAroundLondon()).thenReturn(result);

        assertEquals(result, controller.getUsersInLondon());
        verify(service).getUsersAroundLondon();
    }
}