
package com.campus.ledrian.interation.infrastructure;

import com.campus.ledrian.interation.domain.Interation;
import com.campus.ledrian.interation.domain.InterationRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaInterationRepository extends JpaRepository<Interation, Long>, InterationRepository {
    
}
