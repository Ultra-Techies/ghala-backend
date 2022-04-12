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

    public ResponseEntity saveUser(Users user){
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity deleteUser(Long id){
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Transactional
    public void updateUser(UpdateUserDTO updateUserDTO){
        Users user= userRepository.findById(updateUserDTO.getId())
                .orElseThrow(() ->new IllegalStateException("Warehouse with ID "+ updateUserDTO.getId()+" does not exist!"));
        if (updateUserDTO.getEmail() != null &&
                updateUserDTO.getEmail().length() > 0 &&
                !Objects.equals(user.getEmail(),updateUserDTO.getEmail())) {
            user.setEmail(updateUserDTO.getEmail());
            }

    }

}
