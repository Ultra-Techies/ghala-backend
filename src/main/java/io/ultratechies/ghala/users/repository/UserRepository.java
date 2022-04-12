package io.ultratechies.ghala.users.repository;

import io.ultratechies.ghala.users.domain.Users;
import io.ultratechies.ghala.warehouse.domain.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository <Users, Long>{
    Optional<Users> findUserById(Long id);
}
