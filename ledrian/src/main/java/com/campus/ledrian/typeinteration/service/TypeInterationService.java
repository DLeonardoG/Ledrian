
package com.campus.ledrian.typeinteration.service;

import com.campus.ledrian.typeinteration.domain.TypeInteration;
import com.campus.ledrian.typeinteration.domain.TypeInterationDTO;
import java.util.List;
import java.util.Optional;

public interface TypeInterationService {
     List<TypeInterationDTO> findAll();
   Optional<TypeInterationDTO> findById(Long id);
   TypeInterationDTO save(TypeInterationDTO typeInterationDTO);
   void delete(Long id);
//   TypeInterationDTO convertToDTO(TypeInteration typeInterationDTO);
//   TypeInteration convertToEntity(TypeInterationDTO typeInterationDTO);
}
