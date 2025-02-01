package com.campus.ledrian.user.infrastructure;

import com.campus.ledrian.user.application.UserService;
import com.campus.ledrian.user.application.UserServiceImpl;
import com.campus.ledrian.user.domain.User;
import com.campus.ledrian.user.domain.UserProfileDTO;
import com.campus.ledrian.user.domain.UserSearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/search")
    public List<UserSearchDTO> searchUsers(@RequestParam String query) {
        List<User> users = userService.searchUsers(query);
        return users.stream()
                .map(user -> new UserSearchDTO(
                        user.getId(),
                        user.getName(),
                        user.getUsername(),
                        user.getPhoto()
                ))
                .collect(Collectors.toList());
    }

    @GetMapping("/{userId}")
    public UserProfileDTO getUserProfile(@PathVariable Long userId) {
        User user = userService.getUserProfile(userId);

        List<Long> publications = user.getPublications().stream()
                .map(publication -> publication.getId())
                .collect(Collectors.toList());

        List<Long> followersIds = user.getFollowers().stream()
                .map(follow -> follow.getFollower().getId())
                .collect(Collectors.toList());

        List<Long> followingIds = user.getFollowing().stream()
                .map(follow -> follow.getFollowing().getId())
                .collect(Collectors.toList());

        return new UserProfileDTO(
                user.getId(),
                user.getName(),
                user.getLastname(),
                user.getUsername(),
                user.getPhoto(),
                user.getBio(),
                publications,
                followersIds,
                followingIds
        );
    }
}
