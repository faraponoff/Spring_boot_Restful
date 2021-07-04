package com.springBootRest.bootstrapRest1.BootstrapRest.dto;

import antlr.collections.List;
import lombok.Data;


@Data
public class UserDTO {
    private long id;
    private String username;
    private int age;
    private String password;
    private List<RoleDTO> roles;
}
