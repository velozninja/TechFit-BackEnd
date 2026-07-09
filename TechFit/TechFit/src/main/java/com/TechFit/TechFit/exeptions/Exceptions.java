package com.TechFit.TechFit.exeptions;

public class Exceptions extends RuntimeException {


    public static class AlreadyExist extends RuntimeException {
        public AlreadyExist(String message) {
            super(message);
        }
    }
    public static class BadRequest extends RuntimeException {
        public BadRequest(String message) {
            super(message);
        }

    }
    public static class NotFound extends RuntimeException {
        public NotFound(String message) { super( message); }
    }
}
