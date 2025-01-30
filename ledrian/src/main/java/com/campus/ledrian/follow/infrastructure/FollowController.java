
package com.campus.ledrian.follow.infrastructure;

import com.campus.ledrian.follow.domain.FollowDTO;
import com.campus.ledrian.follow.service.FollowServiceImpl;
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
@RequestMapping("/api/follows")
public class FollowController {

    private final FollowServiceImpl followServiceImpl;

    @Autowired
    public FollowController(FollowServiceImpl followServiceImpl) {
        this.followServiceImpl = followServiceImpl;
    }
    
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FollowDTO> getAllFollows() {
        return followServiceImpl.findAll();
    }
    
    @GetMapping("/{id}")
    public Optional findById(@PathVariable Long id){
        return followServiceImpl.findById(id);
    }
    
    @PostMapping
    public FollowDTO createFollow(@RequestBody FollowDTO followDTO) {
        return followServiceImpl.save(followDTO);
    }
    
     @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFollow(@PathVariable Long id) {
        followServiceImpl.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public FollowDTO updateFollow(@PathVariable Long id, @RequestBody FollowDTO followDTO) {
        followDTO.setId(id);
        return followServiceImpl.save(followDTO);
    }
}
