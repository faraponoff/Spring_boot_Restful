package com.springBoot.bootstrap1.Bootstrap.rest;

import com.springBoot.bootstrap1.Bootstrap.dto.UserDTO;
import com.springBoot.bootstrap1.Bootstrap.mappers.UserMapper;
import com.springBoot.bootstrap1.Bootstrap.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/")
public class UserRestController {
    @GetMapping(value = "/current/")
    public ResponseEntity<UserDTO> getCurrent() {
        User currentUser = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        return new ResponseEntity<>(UserMapper.INSTANCE.toDTO(currentUser), HttpStatus.OK);
    }
}
