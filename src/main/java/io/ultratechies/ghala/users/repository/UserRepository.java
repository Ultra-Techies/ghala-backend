package io.ultratechies.ghala.users.repository;

import io.ultratechies.ghala.users.domain.Users;
import io.ultratechies.ghala.warehouse.domain.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <Users, Long>{
    Optional<Users> findUserById(Long id);
}
