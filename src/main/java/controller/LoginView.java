package controller;

import dto.UserDto;
import entity.User;
import service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView {

    private final UserService userService;

    public JFrame frame;
    private JTextField textField_username;
    private ImageIcon loginIcon;
    private JPasswordField passwordField;
    public JButton btnLogin = new JButton("Login");
    public JButton btnNewUser = new JButton("New user");
    private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    public LoginView() {
        this.userService = new UserService();
        frame = new JFrame("Login");
        initialize();
        frame.setVisible(true);
    }

    private void initialize() {

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(50, 88, 98, 22);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(50, 140, 98, 22);

        textField_username = new JTextField();
        textField_username.setBounds(126, 89, 166, 20);
        textField_username.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBounds(126, 141, 166, 20);

        JLabel lblNewLabel = new JLabel("Introduce credentials");
        lblNewLabel.setFont(new Font("Franklin Gothic Demi", Font.BOLD | Font.ITALIC, 16));
        lblNewLabel.setBounds(103, 25, 227, 22);

        btnLogin.setBounds(100, 196, 75, 23);
        btnNewUser.setBounds(190, 196, 89, 23);

        loginIcon = new ImageIcon("src/main/java/controller/loginscreen.jpg");
        JLabel image = new JLabel(loginIcon);
        image.setSize(380, 300);

        frame.setBounds(500, 500, 380, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        buttonActionListeners();

        addItemsToFrame();
        //frame.getContentPane().add(image);
        frame.getContentPane().add(lblPassword);
        frame.getContentPane().add(lblNewLabel);
        frame.getContentPane().add(lblUsername);
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
    }

    private void addItemsToFrame() {
        frame.getContentPane().add(btnLogin);
        frame.getContentPane().add(textField_username);
        frame.getContentPane().add(passwordField);
        frame.getContentPane().add(btnNewUser);
    }

    private void buttonActionListeners() {
        btnNewUser.addActionListener(e -> new NewUserView(frame));
        btnLogin.addActionListener(e -> verifyDetails());
    }

    private void verifyDetails() {
        String username = textField_username.getText();
        String pass = new String(passwordField.getPassword());
        User user = userService.findUser(username);

        if (user == null) {
            if (username.equals("Admin") && pass.equals("Admin")) {
                new AdminView();
                frame.setVisible(false);
            } else if (username.equals("Employee") && pass.equals("Employee")) {
                new EmployeeView();
                frame.setVisible(false);
            } else
                JOptionPane.showMessageDialog(frame, "User not found!");
        } else if (user.getPassword().equals(pass)) {
            new ClientView();
            frame.setVisible(false);
        } else
            JOptionPane.showMessageDialog(frame, "Wrong password!");
    }


    public JFrame getFrame() {
        return frame;
    }
}
