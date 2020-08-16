package com.demo.london.user;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest(classes = UserService.class)
class UserServiceTest {

    @Mock
    private User user;

    @Mock
    private UserSet userSet;

    @Test
    void getUsersAroundLondon() {
        UserService service = spy(new UserService());

        doReturn(true).when(service).isCityinRange(user);
        doReturn(userSet).when(service).getAllUsers();

        service.getUsersAroundLondon();

        verify(service).getAllUsers();
    }
}