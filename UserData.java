class UserData {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public UserData(String username, String firstName, String lastName, String email, String password) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean checkPassword(String inputPassword) {
        return password.equals(inputPassword);
    }
}

class PasswordException extends Exception {
    public PasswordException(String message) {
        super(message);
    }
}

class UpperCaseCharacterMissing extends PasswordException {
    public UpperCaseCharacterMissing() {
        super("Password must contain at least one uppercase character.");
    }
}

class LowerCaseCharacterMissing extends PasswordException {
    public LowerCaseCharacterMissing() {
        super("Password must contain at least one lowercase character.");
    }
}

class SpecialCharacterMissing extends PasswordException {
    public SpecialCharacterMissing() {
        super("Password must contain at least one special character.");
    }
}

class NumberCharacterMissing extends PasswordException {
    public NumberCharacterMissing() {
        super("Password must contain at least one number.");
    }
}

class Minimum8CharactersRequired extends PasswordException {
    public Minimum8CharactersRequired() {
        super("Password must be at least 8 characters long.");
    }
}