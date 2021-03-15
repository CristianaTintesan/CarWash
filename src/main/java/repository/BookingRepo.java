package repository;

import entity.Booking;
import entity.Box;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingRepo {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Spalatorie");

    private BoxRepo boxRepo = new BoxRepo();

    private static final String dateQueryForType = "SELECT type FROM Booking WHERE date=?1";

    private static final String dateQuery = "SELECT boxId FROM Booking WHERE date=?1 AND ((startHour <= ?2 AND completionHour > ?2) OR (startHour < ?3 AND completionHour > ?3))";

    private static final String occupiedBoxesStartHour = "SELECT startHour FROM Booking WHERE date=?1";

    private static final String occupiedBoxesCompletionHour = "SELECT completionHour FROM Booking WHERE date=?1";

    private static final String viewBookingsQuery ="SELECT boxId, completionHour, date, startHour, type FROM Booking ";

    public void insertNewBooking(Booking book) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(book);
        em.getTransaction().commit();
        em.close();
    }

    public List findBookings(){
        EntityManager em = entityManagerFactory.createEntityManager();
        try{
            List<?> l=em.createQuery(viewBookingsQuery).getResultList();
            return l;
        }catch(Exception e){
            return null;
        }
    }

    public String[] getOccupiedBoxes(String date) {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<?> l1 = em.createQuery(occupiedBoxesStartHour).setParameter(1, date).getResultList();
        List<?> l2 = em.createQuery(occupiedBoxesCompletionHour).setParameter(1, date).getResultList();
        String s[] = new String[l1.size()];
        int i = 0;
        for (Object o : l1) {
            s[i] = o.toString() + " - ";
            i++;
        }
        i = 0;
        for (Object o : l2) {
            s[i] += o.toString() + " \n";
            System.out.println(s[i]);
            i++;
        }
        return s;

    }

    public String getBoxIdForBookingRepo(String date, String startHour, String completionHour) {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<?> l = em.createQuery(dateQuery).setParameter(1, date).setParameter(2, startHour).setParameter(3, completionHour).getResultList();
        List<?> ids = boxRepo.getBoxIds();
        for (Object o : l) {
            if (ids.contains(o))
                ids.remove(o);
        }
        if (!ids.isEmpty()) {
            for (Object o : ids)
                return o.toString();
        }
        return "-1";
    }

    public Booking findBookingId(String id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Booking book = em.find(Booking.class, id);
        em.close();
        return book;
    }

    public List findBookingDate(String date) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            List<?> l = em.createQuery(dateQueryForType).setParameter(1, date).getResultList();
            return l;
        } catch (Exception e) {
            return null;
        }
    }

}
