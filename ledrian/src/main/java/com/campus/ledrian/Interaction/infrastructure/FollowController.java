
package com.campus.ledrian.publication.infrastructure;

import com.campus.ledrian.publication.domain.PublicationDTO;
import com.campus.ledrian.publication.service.PublicationServiceImpl;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
    
    @GetMapping("/{id}")
    public Optional findById(@PathVariable Long id){
        return publicationServiceImpl.findById(id);
    }
    
    @PostMapping
    public PublicationDTO createPublication(@RequestBody PublicationDTO publicationDTO) {
        return publicationServiceImpl.save(publicationDTO);
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
