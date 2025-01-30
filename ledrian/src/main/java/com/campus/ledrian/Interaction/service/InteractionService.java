package com.campus.ledrian.interaction.service;

import com.campus.ledrian.interaction.domain.InteractionDTO;
import java.util.List;
import java.util.Optional;

public interface InteractionService {

    List<InteractionDTO> findAll();

    Optional<InteractionDTO> findById(Long id);

    InteractionDTO save(InteractionDTO interactionDTO);

    void deleteById(Long id);
}
