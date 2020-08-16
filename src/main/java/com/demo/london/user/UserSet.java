package com.demo.london.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class UserSet {

    private Set<User> userSet;
}
