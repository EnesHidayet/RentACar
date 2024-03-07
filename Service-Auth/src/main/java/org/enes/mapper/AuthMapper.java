package org.enes.mapper;

import org.enes.dto.Request.RegisterRequestDto;
import org.enes.dto.Response.RegisterResponseDto;
import org.enes.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthMapper {

    AuthMapper INSTANCE = Mappers.getMapper(AuthMapper.class);

    Auth fromRegisterRequestToAuth(final RegisterRequestDto dto);
    RegisterRequestDto fromAuthToRegisterRequestDto(final Auth auth);
    RegisterResponseDto fromAuthToRegisterResponseDto(final Auth auth);
}
