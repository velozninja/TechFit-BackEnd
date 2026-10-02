package com.TechFit.TechFit.controllers.v1.User.Auth;

import com.TechFit.TechFit.dto.User.TokenResponseDto;
import com.TechFit.TechFit.dto.User.UserRequestDto;
import com.TechFit.TechFit.dto.User.UserResponseDto;
import com.TechFit.TechFit.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@AllArgsConstructor
public class AuthController {
    private final UserService userService;
    @PostMapping("/register")
    public UserResponseDto  Register(@Valid @RequestBody UserRequestDto userRequestDto){
        return userService.register(userRequestDto);
    }
    @PostMapping("/login")
    public TokenResponseDto Login(@Valid @RequestBody UserRequestDto userRequestDto) throws BadRequestException {
        return userService.login(userRequestDto);
    }
}
