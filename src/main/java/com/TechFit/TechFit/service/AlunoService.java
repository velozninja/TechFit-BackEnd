package com.TechFit.TechFit.service;

import com.TechFit.TechFit.database.model.UserEntity;
import com.TechFit.TechFit.database.repository.IUserRepository;
import com.TechFit.TechFit.dto.personal.PersonalResponseDto;
import com.TechFit.TechFit.exeptions.Exceptions;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AlunoService {
    private final IUserRepository userRepository;

    @Cacheable(value = "personal", key = "#SharableTag")
    public PersonalResponseDto getPersonalData(String SharableTag) {
        UserEntity Personal = (UserEntity) userRepository.findByStudents_sharableTag(SharableTag);
        if (Personal == null) {
            throw new Exceptions.NotFound("Personal não encontrado");
        }
        PersonalResponseDto PersonalResponseDto = new PersonalResponseDto();
        PersonalResponseDto.setName(Personal.getUsername());
        PersonalResponseDto.setSharableTag(SharableTag);
        return PersonalResponseDto;
    }

}
