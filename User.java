class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String username;

    public User(String firstName, String lastName, String email, String password, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}

class PasswordException extends Exception {
    public PasswordException(String message) {
        super(message);
    }
}

class UpperCaseCharacterMissing extends PasswordException {
    public UpperCaseCharacterMissing(String message) {
        super(message);
    }
}

class LowerCaseCharacterMissing extends PasswordException {
    public LowerCaseCharacterMissing(String message) {
        super(message);
    }
}

class SpecialCharacterMissing extends PasswordException {
    public SpecialCharacterMissing(String message) {
        super(message);
    }
}

class NumberCharacterMissing extends PasswordException {
    public NumberCharacterMissing(String message) {
        super(message);
    }
}

class Minimum8CharactersRequired extends PasswordException {
    public Minimum8CharactersRequired(String message) {
        super(message);
    }
}

