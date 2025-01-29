
package com.campus.ledrian.typeinteration.infrastructure;

import com.campus.ledrian.typeinteration.domain.TypeInteration;
import com.campus.ledrian.typeinteration.domain.TypeInterationRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTypeInterationRepository extends JpaRepository<TypeInteration, Long>, TypeInterationRepository {
    
}
