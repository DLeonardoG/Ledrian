package com.campus.ledrian.typeinteration.domain;

import java.util.List;
import java.util.Optional;

public interface TypeInterationRepository {

    List<TypeInteration> findAll();

    TypeInteration save(TypeInteration typeInteration);

    Optional<TypeInteration> findById(Long id);

    void deleteById(Long id);

    Optional<TypeInteration> findByType(String type);

}
