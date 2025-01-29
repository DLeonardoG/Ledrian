package com.campus.ledrian.typeinteration.service;

import com.campus.ledrian.typeinteration.domain.TypeInteration;
import com.campus.ledrian.typeinteration.domain.TypeInterationDTO;
import com.campus.ledrian.typeinteration.domain.TypeInterationRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeInterationServiceImpl implements TypeInterationService {

    private final TypeInterationRepository typeInterationRepository;

    @Autowired
    public TypeInterationServiceImpl(TypeInterationRepository typeInterationRepository) {
        this.typeInterationRepository = typeInterationRepository;
    }
    
    
    @Override
    public List<TypeInterationDTO> findAll() {
        return typeInterationRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public  Optional<TypeInterationDTO> findById(Long id) {
        return typeInterationRepository.findById(id)
                .map(this::convertToDTO);
        
        }

    @Override
    public TypeInterationDTO save(TypeInterationDTO typeInterationDTO) {
    TypeInteration typeInteration = convertToEntity(typeInterationDTO);
        TypeInteration savedTypeInteration = typeInterationRepository.save(typeInteration);
        return convertToDTO(savedTypeInteration);
    }

    @Override
    public void delete(Long id) {
        typeInterationRepository.deleteById(id);
    }

    private TypeInterationDTO convertToDTO(TypeInteration typeInterationDTO) {
        return new TypeInterationDTO(
                typeInterationDTO.getId(),
                typeInterationDTO.getType());
    }

    private TypeInteration convertToEntity(TypeInterationDTO typeInterationDTO) {
        TypeInteration typeInteration = new TypeInteration();
        typeInteration.setId(typeInterationDTO.getId());
        typeInteration.setType(typeInterationDTO.getType());
        return typeInteration;
    }

}
