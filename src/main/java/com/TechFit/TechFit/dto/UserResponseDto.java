package com.TechFit.TechFit.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {
    public String name;
    public String email;
    public String password;
    public String SharableTag;
    public boolean personal;


}
