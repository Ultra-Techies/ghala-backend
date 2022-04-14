package io.ultratechies.ghala.roles.service;

import io.ultratechies.ghala.roles.domain.Roles;
import io.ultratechies.ghala.roles.repository.RolesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RolesService {
    @Autowired
    private final RolesRepository rolesRepository;

    public List<Roles> getAllRoles(){
        return rolesRepository.findAll();
    }

    public Optional<Roles> getRoleByName(String name){
        return rolesRepository.findRolesByName(name);
    }
}
