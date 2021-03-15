package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "booking")
public class Booking {

    @Id
    private String bookingId;

    @Column
    private String boxId;

    @Column
    private String date;

    @Column
    private String startHour;

    @Column
    private String completionHour;

    @Column
    private String type;

    public Booking(){}

    public Booking(String bookingId,String boxId,String date,String startHour,String completionHour,String type){
        this.bookingId=bookingId;
        this.boxId=boxId;
        this.date=date;
        this.startHour=startHour;
        this.completionHour=completionHour;
        this.type=type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBoxId() {
        return boxId;
    }

    public String getCompletionHour() {
        return completionHour;
    }

    public String getStartHour() {
        return startHour;
    }

    public void setBoxId(String boxId) {
        this.boxId = boxId;
    }

    public void setCompletionHour(String completionHour) {
        this.completionHour = completionHour;
    }

    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
