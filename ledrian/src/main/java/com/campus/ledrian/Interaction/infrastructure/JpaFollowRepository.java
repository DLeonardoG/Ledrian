
package com.campus.ledrian.follow.infrastructure;

import com.campus.ledrian.follow.domain.Follow;
import com.campus.ledrian.follow.domain.FollowRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaFollowRepository extends JpaRepository<Follow, Long>, FollowRepository {
    
}
