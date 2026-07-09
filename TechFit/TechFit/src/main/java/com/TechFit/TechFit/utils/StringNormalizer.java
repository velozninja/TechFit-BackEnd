package com.TechFit.TechFit.utils;
import java.text.Normalizer;

public class StringNormalizer {
    public static String normalize(String name) {
        String normalizedName = Normalizer.normalize(name, Normalizer.Form.NFD);
        return normalizedName.toLowerCase()
                .replaceAll("\\p{M}", "")
                .replaceAll("\\d+$", "")
                .replaceAll("\\s+", "")
                .trim();

    }
}
