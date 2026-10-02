package com.TechFit.TechFit.controllers.v1.Aluno;

import com.TechFit.TechFit.dto.personal.PersonalResponseDto;
import com.TechFit.TechFit.service.AlunoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/aluno")
@AllArgsConstructor
public class AlunoController {
    private final AlunoService alunoService;
    @GetMapping("/personal/{SharableTag}")
    public ResponseEntity<?> getPersonal(@PathVariable String SharableTag){
        PersonalResponseDto personalResponseDto = alunoService.getPersonalData(SharableTag);
        return ResponseEntity.ok().body(personalResponseDto);


    }
}
