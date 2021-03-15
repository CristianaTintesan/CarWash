package controller;

import entity.User;
import service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewUserView {

    private final UserService userService;
    private LoginView lgnView;

    public JFrame frame;
    private JTextField textFieldName;
    private JTextField textFieldUsername;
    private JTextField textFieldPass;
    private JButton btnBack;
    private JButton btnNext;
    private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    public NewUserView(JFrame LogInFrame) {
        this.userService = new UserService();
        frame = new JFrame();
        initialize();
        frame.setVisible(true);
        LogInFrame.setVisible(false);
    }

    private void initialize() {

        JLabel LName = new JLabel("Full name");
        LName.setBounds(50, 90, 98, 22);

        JLabel LUsername = new JLabel("Username");
        LUsername.setBounds(50, 140, 98, 22);

        JLabel LPass = new JLabel("Password");
        LPass.setBounds(50, 200, 98, 22);

        textFieldName = new JTextField();
        textFieldName.setBounds(126, 91, 166, 20);

        textFieldUsername = new JTextField();
        textFieldUsername.setBounds(126, 141, 166, 20);

        textFieldPass = new JTextField();
        textFieldPass.setBounds(126, 201, 166, 20);

        btnBack = new JButton("Back");
        btnBack.setBounds(100, 260, 75, 23);

        btnNext = new JButton("Next");
        btnNext.setBounds(190, 260, 75, 23);

        frame.setBounds(500, 500, 400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        buttonActionListeners();

        addItemsToFrame();
        frame.getContentPane().add(LName);
        frame.getContentPane().add(LUsername);
        frame.getContentPane().add(LPass);
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);

    }

    private void addItemsToFrame() {
        frame.getContentPane().add(textFieldName);
        frame.getContentPane().add(textFieldUsername);
        frame.getContentPane().add(textFieldPass);
        frame.getContentPane().add(btnBack);
        frame.getContentPane().add(btnNext);

    }

    private void buttonActionListeners() {
        btnBack.addActionListener(e -> {
            lgnView = new LoginView();
            frame.setVisible(false);
            lgnView.getFrame().setVisible(true);
        });
        btnNext.addActionListener(e -> {
            useTextFields();
            lgnView = new LoginView();
            frame.setVisible(false);
            lgnView.getFrame().setVisible(true);
        });
    }

    private void useTextFields() {
        String name = textFieldName.getText();
        String username = textFieldUsername.getText();
        String password = textFieldPass.getText();
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setUsername(username);
        userService.addUser(user);
    }
}
