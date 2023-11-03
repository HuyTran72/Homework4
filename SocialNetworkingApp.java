import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

// Custom exceptions
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

public class SocialNetworkingApp extends JFrame {
    private JTextField firstNameField, lastNameField, emailField, passwordField;
    private ArrayList<UserData> users = new ArrayList<>();

    public SocialNetworkingApp() {
        setTitle("Social networking service");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton signupButton = new JButton("Signup");
        JButton loginButton = new JButton("Login");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        panel.add(signupButton);
        panel.add(loginButton);

        add(panel);
        setVisible(true);

        // Action listener for the signup button
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSignupDialog();
            }
        });

        // Action listener for the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showLoginDialog();
            }
        });
    }

    private void showSignupDialog() {
        JDialog signupDialog = new JDialog(this, "Signup");
        signupDialog.setSize(300, 200);
        signupDialog.setLayout(new GridLayout(6, 2));

        firstNameField = new JTextField(20);
        lastNameField = new JTextField(20);
        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);

        signupDialog.add(new JLabel("First Name:"));
        signupDialog.add(firstNameField);
        signupDialog.add(new JLabel("Last Name:"));
        signupDialog.add(lastNameField);
        signupDialog.add(new JLabel("Email:"));
        signupDialog.add(emailField);
        signupDialog.add(new JLabel("Password:"));
        signupDialog.add(passwordField);

        JButton submitButton = new JButton("Submit");
        signupDialog.add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String username = generateUsername(firstNameField.getText(), lastNameField.getText());
                    String password = passwordField.getText();

                    validatePassword(password);

                    UserData newUser = new UserData(username, firstNameField.getText(), lastNameField.getText(),
                            emailField.getText(), password);
                    users.add(newUser);

                    JOptionPane.showMessageDialog(signupDialog, "Signup successful. Your username is: " + username);
                    signupDialog.dispose();
                } catch (PasswordException pe) {
                    JOptionPane.showMessageDialog(signupDialog, pe.getMessage());
                }
            }
        });

        signupDialog.setVisible(true);
    }

    private void showLoginDialog() {
        JDialog loginDialog = new JDialog(this, "Login");
        loginDialog.setSize(300, 100);
        loginDialog.setLayout(new GridLayout(3, 2));

        JTextField usernameField = new JTextField(20);
        JTextField passwordField = new JPasswordField(20);

        loginDialog.add(new JLabel("Username:"));
        loginDialog.add(usernameField);
        loginDialog.add(new JLabel("Password:"));
        loginDialog.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginDialog.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = passwordField.getText();

                UserData user = findUser(username);
                if (user != null && user.checkPassword(password)) {
                    JOptionPane.showMessageDialog(loginDialog, "Login successful. Welcome, " + user.getUsername());
                } else {
                    JOptionPane.showMessageDialog(loginDialog, "Login failed. Invalid username or password.");
                }
            }
        });

        loginDialog.setVisible(true);
    }

    private void validatePassword(String password) throws PasswordException {
        if (!password.matches(".*[A-Z].*")) {
            throw new UpperCaseCharacterMissing();
        }
        if (!password.matches(".*[a-z].*")) {
            throw new LowerCaseCharacterMissing();
        }
        if (!password.matches(".*[!@#$%^&*()-+].*")) {
            throw new SpecialCharacterMissing();
        }
        if (!password.matches(".*\\d.*")) {
            throw new NumberCharacterMissing();
        }
        if (password.length() < 8) {
            throw new Minimum8CharactersRequired();
        }
    }

    private String generateUsername(String firstName, String lastName) {
        char firstInitial = firstName.charAt(0);
        char lastInitial = lastName.charAt(0);
        Random random = new Random();
        int randomNumber = random.nextInt(9000) + 1000; // Generates a 4-digit random number
        return String.format("%c%c-%04d", firstInitial, lastInitial, randomNumber);
    }

    private UserData findUser(String username) {
        for (UserData user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SocialNetworkingApp());
    }
}
