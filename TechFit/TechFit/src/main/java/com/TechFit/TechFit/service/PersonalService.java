package com.TechFit.TechFit.service;

import com.TechFit.TechFit.database.model.UserEntity;
import com.TechFit.TechFit.database.repository.IUserRepository;
import com.TechFit.TechFit.dto.UserRequestDto;
import com.TechFit.TechFit.exeptions.Exceptions;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PersonalService {
    private final IUserRepository UserRepository;
    public String AddAluno(String Email, UserRequestDto userPL) throws BadRequestException {
        UserEntity userQuery = UserRepository.findByEmail(Email)
                .orElseThrow(() -> new Exceptions.NotFound("User not found"));
        UserEntity Personal = UserRepository.findByEmail(userPL.getEmail())
                        .orElseThrow(() -> new Exceptions.NotFound("User not found"));
        userQuery.setPersonal(Personal);

        UserRepository.save(userQuery);

        if (userQuery.getRoles().getName().equals("ROLE_PERSONAL")) {
            throw new Exceptions.BadRequest("invalid role");
        }

        System.out.println("salvou aluno");




        return Email;
    }
}
