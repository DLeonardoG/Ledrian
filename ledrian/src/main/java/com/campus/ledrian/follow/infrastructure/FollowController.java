
package com.campus.ledrian.follow.infrastructure;

import com.campus.ledrian.follow.domain.FollowDTO;
import com.campus.ledrian.follow.service.FollowServiceImpl;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/unfollow")
    public ResponseEntity<Void> unfollow(
            @RequestParam Long followerId,
            @RequestParam Long followingId) {
        followServiceImpl.unfollow(followerId, followingId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public FollowDTO updateFollow(@PathVariable Long id, @RequestBody FollowDTO followDTO) {
        followDTO.setId(id);
        return followServiceImpl.save(followDTO);
    }
}
