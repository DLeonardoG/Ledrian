package com.campus.ledrian.interation.service;

import com.campus.ledrian.interation.domain.CommentDTO;
import com.campus.ledrian.interation.domain.Interation;
import com.campus.ledrian.interation.domain.InterationDTO;
import com.campus.ledrian.interation.domain.InterationRepository;
import com.campus.ledrian.notification.application.NotificationServiceImpl;
import com.campus.ledrian.notification.domain.NotificationDTO;
import com.campus.ledrian.publication.domain.Publication;
import com.campus.ledrian.publication.domain.PublicationRepository;
import com.campus.ledrian.typeinteration.domain.TypeInteration;
import com.campus.ledrian.typeinteration.domain.TypeInterationRepository;
import com.campus.ledrian.user.domain.User;
import com.campus.ledrian.user.domain.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.stream.events.Comment;

@Service
public class InterationServiceImpl implements InterationService {

    private final NotificationServiceImpl notificationService;
    private final InterationRepository interationRepository;
    private final UserRepository userRepository;
    private final TypeInterationRepository typeInterationRepository;
    private final PublicationRepository publicationRepository;


    @Autowired
    public InterationServiceImpl(NotificationServiceImpl notificationService, InterationRepository interationRepository, UserRepository userRepository, TypeInterationRepository typeInterationRepository, PublicationRepository publicationRepository) {
        this.notificationService = notificationService;
        this.interationRepository = interationRepository;
        this.userRepository = userRepository;
        this.typeInterationRepository = typeInterationRepository;
        this.publicationRepository = publicationRepository;
    }


    @Override
    public List<InterationDTO> findAll() {
        return interationRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<InterationDTO> findById(Long id) {
        return interationRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Override
    public InterationDTO save(InterationDTO interationDTO) {
        if (interationDTO == null) {
            throw new IllegalArgumentException("El DTO de interacción no puede ser nulo");
        }

        Interation interation = convertToEntity(interationDTO);
        Interation savedInteration = interationRepository.save(interation);

        NotificationDTO notificationDTO = new NotificationDTO();
        if (savedInteration.getTypeInteration().getId() == 1) {
            notificationDTO.setType("Like");
            notificationDTO.setContent(savedInteration.getUserGivingInteration().getUsername() + " liked your post");
        } else if (savedInteration.getTypeInteration().getId() == 2){
            notificationDTO.setType("Comment");
            notificationDTO.setContent(savedInteration.getUserGivingInteration().getUsername() + ": "+savedInteration.getComment());
        } else {
            notificationDTO.setType("Interation");
            notificationDTO.setContent("You recived an interation from " + savedInteration.getUserGivingInteration().getUsername());
        }

        notificationDTO.setIdGiver(savedInteration.getUserGivingInteration().getId());
        notificationDTO.setIdReceiver(savedInteration.getPublication().getPublisher().getId());
        notificationService.createNotification(notificationDTO);

        return convertToDTO(savedInteration);
    }


    @Override
    public void deleteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID de la interacción no puede ser nulo");
        }
        interationRepository.deleteById(id);
    }

    private InterationDTO convertToDTO(Interation interation) {
        return new InterationDTO(
                interation.getId(),
                interation.getPublication().getId(),
                interation.getUserGivingInteration().getId(),
                interation.getUserReceivingInteration().getId(),
                interation.getTypeInteration().getId(),
                interation.getDate(),
                interation.getUsername()
        );
    }

    private CommentDTO convertCommentToDTO(Interation interation) {
        return new CommentDTO(
                interation.getId(),
                interation.getPublication().getId(),
                interation.getUserGivingInteration().getId(),
                interation.getUserReceivingInteration().getId(),
                interation.getTypeInteration().getId(),
                interation.getDate(),
                interation.getComment()
        );
    }

    private Interation convertToEntity(InterationDTO interationDTO) {
        if (interationDTO == null) {
            throw new IllegalArgumentException("El DTO de interacción no puede ser nulo");
        }

        Interation interation = new Interation();
        interation.setId(interationDTO.getId());

        Publication publication = publicationRepository.findById(interationDTO.getPublicationId())
                .orElseThrow(() -> new IllegalArgumentException("Publicación no encontrada con ID: " + interationDTO.getPublicationId()));
        interation.setPublication(publication);

        User userGiving = userRepository.findById(interationDTO.getUserGivingId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario que da la interacción no encontrado con ID: " + interationDTO.getUserGivingId()));
        interation.setUserGivingInteration(userGiving);

        User userReceiving = userRepository.findById(interationDTO.getUserReceivingId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario que recibe la interacción no encontrado con ID: " + interationDTO.getUserReceivingId()));
        interation.setUserReceivingInteration(userReceiving);

        TypeInteration typeInteration = typeInterationRepository.findById(interationDTO.getTypeInterationId())
                .orElseThrow(() -> new IllegalArgumentException("Tipo de interacción no encontrado con ID: " + interationDTO.getTypeInterationId()));
        interation.setTypeInteration(typeInteration);

        interation.setDate(interationDTO.getDate());

        interation.setComment(interationDTO.getComment());

        interation.setUsername(interationDTO.getUsername());

        return interation;
    }

    private Interation convertCommentToEntity(CommentDTO commentDTO) {
        if (commentDTO == null) {
            throw new IllegalArgumentException("El DTO de interacción no puede ser nulo");
        }

        Interation interation = new Interation();
        interation.setId(commentDTO.getId());

        Publication publication = publicationRepository.findById(commentDTO.getPublicationId())
                .orElseThrow(() -> new IllegalArgumentException("Publicación no encontrada con ID: " + commentDTO.getPublicationId()));
        interation.setPublication(publication);

        User userGiving = userRepository.findById(commentDTO.getUserGivingId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario que da la interacción no encontrado con ID: " + commentDTO.getUserGivingId()));
        interation.setUserGivingInteration(userGiving);

        User userReceiving = userRepository.findById(commentDTO.getUserReceivingId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario que recibe la interacción no encontrado con ID: " + commentDTO.getUserReceivingId()));
        interation.setUserReceivingInteration(userReceiving);

        TypeInteration typeInteration = typeInterationRepository.findById(commentDTO.getTypeInterationId())
                .orElseThrow(() -> new IllegalArgumentException("Tipo de interacción no encontrado con ID: " + commentDTO.getTypeInterationId()));
        interation.setTypeInteration(typeInteration);

        interation.setDate(commentDTO.getDate());

        interation.setComment(commentDTO.getComment());

        return interation;
    }
}
