
package com.campus.ledrian.user.application;

import com.campus.ledrian.user.domain.RegisterUserDTO;
import com.campus.ledrian.user.domain.User;
import java.util.List;

public interface UserService {
    List<User> getAllUser();
   User getUserById(Long id);
   User findByUsername(Long id);
   User saveUser(User user);
   void deleteUser(Long id);
   boolean emailExists(String email);

    User findByEmail(String email);

    User registrar(RegisterUserDTO registerUserDTO);
}
