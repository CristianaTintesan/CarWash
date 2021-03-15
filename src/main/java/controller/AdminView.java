package controller;

import com.mysql.cj.x.protobuf.MysqlxExpr;
import entity.Box;
import service.BookingService;
import service.BoxService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;


public class AdminView {

    private final BoxService boxService;
    private final BookingService bookingService;

    public JFrame frame;
    public ImageIcon carWashicon;
    public JLabel myLabel;
    public JTable bookingsTable;
    public JButton btnBook = new JButton("View Bookings");
    public JButton btnIncome = new JButton("View Income");
    public JButton btnAddBox = new JButton("Add Box");
    public JButton btnRemoveBox = new JButton("Remove Box");
    public JButton btnSimulation = new JButton("Run Simulation");

    private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    public AdminView() {

        //carWashicon = new ImageIcon(this.getClass().getResource("carwash.jpg"));
        //myLabel = new JLabel(carWashicon);

        this.boxService = new BoxService();
        this.bookingService = new BookingService();

        frame = new JFrame("Admin Options");

        initialize();
        frame.setVisible(true);

    }

    public void initialize() {

        btnBook.setBounds(140, 80, 140, 50);
        btnIncome.setBounds(300, 80, 140, 50);
        btnAddBox.setBounds(140, 160, 140, 50);
        btnRemoveBox.setBounds(300, 160, 140, 50);
        btnSimulation.setBounds(220, 240, 140, 50);

        //myLabel.setBounds(500,500,600,400);
        //frame.setBounds(500, 500, 600, 400);

        //yLabel.setSize(600,400);
        frame.setSize(600, 400);

        addItemsToPane();
        buttonActionListeners();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
    }

    public void addItemsToPane() {

        frame.getContentPane().add(btnBook);
        frame.getContentPane().add(btnIncome);
        frame.getContentPane().add(btnAddBox);
        frame.getContentPane().add(btnRemoveBox);
        //frame.getContentPane().add(btnSimulation);
        //frame.getContentPane().add(myLabel);
    }

    private void buttonActionListeners() {
        btnAddBox.addActionListener(e -> addNewBox());
        btnRemoveBox.addActionListener(e -> removeOneBox());
        btnIncome.addActionListener(e -> showIncome());
        btnBook.addActionListener(e -> showBookings() );
    }

    private void addNewBox() {
        Box box = new Box("Free");
        boxService.addBox(box);
        JOptionPane.showMessageDialog(frame, "Box added successfully!");
    }

    private void removeOneBox() {
        int ok = 1;
        String id = JOptionPane.showInputDialog(frame, "Which box to be removed?");
        try {
            boxService.removeBox(id);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "There is no such box!");
            ok = 0;
            removeOneBox();
        }
        if (ok == 1)
            JOptionPane.showMessageDialog(frame, "Box removed successfully!");
    }

    private void showIncome() {
        int sum = 0;
        String date = JOptionPane.showInputDialog(frame, "Choose period:");
        List<?> list = bookingService.getBookingDates(date);
        for (Object o : list) {
            sum += bookingService.bookingPrice(o.toString());
        }
        JOptionPane.showMessageDialog(frame,"In date of "+date+" the income was "+sum+" dollars!");
    }

    private void showBookings(){
        BookingsView bookingsView = new BookingsView();

        List<?> list = bookingService.getBookings();
        DefaultTableModel bookingTable = new DefaultTableModel();

        Object titleColumn1 = "Box Id";
        Object titleColumn2 = "Completion  Hour";
        Object titleColumn3 = "Date";
        Object titleColumn4 = "Start  Hour";
        Object titleColumn5 = "Type";
        bookingTable.addColumn("BoxId");
        bookingTable.insertRow(0,new Object[] { "Box id"});
        bookingTable.addColumn("CompletionHour");
        bookingTable.addColumn("Date");
        bookingTable.addColumn("StartHour");
        bookingTable.addColumn("Type");

        for (Object o : list) {
            bookingTable.addRow((Object[]) o);
        }

        bookingTable.setValueAt(titleColumn1, 0, 0);
        bookingTable.setValueAt(titleColumn2, 0, 1);
        bookingTable.setValueAt(titleColumn3, 0, 2);
        bookingTable.setValueAt(titleColumn4, 0, 3);
        bookingTable.setValueAt(titleColumn5, 0, 4);

        bookingsView.bookingTable.setModel(bookingTable);
        bookingTable.fireTableDataChanged();

        frame.setVisible(false);


    }

}