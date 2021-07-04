package com.springBoot.bootstrap1.Bootstrap.mappers;

import com.springBoot.bootstrap1.Bootstrap.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toDTO(User user);

}
