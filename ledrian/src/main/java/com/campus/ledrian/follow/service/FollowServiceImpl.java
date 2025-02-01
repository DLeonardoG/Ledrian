package com.campus.ledrian.follow.service;

import com.campus.ledrian.follow.domain.Follow;
import com.campus.ledrian.follow.domain.FollowDTO;
import com.campus.ledrian.follow.domain.FollowRepository;
import com.campus.ledrian.user.domain.User;
import com.campus.ledrian.user.domain.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowServiceImpl implements FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    @Autowired
    public FollowServiceImpl(FollowRepository followRepository, UserRepository userRepository) {
        this.followRepository = followRepository;
        this.userRepository = userRepository;
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
        return convertToDTO(savedFollow);
    }

    @Override
    public void unfollow(Long followerId, Long followingId) {
        followRepository.deleteByFollowerIdAndFollowingId(followerId, followingId);
    }

    @Override
    public void deleteById(Long id) {
        followRepository.deleteById(id);
    }

    private FollowDTO convertToDTO(Follow follow) {
        return new FollowDTO(
                follow.getId(),
                follow.getUserFollowed().getId(),
                follow.getUserFollowing().getId(),
                follow.getDate()
        );
    }
    private Follow convertToEntity(FollowDTO followDTO) {
        Follow follow = new Follow();
        follow.setId(followDTO.getId());
        User userFollowed = userRepository.findById(followDTO.getUsernameFollowedId())
                .orElseThrow(() -> new IllegalArgumentException("user not found"));;
        follow.setUserFollowed(userFollowed);

        User userFollowing = userRepository.findById(followDTO.getUsernameFollowingId())
                .orElseThrow(() -> new IllegalArgumentException("user not found"));;
        follow.setUserFollowing(userFollowing);
        
        follow.setDate(followDTO.getDate());

        return follow;
    }

}
