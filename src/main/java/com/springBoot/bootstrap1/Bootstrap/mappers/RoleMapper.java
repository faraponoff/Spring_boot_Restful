package com.springBoot.bootstrap1.Bootstrap.mappers;

import com.springBoot.bootstrap1.Bootstrap.model.Role;
import org.mapstruct.Mapper;

@Mapper
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    RoleDTO toDTO(Role role);
}
