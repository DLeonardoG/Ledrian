package com.campus.ledrian.interation.service;

import com.campus.ledrian.interation.domain.Interation;
import com.campus.ledrian.interation.domain.InterationDTO;
import com.campus.ledrian.interation.domain.InterationRepository;
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

@Service
public class InterationServiceImpl implements InterationService {

    private final InterationRepository interactionRepository;
    private final UserRepository userRepository;
    private final InterationRepository interactionRepository1;
    private final TypeInterationRepository typeInterationRepository;
    private final PublicationRepository publicationRepository;

    @Autowired
    public InterationServiceImpl(InterationRepository interactionRepository, UserRepository userRepository, InterationRepository interactionRepository1, TypeInterationRepository typeInterationRepository, PublicationRepository publicationRepository) {
        this.interactionRepository = interactionRepository;
        this.userRepository = userRepository;
        this.interactionRepository1 = interactionRepository1;
        this.typeInterationRepository = typeInterationRepository;
        this.publicationRepository = publicationRepository;
    }

    @Override
    public List<InterationDTO> findAll() {
        return interactionRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<InterationDTO> findById(Long id) {
        return interactionRepository.findById(id)
                .map(this::convertToDTO);

    }

    @Override
    public InterationDTO save(InterationDTO interationDTO) {
        Interation interaction = convertToEntity(interationDTO);
        Interation savedInteration = interactionRepository.save(interaction);
        return convertToDTO(savedInteration);
    }

    @Override
    public void deleteById(Long id) {
        interactionRepository.deleteById(id);
    }

    private InterationDTO convertToDTO(Interation interaction) {
        return new InterationDTO(
                interaction.getId(),
                interaction.getPublication().getId(),
                interaction.getUserReceivingInteration().getUsername(),
                interaction.getUserGivingInteration().getUsername(),
                interaction.getTypeInteration().getType(),
                interaction.getDate());
    }

    private Interation convertToEntity(InterationDTO interationDTO) {
        Interation interaction = new Interation();
        interaction.setId(interationDTO.getId());

        Publication publication = publicationRepository.findById(interationDTO.getIdPublication())
                .orElseThrow(() -> new IllegalArgumentException("publi not found"));;
        interaction.setPublication(publication);

        User userReceivingInteration = userRepository.findByUsername(interationDTO.getUserReceivingInteration())
                .orElseThrow(() -> new IllegalArgumentException("user not found"));;
        interaction.setUserReceivingInteration(userReceivingInteration);
        
        User userGivingInteration = userRepository.findByUsername(interationDTO.getUserGivingInteration())
                .orElseThrow(() -> new IllegalArgumentException("user not found"));;
        interaction.setUserGivingInteration(userGivingInteration);

        TypeInteration typeInteration = typeInterationRepository.findByType(interationDTO.getType())
                .orElseThrow(() -> new IllegalArgumentException("user not found"));;
        interaction.setTypeInteration(typeInteration);

        interaction.setDate(interationDTO.getDate());

        return interaction;
    }

}
