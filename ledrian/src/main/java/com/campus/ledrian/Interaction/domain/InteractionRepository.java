package com.campus.ledrian.interaction.domain;

import java.util.List;
import java.util.Optional;

public interface InteractionRepository {

    List<Interaction> findAll();

    Interaction save(Interaction interaction);

    Optional<Interaction> findById(Long id);

    void deleteById(Long id);
}
