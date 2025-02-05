package com.campus.ledrian.user.infrastructure;

import com.campus.ledrian.user.application.UserService;
import com.campus.ledrian.user.application.UserServiceImpl;
import com.campus.ledrian.user.domain.*;
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

    @PutMapping("/edit/{userId}")
    public LoginResponseDTO editUser(@PathVariable long userId, @RequestBody LoginResponseDTO loginResponseDTO) {
        User updatedUser = userService.edit(userId, loginResponseDTO);
        LoginResponseDTO response = new LoginResponseDTO();
        response.setId(updatedUser.getId());
        response.setName(updatedUser.getName());
        response.setLastname(updatedUser.getLastname());
        response.setEmail(updatedUser.getEmail());
        response.setUsername(updatedUser.getUsername());
        response.setBio(updatedUser.getBio());
        return response;
    }

    @GetMapping("/followings/{userId}")
    public UserProfileDTO getFollowingCard(@PathVariable long userId) {
        User user = userService.getUserProfile(userId);

        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setId(user.getId());
        userProfileDTO.setName(user.getName());
        userProfileDTO.setLastname(user.getLastname());
        userProfileDTO.setUsername(user.getUsername());
        userProfileDTO.setPhoto(user.getPhoto());
        return userProfileDTO;
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
