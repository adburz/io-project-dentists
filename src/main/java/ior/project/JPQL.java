package ior.project;

import com.google.gson.Gson;
import ior.project.entities.Visit;
import org.hibernate.SessionFactory;

import java.util.Date;
import java.util.List;

public class JPQL {
    private Gson gson;
    private SessionFactory sessionFactory;

    public JPQL(Gson gson, SessionFactory sessionFactory) {
        this.gson = gson;
        this.sessionFactory = sessionFactory;
    }

    public List<Visit> GetVisitsBeforeDate(Date date) {
        return sessionFactory.openSession().createQuery("from Visit where date < :date", Visit.class)
                .setParameter("date", date)
                .getResultList();
    }

}
