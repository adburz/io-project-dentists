package ior.project;

import com.google.gson.Gson;
import ior.project.entities.Visit;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Date;
import java.util.List;

public class CriteriaAPI {
    private Gson gson;
    private SessionFactory sessionFactory;
    public CriteriaAPI(Gson gson, SessionFactory sessionFactory) {
        this.gson = gson;
        this.sessionFactory = sessionFactory;
    }

    public List<Visit> GetVisitsBeforeDate(Date date) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaQuery<Visit> criteriaQuery = criteriaBuilder.createQuery(Visit.class);

        Root<Visit> root = criteriaQuery.from(Visit.class);
        criteriaQuery.select(root).where(criteriaBuilder.lessThan(root.get("date"), date));

        List<Visit> visits = session.createQuery(criteriaQuery).getResultList();
        session.getTransaction().commit();
        session.close();
        return visits;
    }

    public List<Visit> GetVisitsWithBillsAndDoctors() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaQuery<Visit> criteriaQuery = criteriaBuilder.createQuery(Visit.class);

        Root<Visit> root = criteriaQuery.from(Visit.class);
        criteriaQuery.select(root).where(criteriaBuilder.isNotNull(root.get("bill")), criteriaBuilder.isNotNull(root.get("doctor")));

        List<Visit> visits = session.createQuery(criteriaQuery).getResultList();
        session.getTransaction().commit();
        session.close();
        return visits;
    }

    // get average visit price greater than 100 for each day for visits that have only MedicalTreatmentType Extraction
    public List<Object[]> GetAverageVisitPriceForExtractionMedicalTreatment() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);

        Root<Visit> root = criteriaQuery.from(Visit.class);
        criteriaQuery.multiselect(root.get("date"), criteriaBuilder.avg(root.get("bill").get("amount")))
                .where(criteriaBuilder.greaterThan(root.get("bill").get("amount"), 100),
                        criteriaBuilder.equal(root.get("medicalTreatments").get("name"), "Extraction"))
                .groupBy(root.get("date"));

        List<Object[]> visits = session.createQuery(criteriaQuery).getResultList();
        session.getTransaction().commit();
        session.close();
        return visits;
    }

}
