
package com.campus.ledrian.publication.service;

import com.campus.ledrian.publication.domain.PublicationDTO;
import java.util.List;
import java.util.Optional;

public interface PublicationService {
     List<PublicationDTO> findAll();
   Optional<PublicationDTO> findById(Long id);
   PublicationDTO save(PublicationDTO typeInterationDTO);
   void deleteById(Long id);
//   Publication(TypeInteration typeInterationDTO);
//   TypeInteration convertToEntity(Publication);
}
