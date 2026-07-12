package com.TechFit.TechFit.service;

import com.TechFit.TechFit.config.TokenProvider;
import com.TechFit.TechFit.database.model.RolesEntity;
import com.TechFit.TechFit.database.model.UserEntity;
import com.TechFit.TechFit.database.repository.IRolesRepository;
import com.TechFit.TechFit.database.repository.IUserRepository;
import com.TechFit.TechFit.dto.TokenResponseDto;
import com.TechFit.TechFit.dto.UserRequestDto;
import com.TechFit.TechFit.exeptions.Exceptions;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
            throw new Exceptions.AlreadyExist("User already exist");
        }
        if(userRQ.isPersonal()) {

            RolesEntity role = iRolesRepository.findByName("ROLE_PERSONAL");

            
            userET.setEmail(userRQ.getEmail());
            userET.setPassword(passwordEncoder.encode(userRQ.getPassword()));
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
        System.out.println(userRQ.getEmail());


        try {
            if(userRQ.isPersonal()) {
                System.out.println(userRQ);

              String role_name = "ROLE_PERSONAL";
              RolesEntity role = iRolesRepository.findByName(role_name);
                System.out.println("teste");
              iUserRepository.findByRolesAndEmail(role, userRQ.getEmail())

                      .orElseThrow(() -> new Exceptions.NotFound("User not found"));
                       System.out.println("teste");
                System.out.println(userRQ);


            }
            else{
                String role_name = "ROLE_ALUNO";
                RolesEntity role = iRolesRepository.findByName(role_name);
                System.out.println("teste");

                iUserRepository.findByRolesAndEmail(role, userRQ.getEmail())
                        .orElseThrow(() -> new Exceptions.NotFound("User not found"));
                System.out.println(userRQ.getEmail());
            }

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
