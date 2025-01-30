package com.campus.ledrian.follow.domain;

import java.util.List;
import java.util.Optional;

public interface FollowRepository {

    List<Follow> findAll();

    Follow save(Follow follow);

    Optional<Follow> findById(Long id);

    void deleteById(Long id);

//    Follow findBy(String publi);
}
