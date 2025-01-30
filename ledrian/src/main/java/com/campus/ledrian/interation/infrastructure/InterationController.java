
package com.campus.ledrian.interation.infrastructure;

import com.campus.ledrian.interation.domain.InterationDTO;
import com.campus.ledrian.interation.service.InterationServiceImpl;
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
@RequestMapping("/api/interations")
public class InterationController {

    private final InterationServiceImpl interationServiceImpl;

    @Autowired
    public InterationController(InterationServiceImpl interationServiceImpl) {
        this.interationServiceImpl = interationServiceImpl;
    }
    
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InterationDTO> getAllInterations() {
        return interationServiceImpl.findAll();
    }
    
    @GetMapping("/{id}")
    public Optional findById(@PathVariable Long id){
        return interationServiceImpl.findById(id);
    }
    
    @PostMapping
    public InterationDTO createInteration(@RequestBody InterationDTO interationDTO) {
        return interationServiceImpl.save(interationDTO);
    }
    
     @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInteration(@PathVariable Long id) {
        interationServiceImpl.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public InterationDTO updateInteration(@PathVariable Long id, @RequestBody InterationDTO interationDTO) {
        interationDTO.setId(id);
        return interationServiceImpl.save(interationDTO);
    }
    
}
