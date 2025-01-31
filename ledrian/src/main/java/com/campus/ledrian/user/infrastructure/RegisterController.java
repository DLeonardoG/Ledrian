
package com.campus.ledrian.user.infrastructure;

import com.campus.ledrian.user.application.UserServiceImpl;
import com.campus.ledrian.user.domain.RegisterUserDTO;
import com.campus.ledrian.user.domain.User;
import com.campus.ledrian.user.domain.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/api/register")
    public ResponseEntity<?> register(@RequestBody RegisterUserDTO registerUserDTO) {
        try {
            if (userService.emailExists(registerUserDTO.getEmail())) {
                return ResponseEntity.badRequest().body("El correo ya está registrado.");
            }

            UserDTO savedUser = userService.register(registerUserDTO);

            return ResponseEntity.ok("User registrado exitosamente: " + savedUser.getEmail());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al registrar el User.");
        }
    }
}