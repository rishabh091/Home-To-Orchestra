package com.orchestra.orchestra.services;

import com.orchestra.orchestra.modals.User;
import com.orchestra.orchestra.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Date;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean signup(User user) {
        try {
            user.setAccount_date(new Date().toString());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setActive(true);
            userRepository.save(user);

            System.out.println("User " + user.getEmail() + " added to db");
            return true;
        }
        catch (Exception e) {
            System.out.println(e);

            return false;
        }
    }

    public User update(User user, Principal principal) {
        if(userRepository.findByEmail(principal.getName()).isPresent()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }

        return null;
    }
}
