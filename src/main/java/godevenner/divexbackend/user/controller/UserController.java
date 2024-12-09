package godevenner.divexbackend.user.controller;

import godevenner.divexbackend.config.JwtService;
import godevenner.divexbackend.user.dto.EditUserRequest;
import godevenner.divexbackend.user.dto.PasswordChangeRequest;
import godevenner.divexbackend.user.model.User;
import godevenner.divexbackend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;

    @GetMapping("/user")
    public ResponseEntity<User> getUser(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        String userId = jwtService.extractUserId(token);
        Long id = Long.parseLong(userId);
        return ResponseEntity.ok(userService.findById(id).orElseThrow());
    }

    /**
     * @param authorizationHeader the authorization token from the request header
     * @param request the new password to be set for the user
     * @return the updated user entity
     */
    @PutMapping("/user/password")
    public ResponseEntity<Void> changePassword(@RequestHeader("Authorization") String authorizationHeader, @RequestBody PasswordChangeRequest request) {
        String token = authorizationHeader.replace("Bearer ", "");
        String userId = jwtService.extractUserId(token);
        Long id = Long.parseLong(userId);
        userService.changePassword(id, request.password());
        return ResponseEntity.ok().build();
    }

    /**
     * @param authorizationHeader the authorization token from the request header
     * @param editUserRequest the new user information to be set for the user, firstname, lastname, email, phone
     * @return the updated user entity
     */
    @PutMapping("/user")
    public ResponseEntity<Void> editUser(@RequestHeader("Authorization") String authorizationHeader, @RequestBody EditUserRequest editUserRequest ) {
        String token = authorizationHeader.replace("Bearer ", "");
        String userId = jwtService.extractUserId(token);
        Long id = Long.parseLong(userId);
        userService.editUser(id, editUserRequest);
        return ResponseEntity.ok().build();
    }





}


