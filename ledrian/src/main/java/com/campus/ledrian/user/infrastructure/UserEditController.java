package com.campus.ledrian.user.infrastructure;

import com.campus.ledrian.user.application.UserServiceImpl;
import com.campus.ledrian.user.domain.UserEditDTO;
import com.campus.ledrian.user.domain.UserProfileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/edit")
public class UserEditController {

    private final UserServiceImpl userService;

    @Autowired
    public UserEditController(UserServiceImpl userService) {
        this.userService = userService;
    }
    
        @PutMapping("/{userId}")
        public UserEditDTO updateUserProfile(@PathVariable Long userId, @RequestBody UserEditDTO userEditDTO) {
            userEditDTO.setId(userId);
            return userService.saveEditProf(userEditDTO);
        }

}
