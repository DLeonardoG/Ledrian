package com.campus.ledrian.publication.service;

import com.campus.ledrian.interation.domain.Interation;
import com.campus.ledrian.interation.domain.InterationDTO;
import com.campus.ledrian.interation.domain.InterationRepository;
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
import org.springframework.transaction.annotation.Transactional;

@Service
public class PublicationServiceImpl implements PublicationService {

    private final PublicationRepository publicationRepository;
    private final UserRepository userRepository;
    private final InterationRepository interationRepository;

    @Autowired
    public PublicationServiceImpl(PublicationRepository publicationRepository, UserRepository userRepository, InterationRepository interationRepository) {
        this.publicationRepository = publicationRepository;
        this.userRepository = userRepository;
        this.interationRepository = interationRepository;
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
    @Transactional
    public PublicationDTO updateDescription(Long id, String description) {
        Publication publication = publicationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Publicación no encontrada para id: " + id));
        publication.setDescription(description);
        Publication updatedPublication = publicationRepository.save(publication);
        return convertToDTO(updatedPublication);
    }

    @Override
    public void deleteById(Long id) {
        publicationRepository.deleteById(id);
    }

    @Override
    public List<PublicationDTO> findByUserId(Long userId) {
        return publicationRepository.findByUserId(userId).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private PublicationDTO convertToDTO(Publication publication) {
        List<Interation> interaciones = interationRepository.findByPublicationId(publication.getId());

        List<InterationDTO> interationDTOs = interaciones.stream()
                .map(interation -> new InterationDTO(
                        interation.getId(),
                        interation.getPublication().getId(),
                        interation.getUserGivingInteration().getId(),
                        interation.getUserReceivingInteration().getId(),
                        interation.getTypeInteration().getId(),
                        interation.getDate(),
                        interation.getComment(),
                        interation.getUsername()
                ))
                .collect(Collectors.toList());

        return new PublicationDTO(
                publication.getId(),
                publication.getDescription(),
                publication.getPhoto(),
                publication.getUser().getUsername(),
                publication.getDate(),
                publication.getPublisher().getId(),
                interationDTOs
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
