package io.ultratechies.ghala.users.rest;

import io.swagger.annotations.ApiOperation;
import io.ultratechies.ghala.users.domain.Users;
import io.ultratechies.ghala.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
