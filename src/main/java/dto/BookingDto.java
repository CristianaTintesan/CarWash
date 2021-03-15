package dto;

public class BookingDto {

    private String bookingId;

    private String boxId;

    private String date;

    private String startHour;

    private String completionHour;

    private String type;

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }

    public void setCompletionHour(String completionHour) {
        this.completionHour = completionHour;
    }

    public void setBoxId(String boxId) {
        this.boxId = boxId;
    }

    public String getStartHour() {
        return startHour;
    }

    public String getCompletionHour() {
        return completionHour;
    }

    public String getBoxId() {
        return boxId;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

}
