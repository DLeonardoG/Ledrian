package com.campus.ledrian.publication.service;

import com.campus.ledrian.publication.domain.Publication;
import com.campus.ledrian.publication.domain.PublicationDTO;
import com.campus.ledrian.publication.domain.PublicationRepository;
import com.campus.ledrian.user.domain.User;
import com.campus.ledrian.user.domain.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicationServiceImpl implements PublicationService {

    private final PublicationRepository publicationRepository;
    private final UserRepository userRepository;

    @Autowired
    public PublicationServiceImpl(PublicationRepository publicationRepository, UserRepository userRepository) {
        this.publicationRepository = publicationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<PublicationDTO> findAll() {
        return publicationRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PublicationDTO> findById(Long id) {
        return publicationRepository.findById(id)
                .map(this::convertToDTO);

    }

    @Override
    public PublicationDTO save(PublicationDTO publicationDTO) {
        Publication publication = convertToEntity(publicationDTO);
        Publication savedPublication = publicationRepository.save(publication);
        return convertToDTO(savedPublication);
    }

    @Override
    public void deleteById(Long id) {
        publicationRepository.deleteById(id);
    }

    private PublicationDTO convertToDTO(Publication publication) {
        return new PublicationDTO(
                publication.getId(),
                publication.getDescription(),
                publication.getPhoto(),
                publication.getUser().getUsername(),
                publication.getDate()
        );
    }

    private Publication convertToEntity(PublicationDTO publicationDTO) {
        Publication publication = new Publication();
        publication.setId(publicationDTO.getId());
        publication.setDescription(publicationDTO.getDescription());
        publication.setPhoto(publicationDTO.getPhoto());
        publication.setDate(publicationDTO.getDate());
        User user = userRepository.findByUsername(publicationDTO.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("user not found"));;
        publication.setUser(user);
        return publication;
    }

}
