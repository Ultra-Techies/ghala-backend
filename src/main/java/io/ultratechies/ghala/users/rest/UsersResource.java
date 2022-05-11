package io.ultratechies.ghala.users.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.ultratechies.ghala.users.domain.UpdateUserDTO;
import io.ultratechies.ghala.users.domain.Users;
import io.ultratechies.ghala.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UsersResource {
    @Autowired
    private final UserService userService;

    @GetMapping("/{userId}")
    public Optional<Users> getUserById(@ApiParam(name="id", required = true,example = "1")
                                       @PathVariable Long userId){
        Optional<Users> user=userService.getUserById(userId);
        return user;
    }

    @PostMapping()
    public Map registerUser(@RequestBody Users user){
        return userService.saveUser(user);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity deleteUser(@ApiParam(name="id", required = true,example = "1")
                                     @PathVariable Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

    @PutMapping()
    public ResponseEntity updateUser(@RequestBody UpdateUserDTO updateUserDTO){
        userService.updateUser(updateUserDTO);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("Get All Users")
    @GetMapping("/all")
    public ResponseEntity<List<Users>> getAllUsers(){
        var users= userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @ApiOperation("Check whether user exists")
    @PostMapping("/exists")
    public Map userExists(@RequestBody Users user){
        return userService.userExists(user);
    }

    @ApiOperation("Fetch user By phone")
    @PostMapping("/fetch")
    public ResponseEntity verifyUser(@RequestBody Users user){
        return userService.fetchUserByPhoneNumber(user);
    }
}
