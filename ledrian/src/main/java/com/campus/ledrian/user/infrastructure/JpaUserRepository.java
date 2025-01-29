
package com.campus.ledrian.user.infrastructure;

import com.campus.ledrian.user.domain.User;
import com.campus.ledrian.user.domain.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepository extends JpaRepository<User, Long>, UserRepository{
    
}
