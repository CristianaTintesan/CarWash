package controller;

import javax.swing.*;
import java.awt.*;


public class ClientView {

    public JFrame frame;
    public ImageIcon carWashIcon;
    public JLabel myLabel;
    public JButton btnBook = new JButton ("Book Box");
    public JButton btnPriceList = new JButton("Price List");


    private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    public ClientView(){

        carWashIcon = new ImageIcon("src/main/java/controller/carwash.jpg");
        myLabel = new JLabel(carWashIcon);

        frame = new JFrame("Client Options");

        initialize();
        addItemsToPane();
        frame.setVisible(true);

    }

    public void initialize(){

        btnBook.setBounds(200, 80, 170, 50);
        btnPriceList.setBounds(200, 160, 170, 50);


        myLabel.setSize(600,400);
        frame.setSize(600,400);

        btnActionListeners();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
    }

    public void addItemsToPane(){

        frame.getContentPane().add(btnBook);
        frame.getContentPane().add(btnPriceList);
        frame.getContentPane().add(myLabel);

        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
    }

    private void btnActionListeners(){
        btnBook.addActionListener(e -> {
            new BookBoxView();
            frame.setVisible(false);
        });

        btnPriceList.addActionListener( e -> {
            JOptionPane.showMessageDialog(frame, " Interior -> 15$ \n Exterior -> 10$ \n Complete -> 20$");
        });


    }



}