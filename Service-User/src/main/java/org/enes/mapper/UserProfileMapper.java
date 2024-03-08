package org.enes.mapper;

import org.enes.dto.request.CreateUserRequestDto;
import org.enes.entity.UserProfile;
import org.enes.rabbitmq.model.RegisterModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserProfileMapper {
    UserProfileMapper INSTANCE = Mappers.getMapper(UserProfileMapper.class);

    CreateUserRequestDto fromUserProfileToCreateDto (final UserProfile userProfile);
    UserProfile fromCreateDtoToUserProfile (final CreateUserRequestDto dto);

    UserProfile fromRegisterModelToUserProfile (final RegisterModel model);


}
