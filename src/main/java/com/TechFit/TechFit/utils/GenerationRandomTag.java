package com.TechFit.TechFit.utils;



import org.springframework.stereotype.Component;

import java.security.SecureRandom;
@Component
public class GenerationRandomTag {

    private static final String CHARS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public String generateTag(){
        SecureRandom random = new SecureRandom();
        StringBuilder tag = new StringBuilder(8);
        for (int i = 0; i < 8; i++) {
            tag.append(CHARS.charAt(random.nextInt(CHARS.length())));
        }
        return tag.toString();

    }
}
