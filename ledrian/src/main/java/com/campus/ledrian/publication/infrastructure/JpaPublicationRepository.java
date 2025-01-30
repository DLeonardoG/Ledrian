
package com.campus.ledrian.publication.infrastructure;

import com.campus.ledrian.publication.domain.Publication;
import com.campus.ledrian.publication.domain.PublicationRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPublicationRepository extends JpaRepository<Publication, Long>, PublicationRepository {
    
}
