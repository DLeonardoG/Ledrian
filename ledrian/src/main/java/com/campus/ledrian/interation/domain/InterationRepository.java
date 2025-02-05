package com.campus.ledrian.interation.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InterationRepository {

    List<Interation> findAll();

    Interation save(Interation interation);

    Optional<Interation> findById(Long id);

    void deleteById(Long id);

    List<Interation> findByPublicationId(Long publicationId);

    @Query("SELECT i FROM Interation i WHERE " +
            "i.userGivingInteration.id = :userId AND " +
            "i.publication.id = :postId AND " +
            "i.typeInteration.id = :typeId")
    Optional<Interation> findByUserGivingIdAndPublicationIdAndTypeInterationId(
            @Param("userId") Long userId,
            @Param("postId") Long postId,
            @Param("typeId") Long typeId
    );
    
    @Query("SELECT i FROM Interation i WHERE i.userReceivingInteration.id = :userId AND i.check = false ORDER BY i.date DESC")
List<Interation> findInterationsByReceiver(@Param("userId") Long userId);

}
