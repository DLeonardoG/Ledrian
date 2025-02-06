
package com.campus.ledrian.interation.infrastructure;

import com.campus.ledrian.interation.domain.Interation;
import com.campus.ledrian.interation.domain.InterationRepository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaInterationRepository extends JpaRepository<Interation, Long>, InterationRepository {
    @Query("SELECT i FROM Interation i WHERE i.userReceivingInteration.id = :userId AND i.check = false ORDER BY i.date DESC")
List<Interation> findInterationsByReceiver(@Param("userId") Long userId);

    @Query(value = "SELECT * FROM interation WHERE user_receiving_id = :userId AND check = false ORDER BY date DESC", nativeQuery = true)
    List<Interation> findUnseenNotificationsByUserId(@Param("userId") Long userId);

    @Modifying
    @Query(value = "UPDATE interation SET check = true WHERE id = :notificationId", nativeQuery = true)
    void markAsSeen(@Param("notificationId") Long notificationId);
}

