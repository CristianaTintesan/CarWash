package controller;

import entity.Booking;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import service.BookingService;
import utils.DateLabelFormatter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class EmployeeView {

    private JFrame frame;

    private JButton btnConfirm;
    private JButton btnBack;
    private JComboBox<String> hourComboBox;
    private JComboBox<String> minuteComboBox;
    private JComboBox<String> washComboBox;
    private JDatePickerImpl datePicker;
    private BookingService bookingService;
    private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    public EmployeeView() {
        frame = new JFrame("Book");
        initialize();
        frame.setVisible(true);
        this.bookingService = new BookingService();
    }

    private void initialize() {


        btnConfirm = new JButton("Confirm");
        btnBack = new JButton("Back");
        String[] hourOptions = {
                "12", "13", "14", "15", "16", "17", "18", "19", "20"
        };
        String[] minutesOptions = {
                "00", "10", "20", "30", "40", "50"
        };
        String[] washOptions = {
                "Interior", "Exterior", "Complete"
        };

        hourComboBox = new JComboBox<>(hourOptions);
        minuteComboBox = new JComboBox<>(minutesOptions);
        washComboBox = new JComboBox<>(washOptions);
        JLabel timeLabel = new JLabel("Select time");
        JLabel chooseLabel = new JLabel("Choose date");
        //JLabel dateLabel = new JLabel("Select date");
        JLabel typeLabel = new JLabel("Select washing");


        //Calendar calendar = Calendar.getInstance();
        //dateModel.setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        UtilDateModel dateModel = new UtilDateModel();
        Properties properties = new Properties();
        properties.put("text.today", "Today");
        properties.put("text.month", "Month");
        properties.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(dateModel, properties);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());


        //hourTextField.setBounds(140,150,166,20);

        //dateLabel.setBounds(40, 80, 150, 22);
        //datePicker.setBounds(140, 80, 150, 22);
        timeLabel.setBounds(40, 80, 150, 22);
        hourComboBox.setBounds(140, 80, 50, 20);
        minuteComboBox.setBounds(200, 80, 50, 20);
        typeLabel.setBounds(40, 160, 120, 20);
        washComboBox.setBounds(165, 160, 80, 20);



        btnConfirm.setBounds(285, 230, 85, 43);
        btnBack.setBounds(10, 230, 75, 43);

        chooseLabel.setFont(new Font("Franklin Gothic Demi", Font.BOLD | Font.ITALIC, 16));
        chooseLabel.setBounds(130, 25, 227, 22);

        frame.setBounds(500, 500, 400, 360);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);

        btnActionListeners();

        addItemsToFrame();
        frame.getContentPane().add(timeLabel);
        frame.getContentPane().add(chooseLabel);
        //frame.getContentPane().add(datePicker);
        //frame.getContentPane().add(dateLabel);
        frame.getContentPane().add(typeLabel);

    }

    private void addItemsToFrame() {
        frame.getContentPane().add(btnConfirm);
        frame.getContentPane().add(btnBack);
        frame.getContentPane().add(hourComboBox);
        frame.getContentPane().add(minuteComboBox);
        frame.getContentPane().add(washComboBox);

    }

    private void btnActionListeners() {
        btnConfirm.addActionListener(e -> {
            Object hourString = hourComboBox.getSelectedItem();
            if (hourString == null) {
                JOptionPane.showMessageDialog(frame, "Please select an hour!");
                return;
            }
            Object minuteString = minuteComboBox.getSelectedItem();
            if (minuteString == null) {
                JOptionPane.showMessageDialog(frame, "Please select a minute!");
                return;
            }

            Date selectedDate = null;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            try {
                selectedDate = simpleDateFormat.parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + String.format(" %s:%s", hourString, minuteString));
            } catch (ParseException parseException) {
                return;
            }
            Date currentDate = new Date();
            if (currentDate.after(selectedDate)) {
                JOptionPane.showMessageDialog(frame, "Selected date is older than current date!");
                return;
            }


            String startTime = hourString + ":" + minuteString;
            Object washTypeObject = washComboBox.getSelectedItem();
            if (washTypeObject == null) {
                JOptionPane.showMessageDialog(frame, "Please select a wash type!");
                return;
            }
            int minute = Integer.parseInt(minuteString.toString());
            int duration = getDurationForWashType(washTypeObject.toString());
            int newMinutes = minute + duration;
            int hour = Integer.parseInt(hourString.toString());
            if (newMinutes / 60 > 0) {
                hour = hour + (newMinutes / 60);
            }
            minute = newMinutes % 60;
            String completionTime = String.format("%02d:%02d", hour, minute);

            Booking booking = new Booking();
            booking.setDate(new SimpleDateFormat("yyyy-MM-dd").format(selectedDate));
            booking.setStartHour(startTime);
            booking.setCompletionHour(completionTime);
            booking.setType(washTypeObject.toString());
            booking.setBoxId("1"); // to be implemented...
            this.bookingService.addBooking(booking);
            JOptionPane.showMessageDialog(frame, "Booking created successfully!");


        });

        btnBack.addActionListener(e -> {
            new LoginView();
            frame.setVisible(false);
        });
    }

    private int getDurationForWashType(String washType) {
        if ("Exterior".equals(washType)) {
            return 10;
        }
        if ("Interior".equals(washType)) {
            return 10;
        }
        if ("Complete".equals(washType)) {
            return 20;
        }
        return 0;
    }
}