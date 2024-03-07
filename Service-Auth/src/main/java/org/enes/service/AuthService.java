package org.enes.service;

import org.enes.dto.Request.ActivationRequestDto;
import org.enes.dto.Request.LoginRequestDto;
import org.enes.dto.Request.RegisterRequestDto;
import org.enes.dto.Request.UpdatePasswordRequestDto;
import org.enes.dto.Response.RegisterResponseDto;
import org.enes.entity.Auth;
import org.enes.mapper.AuthMapper;
import org.enes.repository.AuthRepository;
import org.enes.utility.ServiceManager;
import org.enes.utility.enums.EStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth, Long> {
    private final AuthRepository repository;
    private final AuthMapper mapper = AuthMapper.INSTANCE;
    public AuthService(JpaRepository<Auth, Long> repository, AuthRepository repository1) {
        super(repository);
        this.repository = repository1;
    }

    public RegisterResponseDto register(RegisterRequestDto dto){
        Auth auth = mapper.fromRegisterRequestToAuth(dto);
        save(auth);
        return mapper.fromAuthToRegisterResponseDto(auth);
    }

    public Boolean login(LoginRequestDto dto){
        Optional<Auth> auth = repository.findByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        if (!auth.get().getStatus().equals(EStatus.ACTIVE)){
            throw new RuntimeException("Auth not active");
        }
        if (auth.isEmpty()){
            throw new RuntimeException("Auth not found");
        }
        return true;
    }

    public Boolean activateStatus(ActivationRequestDto dto){
        Optional<Auth> auth = repository.findByIdAndActivationCode(dto.getId(), dto.getActivationCode());
        if (auth.isEmpty()){
            throw new RuntimeException("Auth not found");
        }
        auth.get().setStatus(EStatus.ACTIVE);
        save(auth.get());
        return true;
    }

    public Boolean update(UpdatePasswordRequestDto dto){
        Optional<Auth> auth = repository.findByIdAndPassword(dto.getId(),dto.getOldPassword());
        if (auth.isEmpty()){
            throw new RuntimeException("Auth not found");
        }
        auth.get().setPassword(dto.getNewPassword());
        save(auth.get());
        return true;
    }


    public Boolean delete (Long id){
        Optional<Auth> auth = findById(id);
        if (auth.isEmpty()){
            throw new RuntimeException("Auth not found");
        }
        auth.get().setStatus(EStatus.DELETED);
        save(auth.get());
        return true;
    }

    public Optional<Auth> findByIdAuth(Long id){
        Optional<Auth> auth = findById(id);
        if (auth.isEmpty()){
            throw new RuntimeException("Auth not found");
        }
        return auth;
    }

    public List<Auth> findAllAuth(){
        return findAll();
    }
}
