
package com.campus.ledrian.interation.infrastructure;

import com.campus.ledrian.interation.domain.CommentDTO;
import com.campus.ledrian.interation.domain.Interation;
import com.campus.ledrian.interation.domain.InterationDTO;
import com.campus.ledrian.interation.domain.InterationRepository;
import com.campus.ledrian.interation.service.InterationServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/interations")
public class InterationController {

    private final InterationServiceImpl interationServiceImpl;
    private final InterationRepository interationRepository;

    @Autowired
    public InterationController(InterationServiceImpl interationServiceImpl, InterationRepository interationRepository) {
        this.interationServiceImpl = interationServiceImpl;
        this.interationRepository = interationRepository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InterationDTO> getAllInterations() {
        return interationServiceImpl.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InterationDTO> getInterationById(@PathVariable Long id) {
        Optional<InterationDTO> interationDTO = interationServiceImpl.findById(id);
        return interationDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/comment")
    public ResponseEntity<InterationDTO> updateComment(@PathVariable Long id, @RequestBody Map<String, String> payload) {
        if (payload == null || !payload.containsKey("comment")) {
            return ResponseEntity.badRequest().build();
        }

        Optional<InterationDTO> optionalInterationDTO = interationServiceImpl.findById(id);
        if (!optionalInterationDTO.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        InterationDTO interationDTO = optionalInterationDTO.get();
        interationDTO.setComment(payload.get("comment"));

        InterationDTO updatedInteration = interationServiceImpl.save(interationDTO);
        return ResponseEntity.ok(updatedInteration);
    }

    @PostMapping
    public ResponseEntity<InterationDTO> createInteration(@RequestBody InterationDTO interationDTO) {
        if (interationDTO == null) {
            return ResponseEntity.badRequest().build();
        }
        InterationDTO savedInteration = interationServiceImpl.save(interationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedInteration);
    }


    @PutMapping("/{id}")
    public ResponseEntity<InterationDTO> updateInteration(@PathVariable Long id, @RequestBody InterationDTO interationDTO) {
        if (interationDTO == null || id == null) {
            return ResponseEntity.badRequest().build();
        }
        interationDTO.setId(id);
        InterationDTO updatedInteration = interationServiceImpl.save(interationDTO);
        return ResponseEntity.ok(updatedInteration);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInteration(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        interationServiceImpl.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/check-like")
    public ResponseEntity<Map<String, Object>> checkIfUserLikedPost(
            @RequestParam Long userId,
            @RequestParam Long postId) {

        Optional<Interation> interaction = interationRepository.findByUserGivingIdAndPublicationIdAndTypeInterationId(
                userId,
                postId,
                1L
        );

        Map<String, Object> response = new HashMap<>();
        response.put("hasLiked", interaction.isPresent());
        interaction.ifPresent(i -> response.put("interactionId", i.getId()));

        return ResponseEntity.ok(response);
    }
}
