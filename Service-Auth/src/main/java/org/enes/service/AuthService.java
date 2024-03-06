package org.enes.service;

import org.enes.entity.Auth;
import org.enes.repository.AuthRepository;
import org.enes.utility.ServiceManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService extends ServiceManager<Auth, Long> {
    private final AuthRepository repository;
    public AuthService(JpaRepository<Auth, Long> repository, AuthRepository repository1) {
        super(repository);
        this.repository = repository1;
    }

}
