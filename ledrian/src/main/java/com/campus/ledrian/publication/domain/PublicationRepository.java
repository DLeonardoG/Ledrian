package com.campus.ledrian.publication.domain;

import java.util.List;
import java.util.Optional;

public interface PublicationRepository {

    List<Publication> findAll();

    Publication save(Publication typeInteration);

    Optional<Publication> findById(Long id);

    void deleteById(Long id);

//    Publication findBy(String publi);
}
