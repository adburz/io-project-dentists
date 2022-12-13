package ior.project;

import ior.project.entities.Doctor;
import ior.project.entities.Patient;
import ior.project.entities.Visit;
import org.hibernate.SessionFactory;

import java.util.Date;
import java.util.List;

public class JPQLQueryManager {
    private SessionFactory sessionFactory;

    public JPQLQueryManager(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Visit> GetAllVisits() {
        return sessionFactory.openSession().createQuery("from Visit", Visit.class).getResultList();
    }
    public List<Visit> GetVisitsBeforeDate(Date date) {
        return sessionFactory.openSession().createQuery("from Visit where date < :date", Visit.class)
                .setParameter("date", date)
                .getResultList();
    }

    public List<Object[]> GetPatientsAndTheirVisits() {
        return sessionFactory.openSession().createQuery(
                "select p, v " +
                        "from Patient p " +
                        "join p.visits v",
                        Object[].class)
                .getResultList();
    }

    public List<Object[]> GetPatientsAndTheirVisitsWithBillsAmountLessThan(int amount) {
        return sessionFactory.openSession().createQuery(
                "select p, v from Patient p " +
                        "join p.visits v " +
                        "join v.bill b " +
                        "where b.amount < " + amount,
                        Object[].class)
                .getResultList();
    }

    public List<Doctor[]> GetDoctorsWithAtLeastTwoDifferentPatients() {
        return sessionFactory.openSession().createQuery(
                "select d " +
                        "from Doctor d " +
                        "join d.visits v " +
                        "join v.patient p " +
                        "group by d " +
                        "having count(distinct p) >= 2",
                        Doctor[].class)
                .getResultList();
    }

    public List<Patient[]> GetPatientsWithAtLeastTwoVisits() {
        return sessionFactory.openSession().createQuery(
                "select p " +
                        "from Patient p " +
                        "join p.visits v " +
                        "group by p " +
                        "having count(v) >= 2",
                        Patient[].class)
                .getResultList();
    }
}
