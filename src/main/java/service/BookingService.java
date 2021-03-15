package service;

import dto.BookingDto;
import entity.Booking;
import exception.EntityNotExistException;
import mappers.BookingMapper;
import repository.BookingRepo;
import utils.ApplicationUtils;

import java.util.List;

public class BookingService {

    private BookingRepo bookRepo;

    public BookingService() {
        this.bookRepo = new BookingRepo();
    }

    public BookingDto getBooking(String id) {
        Booking book = bookRepo.findBookingId(id);

        if (book == null) {
            throw new EntityNotExistException("No booking having id " + id + "exists.");
        }

        return BookingMapper.entityToDto(book);
    }

    public void addBooking(Booking book) {
        book.setBookingId(ApplicationUtils.generateNewUUID());
        bookRepo.insertNewBooking(book);
    }

    public List getBookings() { return bookRepo.findBookings();}

    public int bookingPrice(String type) {
        int price = -1;
            if (type.equals("Interior"))
                price = 10;
            else if (type.equals("Exterior"))
                price = 10;
            else if (type.equals("Complete"))
                price = 18;
        return price;
    }

    public String getBoxIdForBooking(String date,String startHour,String completionHour){
        return bookRepo.getBoxIdForBookingRepo(date,startHour,completionHour);
    }

    public String[] occupiedBoxes(String date){
        return bookRepo.getOccupiedBoxes(date);
    }

    public List getBookingDates(String date){
        return bookRepo.findBookingDate(date);
    }

    public String getBookingDay(String date){
        String[] yearMonthDay=date.split("-");
        return yearMonthDay[2];
    }

    public String getBookingMonth(String date){
        String[] yearMonthDay=date.split("-");
        return yearMonthDay[1];
    }

    public String getBookingYear(String date){
        String[] yearMonthDay=date.split("-");
        return yearMonthDay[0];
    }
}
