package com.campus.ledrian.interation.service;

import com.campus.ledrian.interation.domain.InterationDTO;
import java.util.List;
import java.util.Optional;

public interface InterationService {

    List<InterationDTO> findAll();

    Optional<InterationDTO> findById(Long id);

    InterationDTO save(InterationDTO interationDTO);

    void deleteById(Long id);
}
