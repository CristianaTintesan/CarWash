package mappers;

import dto.BookingDto;
import entity.Booking;

public class BookingMapper {

    public static BookingDto entityToDto(Booking book){
        BookingDto bookDto = new BookingDto();
        bookDto.setBookingId(book.getBookingId());
        bookDto.setBoxId(book.getBoxId());
        bookDto.setCompletionHour(book.getCompletionHour());
        bookDto.setDate(book.getDate());
        bookDto.setStartHour(book.getStartHour());
        bookDto.setType(book.getType());
        return bookDto;
    }
}
