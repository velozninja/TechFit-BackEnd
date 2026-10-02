package com.TechFit.TechFit.controllers.v1.User;

import com.TechFit.TechFit.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/v1/User")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/CreateProfile")
    public ResponseEntity<String> setProfileImage(@RequestBody MultipartFile file, String Email) throws IOException {
        String profileImage = userService.setProfileImage(file, Email);
        return ResponseEntity.ok().body(profileImage);
    }
}
