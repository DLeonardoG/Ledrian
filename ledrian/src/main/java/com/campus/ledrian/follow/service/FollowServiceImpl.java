package com.campus.ledrian.follow.service;

import com.campus.ledrian.follow.domain.Follow;
import com.campus.ledrian.follow.domain.FollowDTO;
import com.campus.ledrian.follow.domain.FollowRepository;
import com.campus.ledrian.notification.application.NotificationServiceImpl;
import com.campus.ledrian.notification.domain.NotificationDTO;
import com.campus.ledrian.notification.domain.NotificationRepository;
import com.campus.ledrian.user.domain.User;
import com.campus.ledrian.user.domain.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FollowServiceImpl implements FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    private final NotificationServiceImpl notificationService;



    @Autowired
    public FollowServiceImpl(FollowRepository followRepository, UserRepository userRepository, NotificationServiceImpl notificationService) {
        this.followRepository = followRepository;
        this.userRepository = userRepository;
        this.notificationService = notificationService;
    }



    @Override
    public List<FollowDTO> findAll() {
        return followRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<FollowDTO> findById(Long id) {
        return followRepository.findById(id)
                .map(this::convertToDTO);

    }

    @Override
    public FollowDTO save(FollowDTO followDTO) {
        Follow follow = convertToEntity(followDTO);
        Follow savedFollow = followRepository.save(follow);

        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setType("Follow");
        notificationDTO.setContent(follow.getFollowing().getUsername() + " started following you...");
        notificationDTO.setIdGiver(follow.getFollowing().getId());
        notificationDTO.setIdReceiver(follow.getFollower().getId());
        notificationService.createNotification(notificationDTO);
        return convertToDTO(savedFollow);
    }

    @Transactional
    @Override
    public void unfollow(Long followerId, Long followingId) {
        followRepository.deleteByFollower_IdAndFollowing_Id(followerId, followingId);
    }

    @Override
    public void deleteById(Long id) {
        followRepository.deleteById(id);
    }

    private FollowDTO convertToDTO(Follow follow) {
        return new FollowDTO(
                follow.getId(),
                follow.getFollower().getId(),
                follow.getFollowing().getId(),
                follow.getDate()
        );
    }
    private Follow convertToEntity(FollowDTO followDTO) {
        Follow follow = new Follow();
        follow.setId(followDTO.getId());

        User userFollowing = userRepository.findById(followDTO.getUsernameFollowingId())
                .orElseThrow(() -> new IllegalArgumentException("user not found"));
        follow.setFollower(userFollowing);

        User userFollowed = userRepository.findById(followDTO.getUsernameFollowedId())
                .orElseThrow(() -> new IllegalArgumentException("user not found"));
        follow.setFollowing(userFollowed);
        
        follow.setDate(followDTO.getDate());
        if (followDTO.getUsernameFollowedId().equals(followDTO.getUsernameFollowingId())) {
            throw new IllegalArgumentException("Un usuario no puede seguirse a sí mismo.");
        }

        return follow;
    }

}
