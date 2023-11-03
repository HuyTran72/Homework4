import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SocialNetworkApp extends JFrame {
    private List<User> users = new ArrayList<>();

    public SocialNetworkApp() {
        setTitle("My Social Network App");
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

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Signup");
                JPanel panel = new JPanel(new GridLayout(5, 2));

                JTextField firstNameField = new JTextField();
                JTextField lastNameField = new JTextField();
                JTextField emailField = new JTextField();
                JPasswordField passwordField = new JPasswordField();

                panel.add(new JLabel("First Name"));
                panel.add(firstNameField);
                panel.add(new JLabel("Last Name"));
                panel.add(lastNameField);
                panel.add(new JLabel("Email"));
                panel.add(emailField);
                panel.add(new JLabel("Password"));
                panel.add(passwordField);

                JButton submitButton = new JButton("Submit");
                panel.add(submitButton);

                frame.add(panel);
                frame.setSize(300, 200);
                frame.setVisible(true);

                submitButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String firstName = firstNameField.getText();
                        String lastName = lastNameField.getText();
                        String email = emailField.getText();
                        String password = new String(passwordField.getPassword());
                        try {
                            checkPasswordRequirements(password);
                            String username = generateUsername(firstName, lastName);
                            User newUser = new User(firstName, lastName, email, password, username);
                            users.add(newUser);
                            JOptionPane.showMessageDialog(frame, "Signup successful. Your username is: " + username);
                            frame.dispose();
                        } catch (PasswordException pe) {
                            JOptionPane.showMessageDialog(frame, pe.getMessage());
                        }
                    }
                });
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement login logic here
            }
        });
    }

    private void checkPasswordRequirements(String password) throws PasswordException {
        if (!password.matches(".*[A-Z].*")) {
            throw new UpperCaseCharacterMissing("Password must contain at least one uppercase character.");
        }
        if (!password.matches(".*[a-z].*")) {
            throw new LowerCaseCharacterMissing("Password must contain at least one lowercase character.");
        }
        if (!password.matches(".*\\d.*")) {
            throw new NumberCharacterMissing("Password must contain at least one number.");
        }
        if (!password.matches(".*[!@#$%^&*()-+].*")) {
            throw new SpecialCharacterMissing("Password must contain at least one special character.");
        }
        if (password.length() < 8) {
            throw new Minimum8CharactersRequired("Password must be at least 8 characters long.");
        }
    }

    private String generateUsername(String firstName, String lastName) {
        char firstInitial = Character.toUpperCase(firstName.charAt(0));
        char lastInitial = Character.toUpperCase(lastName.charAt(0));
        Random random = new Random();
        int randomNumber = random.nextInt(9000) + 1000;
        return String.format("%c%c-%d", firstInitial, lastInitial, randomNumber);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SocialNetworkApp::new);
    }
}