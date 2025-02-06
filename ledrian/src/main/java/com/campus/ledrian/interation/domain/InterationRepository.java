package com.campus.ledrian.interation.domain;

import org.springframework.data.jpa.repository.Modifying;
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

    @Query(value = "SELECT * FROM interation WHERE user_receiving_id = :userId AND check = false ORDER BY date DESC", nativeQuery = true)
    List<Interation> findUnseenNotificationsByUserId(@Param("userId") Long userId);

    @Modifying
    @Query(value = "UPDATE interation SET check = true WHERE id = :notificationId", nativeQuery = true)
    void markAsSeen(@Param("notificationId") Long notificationId);
}
