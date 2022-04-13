package io.ultratechies.ghala.users.rest;

import io.ultratechies.ghala.users.domain.Users;
import io.ultratechies.ghala.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UsersResource {
    private final UserService userService;

    @GetMapping()
    public ResponseEntity<List<Users>> getAllUsers(){
        var users= userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
