import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PasswordGUI {
    private final String storedPassword = "ItsTimmyTime123";

    private JFrame frame;
    private JLabel resultLabel;
    private int validAttempts = 10;
    private JTextField passwordField; // Use JTextField instead of JPasswordField

    public PasswordGUI() {
        initializeGUI();
    }

    private void initializeGUI() {
        // Create the main frame
        frame = new JFrame("Cyber Interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 250);
        frame.setLayout(new BorderLayout(10, 10));

        // Create a panel for the content
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add a welcome message
        JLabel welcomeLabel = new JLabel("Welcome to the Cyber Interface!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(welcomeLabel);

        // Add a password prompt
        JLabel promptLabel = new JLabel("Please enter your password:", SwingConstants.CENTER);
        panel.add(promptLabel);

        // Create a text field
        passwordField = new JTextField(20); // JTextField for visible text
        passwordField.setHorizontalAlignment(JTextField.CENTER);
        passwordField.setToolTipText("Enter your password here");
        panel.add(passwordField);

        // Add a submit button
        JButton submitButton = new JButton("Submit");
        panel.add(submitButton);

        // Add a label to display the result
        resultLabel = new JLabel("", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));
        frame.add(panel, BorderLayout.CENTER);
        frame.add(resultLabel, BorderLayout.SOUTH);

        // Add an action listener to the submit button
        submitButton.addActionListener(new SubmitButtonListener());

        // Make the frame visible
        frame.setVisible(true);
    }

    private class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Get the entered password
            String inputPassword = passwordField.getText(); // Use getText() for JTextField

            // Compare it to the stored password
            if (inputPassword.equals(storedPassword)) {
                resultLabel.setText("Access Granted!");
                resultLabel.setForeground(Color.GREEN);
            } else {
                validAttempts--;
                resultLabel.setText("Access Denied! Attempts left: " + validAttempts);
                resultLabel.setForeground(Color.RED);

                if (validAttempts == 0) {
                    JOptionPane.showMessageDialog(frame, "You have exceeded the number of attempts. Exiting the program.");
                    System.exit(0);
                }
            }
            // Clear the password field
            passwordField.setText("");
        }
    }

    public static void main(String[] args) {
        // Create the GUI
        SwingUtilities.invokeLater(PasswordGUI::new);
    }
}
