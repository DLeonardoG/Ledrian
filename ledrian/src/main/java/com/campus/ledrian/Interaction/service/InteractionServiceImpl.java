package com.campus.ledrian.interaction.service;

import com.campus.ledrian.interaction.domain.Interaction;
import com.campus.ledrian.interaction.domain.InteractionDTO;
import com.campus.ledrian.interaction.domain.InteractionRepository;
import com.campus.ledrian.typeinteration.domain.TypeInterationRepository;
import com.campus.ledrian.user.domain.User;
import com.campus.ledrian.user.domain.UserRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InteractionServiceImpl implements InteractionService {

    private final InteractionRepository interactionRepository;
    private final UserRepository userRepository;
    private final InteractionRepository interactionRepository1;
    private final TypeInterationRepository typeInteractionRepository;

    @Autowired
    public InteractionServiceImpl(InteractionRepository interactionRepository, UserRepository userRepository, InteractionRepository interactionRepository1, TypeInterationRepository typeInteractionRepository) {
        this.interactionRepository = interactionRepository;
        this.userRepository = userRepository;
        this.interactionRepository1 = interactionRepository1;
        this.typeInteractionRepository = typeInteractionRepository;
    }

    @Override
    public List<InteractionDTO> findAll() {
        return interactionRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<InteractionDTO> findById(Long id) {
        return interactionRepository.findById(id)
                .map(this::convertToDTO);

    }

    @Override
    public InteractionDTO save(InteractionDTO interactionDTO) {
        Interaction interaction = convertToEntity(interactionDTO);
        Interaction savedInteraction = interactionRepository.save(interaction);
        return convertToDTO(savedInteraction);
    }

    @Override
    public void deleteById(Long id) {
        interactionRepository.deleteById(id);
    }

    private InteractionDTO convertToDTO(Interaction interaction) {
        return new InteractionDTO(
                 interaction.getId(), 
                 interaction.getPublication().getId(), 
                 interaction.getUserReceivingInteraction().getUsername(), 
                 interaction.getUserGivingInteraction().getUsername(), 
                 interaction.getTypeInteraction().getType(), 
                 interaction.getDate()); 
    }

    private Interaction convertToEntity(InteractionDTO interactionDTO) {
        Interaction interaction = new Interaction();
        interaction.setId(interactionDTO.getId());
        User following = userRepository.findByUsername(interactionDTO.getUsernameInteractioned())
                .orElseThrow(() -> new IllegalArgumentException("user not found"));;
        interaction.setUserInteractioned(userInteractioned);

        User userInteractioning = userRepository.findByUsername(interactionDTO.getUsernameInteractioning())
                .orElseThrow(() -> new IllegalArgumentException("user not found"));;
        interaction.setUserInteractioning(userInteractioning);

        interaction.setDate(interactionDTO.getDate());

        return interaction;
    }

}
