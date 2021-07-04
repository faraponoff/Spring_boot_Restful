package com.springBootRest.bootstrapRest1.BootstrapRest.mappers;

import com.springBootRest.bootstrapRest1.BootstrapRest.dto.UserDTO;
import com.springBootRest.bootstrapRest1.BootstrapRest.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toDTO(User user);

}
