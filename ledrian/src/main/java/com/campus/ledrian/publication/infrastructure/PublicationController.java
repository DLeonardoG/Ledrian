
package com.campus.ledrian.publication.infrastructure;

import com.campus.ledrian.publication.domain.PublicationDTO;
import com.campus.ledrian.publication.service.PublicationServiceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/publications")
public class PublicationController {

    private final PublicationServiceImpl publicationServiceImpl;

    @Autowired
    public PublicationController(PublicationServiceImpl publicationServiceImpl) {
        this.publicationServiceImpl = publicationServiceImpl;
    }
    
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PublicationDTO> getAllPublications() {
        return publicationServiceImpl.findAll();
    }

    @GetMapping("/images/{filename}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        try {
            Path filePath = Paths.get("uploads/").resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                        .header("Content-Type", Files.probeContentType(filePath))
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GetMapping("/{id}")
    public Optional findById(@PathVariable Long id){
        return publicationServiceImpl.findById(id);
    }

    @GetMapping("/user/{id}")
    public List<PublicationDTO> findByUserId(@PathVariable Long id){
        return publicationServiceImpl.findByUserId(id);
    }
    
    @PostMapping
    public PublicationDTO createPublication(@RequestBody PublicationDTO publicationDTO) {
        return publicationServiceImpl.save(publicationDTO);
    }

    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public ResponseEntity<String> createPublicationWithImage(
            @RequestParam("description") String description,
            @RequestParam("photo") MultipartFile photo,
            @RequestParam("username") String username,
            @RequestParam("publisherId") Long publisherId) {

        try {
            String uploadDir = "uploads/";
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String fileName = System.currentTimeMillis() + "_" + photo.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);

            Files.copy(photo.getInputStream(), filePath);

            PublicationDTO publicationDTO = new PublicationDTO(
                    null,
                    description,
                    fileName,
                    username,
                    LocalDateTime.now(),
                    publisherId
            );

            publicationServiceImpl.save(publicationDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body("Publicación creada exitosamente");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar la imagen");
        }
    }
    
     @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublication(@PathVariable Long id) {
        publicationServiceImpl.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public PublicationDTO updatePublication(@PathVariable Long id, @RequestBody PublicationDTO publicationDTO) {
        publicationDTO.setId(id);
        return publicationServiceImpl.save(publicationDTO);
    }
    
    // asi seria lo basico 
    // no se como usarlo con security
    // creo que ya esta ready brou
    
    
}
