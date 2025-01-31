package com.campus.ledrian.user.application;

import com.campus.ledrian.follow.domain.FollowRepository;
import com.campus.ledrian.publication.domain.Publication;
import com.campus.ledrian.publication.domain.PublicationRepository;
import com.campus.ledrian.user.domain.User;
import com.campus.ledrian.user.domain.UserDTO;
import com.campus.ledrian.user.domain.RegisterUserDTO;
import com.campus.ledrian.user.domain.User;
import com.campus.ledrian.user.domain.UserDTO;
import com.campus.ledrian.user.domain.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {

    private final UserRepository userRepository;
    private final PublicationRepository publicationRepository;
    private final FollowRepository followRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PublicationRepository publicationRepository, FollowRepository followRepository) {
        this.userRepository = userRepository;
        this.publicationRepository = publicationRepository;
        this.followRepository = followRepository;
    }

    public UserDTO getUserByEmail(String email) {
        UserDTO userDTO = convertToDTO(userRepository.findByEmail(email));
        return userDTO;
    }

    public Optional<UserDTO> findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(this::convertToDTO);
    }

    public User register(RegisterUserDTO registerUserDTO) {

        User User = new User();
        User.setName(registerUserDTO.getName());
        User.setLastname(registerUserDTO.getLastname());
        User.setEmail(registerUserDTO.getEmail());
        User.setUsername(registerUserDTO.getUsername());
        User.setPassword(passwordEncoder.encode(registerUserDTO.getPassword()));
        User.setPhoto(registerUserDTO.getPhoto());

        return userRepository.save(User);
    }

    public boolean verifyByEmail(String email, String password) {
        UserDTO User = getUserByEmail(email);
        if (User == null) {
            return false;
        }
        return passwordEncoder.matches(password, User.getPassword());
    }

    public boolean emailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }

    public UserDTO findByEmail(String email) {
        UserDTO user = convertToDTO( userRepository.findByEmail(email));
        return user;
    }

    private UserDTO convertToDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getUsername(),
                user.getPassword(),
                user.getPhoto(),
                user.getLastname(),
                user.getBio(),
                user.getPublications().stream()
                        .map(e -> e.getId())
                        .collect(Collectors.toList()),
                user.getFollowers().stream()
                        .map(e -> e.getId())
                        .collect(Collectors.toList()),
                user.getFollowing().stream()
                        .map(e -> e.getId())
                        .collect(Collectors.toList())
        );
    }

   private User convertToEntity(UserDTO userDTO) {
    User user = new User();
    user.setId(userDTO.getId());
    user.setName(userDTO.getName());
    user.setLastname(userDTO.getLastname());
    user.setEmail(userDTO.getEmail());
    user.setUsername(userDTO.getUsername());
    user.setPassword(userDTO.getPassword());
    user.setPhoto(userDTO.getPhoto());
    user.setBio(userDTO.getBio());

    // Convertir followers (IDs) a entidades User
    if (userDTO.getFollowers() != null) {
        List<User> followers = userDTO.getFollowers().stream()
                .map(id -> userRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id)))
                .collect(Collectors.toList());
        user.setFollowers(followers);
    }

    // Convertir following (IDs) a entidades User
    if (userDTO.getFollowing() != null) {
        List<User> following = userDTO.getFollowing().stream()
                .map(id -> userRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id)))
                .collect(Collectors.toList());
        user.setFollowing(following);
    }

    // Convertir publications (IDs) a entidades Publication (si es necesario)
    if (userDTO.getPublications() != null) {
        List<Publication> publications = userDTO.getPublications().stream()
                .map(id -> publicationRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Publication not found with id: " + id)))
                .collect(Collectors.toList());
        user.setPublications(publications);
    }

    return user;
}

}
