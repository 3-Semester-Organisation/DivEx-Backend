package godevenner.divexbackend.user.controller;

import godevenner.divexbackend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public ResponseEntity<null> userLogin(){
        return ResponseEntity.ok(null);
    }

    @GetMapping("/register")
    public ResponseEntity<null> userLogin(){
        return ResponseEntity.ok(null);
    }
}
