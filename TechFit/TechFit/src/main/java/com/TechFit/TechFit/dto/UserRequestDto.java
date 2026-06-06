package com.TechFit.TechFit.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDto {
    public String name;
    public String email;
    public String password;
    public boolean personal;





}
