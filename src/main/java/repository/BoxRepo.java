package repository;

import entity.Box;
import entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class BoxRepo {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Spalatorie");

    private final static String maxId = "SELECT MAX(id) FROM Box";

    private final static String selectIds="SELECT id FROM Box";

    public void insertNewBox(Box box) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(box);
        em.getTransaction().commit();
        em.close();
    }

    public List getBoxIds(){
        EntityManager em = entityManagerFactory.createEntityManager();
        List<?> list =em.createQuery(selectIds).getResultList();
        return list;
    }

    public int lastId() {
        String t = "";
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            List<?> test = em.createQuery(maxId).getResultList();
            for (Object a : test)
                t = a.toString();
        }catch(NullPointerException e){
            t="0";
        }
        int toReturn = Integer.parseInt(t);

        return toReturn;
    }

    public void removeBoxRep(String id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Box toRemove = em.find(Box.class, id);
        em.remove(toRemove);
        em.getTransaction().commit();
        em.close();
    }

    public Box findBox(String username) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Box box = em.find(Box.class, username);
        em.close();
        return box;
    }

}
