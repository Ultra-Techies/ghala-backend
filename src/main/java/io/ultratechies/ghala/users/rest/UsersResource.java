package io.ultratechies.ghala.users.rest;

import io.swagger.annotations.ApiOperation;
import io.ultratechies.ghala.users.domain.Users;
import io.ultratechies.ghala.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UsersResource {
    @Autowired
    private final UserService userService;

    @ApiOperation("Get All Users")
    @GetMapping()
    public ResponseEntity<List<Users>> getAllUsers(){
        var users= userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @ApiOperation("Check whether user exists")
    @PostMapping
    public Map userExists(@RequestBody Users user){
        return userService.userExists(user);
    }

    @ApiOperation("Verify user")
    @PutMapping
    public Map verifyUser(@RequestBody Users user){
        return userService.verifyUser(user);
    }
}
