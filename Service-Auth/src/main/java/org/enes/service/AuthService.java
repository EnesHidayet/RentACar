package org.enes.service;

import org.enes.dto.Request.ActivationRequestDto;
import org.enes.dto.Request.LoginRequestDto;
import org.enes.dto.Request.RegisterRequestDto;
import org.enes.dto.Request.UpdatePasswordRequestDto;
import org.enes.dto.Response.RegisterResponseDto;
import org.enes.entity.Auth;
import org.enes.exception.AuthManagerException;
import org.enes.exception.ErrorType;
import org.enes.mapper.AuthMapper;
import org.enes.rabbitmq.producer.RegisterUserProducer;
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
    private final RegisterUserProducer producer;
    public AuthService(JpaRepository<Auth, Long> repository, AuthRepository repository1, RegisterUserProducer producer) {
        super(repository);
        this.repository = repository1;
        this.producer = producer;
    }

    public RegisterResponseDto register(RegisterRequestDto dto){

        Auth auth = save(mapper.fromRegisterRequestToAuth(dto));
        try {
            producer.sendNewUser(mapper.fromAuthToRegisterModel(auth));
        }catch (Exception e){
                throw new AuthManagerException(ErrorType.USER_NOT_CREATED);
        }
        return mapper.fromAuthToRegisterResponseDto(auth);
    }

    public Boolean login(LoginRequestDto dto){
        Optional<Auth> auth = repository.findByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        if (!auth.get().getStatus().equals(EStatus.ACTIVE)){
            throw new AuthManagerException(ErrorType.ACCOUNT_NOT_ACTIVE);
        }
        if (auth.isEmpty()){
            throw new AuthManagerException(ErrorType.LOGIN_ERROR);
        }
        return true;
    }

    public Boolean activateStatus(ActivationRequestDto dto){
        Optional<Auth> auth = repository.findByIdAndActivationCode(dto.getId(), dto.getActivationCode());
        if (auth.isEmpty()){
            throw new AuthManagerException(ErrorType.USER_NOT_FOUND);
        }
        if (!auth.get().getActivationCode().equals(dto.getActivationCode())){
            throw new AuthManagerException(ErrorType.ACTIVATION_CODE_ERROR);
        }
        auth.get().setStatus(EStatus.ACTIVE);
        save(auth.get());
        return true;
    }

    public Boolean update(UpdatePasswordRequestDto dto){
        Optional<Auth> auth = repository.findByIdAndPassword(dto.getId(),dto.getOldPassword());
        if (auth.isEmpty()){
            throw new AuthManagerException(ErrorType.USER_NOT_FOUND);
        }
        auth.get().setPassword(dto.getNewPassword());
        save(auth.get());
        return true;
    }


    public Boolean delete (Long id){
        Optional<Auth> auth = findById(id);
        if (auth.isEmpty()){
            throw new AuthManagerException(ErrorType.USER_NOT_FOUND);
        }
        auth.get().setStatus(EStatus.DELETED);
        save(auth.get());
        return true;
    }

    public Optional<Auth> findByIdAuth(Long id){
        Optional<Auth> auth = findById(id);
        if (auth.isEmpty()){
            throw new AuthManagerException(ErrorType.USER_NOT_FOUND);
        }
        return auth;
    }

    public List<Auth> findAllAuth(){
        return findAll();
    }
}
