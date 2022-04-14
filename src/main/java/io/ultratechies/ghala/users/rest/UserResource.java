package io.ultratechies.ghala.users.rest;

import io.ultratechies.ghala.users.domain.UpdateUserDTO;
import io.ultratechies.ghala.users.domain.Users;
import io.ultratechies.ghala.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserResource {
    @Autowired
    private final UserService userService;

    @GetMapping("{userId}")
    public Optional<Users> getUserById(@PathVariable Long userId){
        Optional<Users> user=userService.getUserById(userId);
        return user;
    }

    @PostMapping()
    public ResponseEntity<Void> registerUser(@RequestBody Users user){
        userService.saveUser(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{userId}")
    public ResponseEntity deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

    @PutMapping()
    public ResponseEntity updateUser(@RequestBody UpdateUserDTO updateUserDTO){
        userService.updateUser(updateUserDTO);
        return ResponseEntity.ok().build();
    }

}
