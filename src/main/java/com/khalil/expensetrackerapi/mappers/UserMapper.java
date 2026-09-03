package com.khalil.expensetrackerapi.mappers;

import com.khalil.expensetrackerapi.dtos.auth.RegisterRequest;
import com.khalil.expensetrackerapi.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    User toEntity(RegisterRequest registerRequest);
}
