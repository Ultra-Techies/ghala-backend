package io.ultratechies.ghala.roles.rest;

import io.swagger.annotations.ApiOperation;
import io.ultratechies.ghala.roles.domain.Roles;
import io.ultratechies.ghala.roles.service.RolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/roles")
public class RolesResource {
    @Autowired
    private final RolesService rolesService;

    @ApiOperation("Get A list of all roles")
    @GetMapping()
    public List<Roles> getRoles(){
        return rolesService.getAllRoles();
    }
}
