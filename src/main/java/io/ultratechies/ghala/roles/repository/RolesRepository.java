package io.ultratechies.ghala.roles.repository;

import io.ultratechies.ghala.roles.domain.Roles;
import io.ultratechies.ghala.users.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Roles,Long> {
    Optional<Roles> findRolesByName(String name);
}
