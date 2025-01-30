package com.campus.ledrian.follow.service;

import com.campus.ledrian.follow.domain.FollowDTO;
import java.util.List;
import java.util.Optional;

public interface FollowService {

    List<FollowDTO> findAll();

    Optional<FollowDTO> findById(Long id);

    FollowDTO save(FollowDTO followDTO);

    void deleteById(Long id);
}
