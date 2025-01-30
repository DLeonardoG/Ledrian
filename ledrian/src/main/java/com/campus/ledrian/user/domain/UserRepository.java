
package com.campus.ledrian.user.domain;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    
    List<User> findAll();
    User save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);
    User findByEmail(String email);
    void deleteById(Long id);
}
