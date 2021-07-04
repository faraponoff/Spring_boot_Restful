package com.springBootRest.bootstrapRest1.BootstrapRest.mappers;

import com.springBootRest.bootstrapRest1.BootstrapRest.dto.RoleDTO;
import com.springBootRest.bootstrapRest1.BootstrapRest.model.Role;
import org.mapstruct.Mapper;

@Mapper
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    RoleDTO toDTO(Role role);
}
