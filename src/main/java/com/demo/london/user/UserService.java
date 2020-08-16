package com.demo.london.user;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    public Set<User> getUsersAroundLondon() {

        var allUsers = getAllUsers();

        if(allUsers == null) {
            return Set.of();
        }

        return allUsers.getUserSet()
                .stream()
                .filter(this::isCityinRange)
                .collect(Collectors.toSet());
    }

    public UserSet getAllUsers() {
        final String uri = "https://bpdts-test-app-v3.herokuapp.com/users";

        return createRestTemplate().getForObject(uri, UserSet.class);
    }

    public boolean isCityinRange(User user) {
        if(Objects.equals("London", user.getCity())) {
            return true;
        }

        return new BigDecimal(user.getLongitude()).signum() > 0
                && new BigDecimal(user.getLatitude()).signum() > 0;
    }

    @Bean
    private RestTemplate createRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(0, createMappingJacksonHttpMessageConverter());
        return restTemplate;
    }

    private MappingJackson2HttpMessageConverter createMappingJacksonHttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(createObjectMapper());
        return converter;
    }

    private ObjectMapper createObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

        SimpleModule module = new SimpleModule();
        module.addDeserializer(UserSet.class, new Deserializer());
        objectMapper.registerModule(module);
        return objectMapper;
    }
}
