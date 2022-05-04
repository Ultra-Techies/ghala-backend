package io.ultratechies.ghala.users.service;

import io.ultratechies.ghala.enums.RolesEnum;
import io.ultratechies.ghala.users.domain.UpdateUserDTO;
import io.ultratechies.ghala.users.domain.Users;
import io.ultratechies.ghala.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    public List<Users> getAllUsers(){
        return userRepository.findAll();

    }
    public Optional<Users> getUserById(Long id){

       Users user=userRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("User with Id "+id +" does not exist!"));
       return Optional.ofNullable(user);
    }

    public Map<String,String> saveUser(Users user){
        Optional<Users> userByEmail=userRepository.findUsersByEmail(user.getEmail());
        if (userByEmail.isPresent()){
            throw  new IllegalStateException("User with email exists!");
        }
        Optional<Users> userByPhone=userRepository.findUsersByPhoneNumber(user.getPhoneNumber());
        if (userByPhone.isPresent()){
            throw new IllegalStateException("User with Phone Number exists!");
        }
        Boolean firstUser=userRepository.findAll().isEmpty();
        if (firstUser){
            user.setRole(RolesEnum.ADMIN);
        }else {
        user.setRole(RolesEnum.BASIC);
            }
        Users newUser=userRepository.save(user);
        Map map = new HashMap<>();
        map.put("id",newUser.getId());
        return map;
    }

    public ResponseEntity deleteUser(Long id){
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public Map userExists(Users user){
        Optional<Users> userByPhone=userRepository.findUsersByPhoneNumber(user.getPhoneNumber());
        Boolean exists=userByPhone.isPresent();
        Map map = new HashMap<>();
        map.put("exists",exists);
        return map;
    }

    public ResponseEntity verifyUser(Users user){
        Optional<Users> userByPhone=userRepository.findUsersByPhoneNumber(user.getPhoneNumber());
        Boolean exists=userByPhone.isPresent();
        if(!exists){
            throw new IllegalArgumentException("User with Phone Number does not exist!");
        }
        Users userByNo=userRepository.findUsersByPhoneNumber(user.getPhoneNumber()).get();
        Boolean matches=Objects.equals(user.getPassword(),userByNo.getPassword());
        Map map = new HashMap<>();
        map.put("verified",matches);
        if (matches){
            return ResponseEntity.ok(userByNo);
        }else {
           return ResponseEntity.ok(map);
        }
    }

    @Transactional
    public void updateUser(UpdateUserDTO updateUserDTO){
        Users user= userRepository.findById(updateUserDTO.getId())
                .orElseThrow(() ->new IllegalStateException("User with ID "+ updateUserDTO.getId()+" does not exist!"));
        if (updateUserDTO.getEmail() != null &&
                updateUserDTO.getEmail().length() > 0 &&
                !Objects.equals(user.getEmail(),updateUserDTO.getEmail())) {
            Optional<Users> userByEmail=userRepository.findUsersByEmail(updateUserDTO.getEmail());
            if (userByEmail.isPresent()){
                throw  new IllegalStateException("User with email exists!");
            }
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
        if (updateUserDTO.getFirstName() != null &&
                updateUserDTO.getFirstName().length() > 0 &&
                !Objects.equals(user.getFirstName(),updateUserDTO.getFirstName())) {
            user.setFirstName(updateUserDTO.getFirstName());
        }
        if (updateUserDTO.getLastName() != null &&
                updateUserDTO.getLastName().length() > 0 &&
                !Objects.equals(user.getLastName(),updateUserDTO.getLastName())) {
            user.setLastName(updateUserDTO.getLastName());
        }
        if (updateUserDTO.getProfilePhoto() != null &&
                !Objects.equals(user.getProfilePhoto(),updateUserDTO.getProfilePhoto())) {
            user.setProfilePhoto(updateUserDTO.getProfilePhoto());
        }

    }

}
