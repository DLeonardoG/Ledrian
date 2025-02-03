package com.campus.ledrian.interation.domain;

import java.util.List;
import java.util.Optional;

public interface InterationRepository {

    List<Interation> findAll();

    Interation save(Interation interation);

    Optional<Interation> findById(Long id);

    void deleteById(Long id);

    List<Interation> findByPublicationId(Long publicationId);
}
