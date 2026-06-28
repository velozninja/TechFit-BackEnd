package com.TechFit.TechFit.exeptions;

public class NotFound extends RuntimeException {
    public NotFound(String message) {
        super(message);
    }

    public static class AlreadyExist extends RuntimeException {
        public AlreadyExist(String message) {
            super(message);
        }
    }
}
