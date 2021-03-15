package controller;

import javax.swing.*;
import java.awt.*;

public class BookingsView {
    JFrame frame;
    JTable bookingTable ;
    JButton btnBack;
    private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    public BookingsView(){
        frame = new JFrame("Show Bookings");
        bookingTable = new JTable();
        initialize();
        addItemsToPane();
        frame.setVisible(true);
    }

    public void initialize(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        bookingTable.setBounds(30, 40, 500, 230);

        frame.setSize(600,400);

        btnBack = new JButton("Back");
        btnBack.setBounds(30, 300, 75, 43);

        btnActionListeners();

    }

    public void addItemsToPane(){

        frame.getContentPane().add(bookingTable);
        frame.getContentPane().add(btnBack);


        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
    }

    private void btnActionListeners(){
        btnBack.addActionListener(e -> {
            new AdminView();
            frame.setVisible(false);
        });


    }

}
