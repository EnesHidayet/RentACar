package org.enes.service;


import org.enes.dto.request.CreateUserRequestDto;
import org.enes.entity.UserProfile;
import org.enes.mapper.UserProfileMapper;
import org.enes.repository.UserRepository;
import org.enes.utility.ServiceManager;
import org.enes.utility.enums.EStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService extends ServiceManager<UserProfile,String > {

    private final UserRepository repository;
    private final UserProfileMapper mapper;

    public UserService(MongoRepository<UserProfile, String> repository, UserRepository repository1, UserProfileMapper mapper) {
        super(repository);
        this.repository = repository1;
        this.mapper = mapper;
    }

    public Boolean createUser(CreateUserRequestDto dto){
        save(mapper.fromCreateDtoToUserProfile(dto));
        return true;
    }

    public Boolean activateStatus(Long authId){
        Optional<UserProfile> user = repository.findByAuthId(authId);
        if (user.isEmpty()){
            throw new RuntimeException("User not found");
        }
        user.get().setStatus(EStatus.ACTIVE);
        update(user.get());
        return true;
    }

    public Boolean deleteUser(Long authId){
        Optional<UserProfile> user = repository.findByAuthId(authId);
        if (user.isEmpty()){
            throw new RuntimeException("User not found");
        }
        user.get().setStatus(EStatus.DELETED);
        update(user.get());
        return true;
    }

//    public Boolean updateUser(Long id,String oldPassword,String newPassword){
//        Optional<UserProfile> user = repository.findByAuthIdAndPassword(id,oldPassword);
//        if (user.isEmpty()){
//            throw new RuntimeException("User not found");
//        }
//        user.get().setPassword(newPassword);
//        update(user.get());
//        return true;
//    }

    public Optional<UserProfile> findByIdUser(String id){
        Optional<UserProfile> user = findById(id);
        if (user.isEmpty()){
            throw new RuntimeException("User not found");
        }
        return user;
    }

    public List<UserProfile> findAllUser(){
        return findAll();
    }
}
