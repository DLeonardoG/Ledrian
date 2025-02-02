
package com.campus.ledrian.typeinteration.infrastructure;

import com.campus.ledrian.typeinteration.domain.TypeInterationDTO;
import com.campus.ledrian.typeinteration.service.TypeInterationServiceImpl;
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
@RequestMapping("/api/typesinterations")
public class TypeInterationController {
    
    private final TypeInterationServiceImpl typeInterationServiceImpl;

    @Autowired
    public TypeInterationController(TypeInterationServiceImpl typeInterationServiceImpl) {
        this.typeInterationServiceImpl = typeInterationServiceImpl;
    }
    
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TypeInterationDTO> getAllTypeInterations() {
        return typeInterationServiceImpl.findAll();
    }
    
    @GetMapping("/{id}")
    public Optional findById(@PathVariable Long id){
        return typeInterationServiceImpl.findById(id);
    }
    
    @PostMapping
    public TypeInterationDTO createTypeInteration(@RequestBody TypeInterationDTO typeInterationDTO) {
        return typeInterationServiceImpl.save(typeInterationDTO);
    }
    
     @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTypeInteration(@PathVariable Long id) {
        typeInterationServiceImpl.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public TypeInterationDTO updateTypeInteration(@PathVariable Long id, @RequestBody TypeInterationDTO typeInterationDTO) {
        typeInterationDTO.setId(id);
        return typeInterationServiceImpl.save(typeInterationDTO);
    }
    
    // asi seria lo basico 
    // no se como usarlo con security
    // creo que ya esta ready brou
    
    
}
