package com.travelagency.config;

import com.travelagency.model.Role;
import com.travelagency.model.User;
import com.travelagency.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        if (userService.getUserByEmail("manager@agency.com") == null) {
            User manager = new User();
            manager.setEmail("manager@agency.com");
            manager.setPassword("manager123");
            manager.setFirstName("Manager");
            manager.setLastName("Managerovic");
            manager.setRole(Role.MANAGER);
            userService.saveUser(manager);
        }
    }
}
