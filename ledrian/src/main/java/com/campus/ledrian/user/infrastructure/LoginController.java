
package com.campus.ledrian.user.infrastructure;

import com.campus.ledrian.security.JWTAuthtenticationConfig;
import com.campus.ledrian.user.application.UserServiceImpl;
import com.campus.ledrian.user.domain.User;
import com.campus.ledrian.user.domain.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private JWTAuthtenticationConfig jwtAuthtenticationConfig;

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/api/login")
    public ResponseEntity<?> login(
            @RequestParam("email") String email,
            @RequestParam("password") String password) {

        if (userService.verifyByEmail(email, password)) {
            String token = jwtAuthtenticationConfig.getJWTToken(email);

            UserDTO user = userService.findByEmail(email);

            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("user", user);

            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}