package org.enes.controller;

import lombok.RequiredArgsConstructor;
import org.enes.dto.request.CreateUserRequestDto;
import org.enes.entity.UserProfile;
import org.enes.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static org.enes.constant.RestApiUrls.*;
@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping(CREATE)
    public ResponseEntity<Boolean> createUser(CreateUserRequestDto dto){
        return ResponseEntity.ok(service.createUser(dto));
    }

    @GetMapping(FIND_BY_ID)
    public ResponseEntity<Optional<UserProfile>> findByUser(String id){
        return ResponseEntity.ok(service.findByIdUser(id));
    }

    @GetMapping(FIND_ALL)
    public ResponseEntity<List<UserProfile>> findAll() {
        return ResponseEntity.ok(service.findAllUser());
    }


}
