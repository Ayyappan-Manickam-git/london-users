package com.demo.london.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String ipAddress;

    private String latitude;

    private String longitude;

    private String city;
}
