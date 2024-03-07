package org.enes.controller;

import lombok.RequiredArgsConstructor;
import org.enes.dto.Request.ActivationRequestDto;
import org.enes.dto.Request.LoginRequestDto;
import org.enes.dto.Request.RegisterRequestDto;
import org.enes.dto.Request.UpdatePasswordRequestDto;
import org.enes.dto.Response.RegisterResponseDto;
import org.enes.entity.Auth;
import org.enes.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

import static org.enes.constant.RestApiUrls.*;

@RestController
@RequestMapping(AUTH)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService service;

    @PostMapping(REGISTER)
    public ResponseEntity<RegisterResponseDto> register(@RequestBody RegisterRequestDto dto){
        return ResponseEntity.ok(service.register(dto));
    }

    @PostMapping(LOGIN)
    public ResponseEntity<Boolean> login(@RequestBody LoginRequestDto dto){
        return ResponseEntity.ok(service.login(dto));
    }

    @PostMapping(ACTIVATE)
    public ResponseEntity<Boolean> activateStatus(@RequestBody ActivationRequestDto dto){
        return ResponseEntity.ok(service.activateStatus(dto));
    }

    @PutMapping(UPDATE)
    public ResponseEntity<Boolean> update(UpdatePasswordRequestDto dto){
        return ResponseEntity.ok(service.update(dto));
    }

    @DeleteMapping(DELETE)
    public ResponseEntity<Boolean> delete(@RequestParam Long id){
        return ResponseEntity.ok(service.delete(id));
    }

    @GetMapping(FIND_BY_ID)
    public ResponseEntity<Optional<Auth>> findById(Long id){
        return ResponseEntity.ok(service.findByIdAuth(id));
    }

    @GetMapping(FIND_ALL)
    public ResponseEntity<List<Auth>> findAll(){
        return ResponseEntity.ok(service.findAllAuth());
    }
}
