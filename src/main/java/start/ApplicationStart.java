package start;

import controller.*;
import entity.Booking;
import entity.Box;
import entity.User;
import repository.BookingRepo;
import repository.BoxRepo;
import repository.UserRepo;
import service.BookingService;
import service.BoxService;
import service.UserService;
import utils.ApplicationUtils;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ApplicationStart {

    public static void main(String[] args) throws IOException {

        //Booking test = new Booking("06a0c7f", "1", "30 Jan", "12:30", "12:45", "Exterior");
        ///BookingService t = new BookingService();
        //t.addBooking(test);
        //int s=t.bookingPrice(test);
        //System.out.println(s);

        //BookingRepo test = new BookingRepo();
        //test.getBoxIdForBooking("2020-12-23","12:05","12:15");
        //test.getOccupiedBoxes("2020-12-23");

        //new LoginView();
        //new BookBoxView();
        //new ChooseTypeView();
        new AdminView();
        //new BookBoxView();
        //new ClientView();
        //new EmployeeView();

    }
}
