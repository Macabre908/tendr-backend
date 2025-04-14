package com.tender.net.controller;

import com.tender.net.entities.User;
import com.tender.net.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/userSignUp")
    public ResponseEntity<Map<String, String>> userSignUp(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        Map<String, String> map = new HashMap<>();
        map.put("status", "success");
        return ResponseEntity.status(HttpStatus.CREATED).body(map);
    }

}
