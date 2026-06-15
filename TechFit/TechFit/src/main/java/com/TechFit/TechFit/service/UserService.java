package com.TechFit.TechFit.service;

import com.TechFit.TechFit.config.TokenProvider;
import com.TechFit.TechFit.database.model.RolesEntity;
import com.TechFit.TechFit.database.model.UserEntity;
import com.TechFit.TechFit.database.repository.IRolesRepository;
import com.TechFit.TechFit.database.repository.IUserRepository;
import com.TechFit.TechFit.dto.TokenResponseDto;
import com.TechFit.TechFit.dto.UserRequestDto;
import com.TechFit.TechFit.exeptions.AlreadyExist;
import com.TechFit.TechFit.exeptions.NotFound;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final IRolesRepository iRolesRepository;
    private final IUserRepository iUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    @Value(value = "${jwt.expiration}")
    private long TokenExpirationTime;
    public UserRequestDto register(UserRequestDto userRQ) {
        UserEntity userET = new UserEntity();
        Optional<UserEntity> userV = iUserRepository.findByEmail(userRQ.getEmail());

        if (userV.isPresent()) {
            throw new AlreadyExist("User already exist");
        }
        if(userRQ.isPersonal()) {

            RolesEntity role = iRolesRepository.findByName("ROLE_PERSONAL");
        if(role.getName() != "ROLE_PERSONAL" && role.getName() != "ROLE_ALUNO" && role == null) {
                throw new NotFound("Role is not found");
            }
            
            userET.setEmail(userRQ.getEmail());
            userET.setPassword(userRQ.getPassword());
            userET.setUsername(userRQ.getName());
            userET.setRoles(role);

            iUserRepository.save(userET);
            return userRQ;






        }
        else {
            RolesEntity role = iRolesRepository.findByName("ROLE_ALUNO");

            userET.setEmail(userRQ.getEmail());
            userET.setPassword(passwordEncoder.encode(userRQ.getPassword()));
            userET.setUsername(userRQ.getName());
            userET.setRoles(role);

            iUserRepository.save(userET);


            return userRQ;
        }



    }
    public TokenResponseDto login(UserRequestDto userRQ) throws BadRequestException {
        try {

             Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRQ.getEmail(), userRQ.getPassword()));
             String token = tokenProvider.gerarToken(authentication);
             return new TokenResponseDto(token, TokenExpirationTime);




        } catch (BadCredentialsException e) {
            throw new  BadRequestException("Invalid credentials");
        }
        catch (Exception e) {
            throw e;
        }


    }

}
