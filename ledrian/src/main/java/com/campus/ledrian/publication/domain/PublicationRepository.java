package com.campus.ledrian.publication.domain;

import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PublicationRepository {

    List<Publication> findAll();

    Publication save(Publication typeInteration);

    Optional<Publication> findById(Long id);

    void deleteById(Long id);

    @Query("select p from Publication p where p.publisher.id = :id")
    List<Publication> findByUserId(Long id);

//    Publication findBy(String publi);
}
