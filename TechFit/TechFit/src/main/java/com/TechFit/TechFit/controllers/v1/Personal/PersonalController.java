package com.TechFit.TechFit.controllers.v1.Personal;

import com.TechFit.TechFit.database.repository.IUserRepository;
import com.TechFit.TechFit.dto.UserRequestDto;
import com.TechFit.TechFit.service.PersonalService;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/personal")
@AllArgsConstructor
public class PersonalController {
    private final PersonalService PersonalService;
    @PatchMapping("/aluno")
    public ResponseEntity<?> addAluno(@RequestParam String Email, @RequestBody UserRequestDto userPL) throws BadRequestException {
        PersonalService.AddAluno(Email,userPL);
        return ResponseEntity.noContent().build();
    }

}
