package io.ultratechies.ghala.users.service;

import io.ultratechies.ghala.users.domain.UpdateUserDTO;
import io.ultratechies.ghala.users.domain.Users;
import io.ultratechies.ghala.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    public List<Users> getAllUsers(){
        return userRepository.findAll();

    }
    public Optional<Users> getUserById(Long id){
        return userRepository.findById(id);
    }

    public Long saveUser(Users user){
        return userRepository.save(user).getId();
    }

    public ResponseEntity deleteUser(Long id){
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Transactional
    public void updateUser(UpdateUserDTO updateUserDTO){
        Users user= userRepository.findById(updateUserDTO.getId())
                .orElseThrow(() ->new IllegalStateException("User with ID "+ updateUserDTO.getId()+" does not exist!"));
        if (updateUserDTO.getEmail() != null &&
                updateUserDTO.getEmail().length() > 0 &&
                !Objects.equals(user.getEmail(),updateUserDTO.getEmail())) {
            user.setEmail(updateUserDTO.getEmail());
            }
        if (updateUserDTO.getPhoneNumber() != null &&
                updateUserDTO.getPhoneNumber().length() > 0 &&
                !Objects.equals(user.getPhoneNumber(),updateUserDTO.getPhoneNumber())) {
            user.setPhoneNumber(updateUserDTO.getPhoneNumber());
        }
        if (updateUserDTO.getAssignedWarehouse() != null &&
                !Objects.equals(user.getAssignedWarehouse(),updateUserDTO.getAssignedWarehouse())) {
            user.setAssignedWarehouse(updateUserDTO.getAssignedWarehouse());
        }
        if (updateUserDTO.getPassword() != null &&
                updateUserDTO.getPassword().length() > 0 &&
                !Objects.equals(user.getPassword(),updateUserDTO.getPassword())) {
            user.setPassword(updateUserDTO.getPassword());
        }

    }

}
