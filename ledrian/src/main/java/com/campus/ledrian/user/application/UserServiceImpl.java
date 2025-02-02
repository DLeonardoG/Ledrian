package com.campus.ledrian.user.application;

import com.campus.ledrian.follow.domain.Follow;
import com.campus.ledrian.follow.domain.FollowRepository;
import com.campus.ledrian.publication.domain.Publication;
import com.campus.ledrian.publication.domain.PublicationRepository;
import com.campus.ledrian.user.domain.RegisterUserDTO;
import com.campus.ledrian.user.domain.User;
import com.campus.ledrian.user.domain.UserEditDTO;
import com.campus.ledrian.user.domain.UserProfileDTO;
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
    private PasswordEncoder passwordEncoder;
    @Autowired
    private PublicationRepository publicationRepo;

    @Autowired
    private FollowRepository followRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
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
        User User = getUserByEmail(email);
        if (User == null) {
            return false;
        }
        return passwordEncoder.matches(password, User.getPassword());
    }

    public boolean emailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> searchUsers(String query) {
        return userRepository.findByNameContainingOrUsernameContaining(query, query);
    }

    public User getUserProfile(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public UserEditDTO saveEditProf(UserEditDTO userEditDTO) {
        User user = convertToEntity(userEditDTO);
        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    public UserEditDTO convertToDTO(User user) {
        UserEditDTO dto = new UserEditDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setLastname(user.getLastname());
        dto.setUsername(user.getUsername());
        dto.setPhoto(user.getPhoto());
        dto.setBio(user.getBio());

        // Convertir las publicaciones a una lista de IDs
        List<Long> publicationIds = user.getPublications().stream()
                .map(Publication::getId)
                .collect(Collectors.toList());
        dto.setPublications(publicationIds);

        // Convertir los seguidores a una lista de IDs
        List<Long> followerIds = user.getFollowers().stream()
                .map(follow -> follow.getFollower().getId())
                .collect(Collectors.toList());
        dto.setFollowersIds(followerIds);

        // Convertir los seguidos a una lista de IDs
        List<Long> followingIds = user.getFollowing().stream()
                .map(follow -> follow.getFollowing().getId())
                .collect(Collectors.toList());
        dto.setFollowingIds(followingIds);

        return dto;
    }

    public User convertToEntity(UserEditDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setLastname(dto.getLastname());
        user.setUsername(dto.getUsername());
        user.setPhoto(dto.getPhoto());
        user.setBio(dto.getBio());

        // Asignar publicaciones (si es necesario)
        if (dto.getPublications() != null) {
            List<Publication> publications = dto.getPublications().stream()
                    .map(publicationRepo::findById) // Buscar cada publicación por ID
                    .filter(Optional::isPresent) // Filtrar las que existen
                    .map(Optional::get) // Obtener la publicación
                    .collect(Collectors.toList());
            user.setPublications(publications);
        }

        return user;
    }

}
