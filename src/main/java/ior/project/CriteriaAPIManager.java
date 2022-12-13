package ior.project;

import com.google.gson.Gson;
import ior.project.entities.Doctor;
import ior.project.entities.Patient;
import ior.project.entities.Visit;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.beans.Expression;
import java.util.Date;
import java.util.List;

public class CriteriaAPIManager {
    private SessionFactory sessionFactory;
    public CriteriaAPIManager(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Visit> GetAllVisits() {
        var session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        var builder = session.getCriteriaBuilder();
        CriteriaQuery<Visit> query = builder.createQuery(Visit.class);
        Root<Visit> root = query.from(Visit.class);
        query.select(root);
        var result = session.createQuery(query).getResultList();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public List<Visit> GetVisitsBeforeDate(Date date) {
        var session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        var builder = session.getCriteriaBuilder();
        CriteriaQuery<Visit> query = builder.createQuery(Visit.class);
        Root<Visit> root = query.from(Visit.class);
        query.select(root).where(builder.lessThan(root.get("date"), date));
        var result = session.createQuery(query).getResultList();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public List<Object[]> GetPatientsAndTheirVisits() {
        var session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        var builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root<Visit> root = query.from(Visit.class);
        query.multiselect(root.get("patient"), root);
        var result = session.createQuery(query).getResultList();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public List<Object[]> GetPatientsAndTheirVisitsWithBillsAmountLessThan(int amount) {
        var session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        var builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root<Visit> root = query.from(Visit.class);
        query.multiselect(root.get("patient"), root)
                .where(builder.lessThan(root.get("bill").get("amount"), amount));
        var result = session.createQuery(query).getResultList();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public List<Doctor[]> GetDoctorsWithAtLeastTwoDifferentPatients() {
        var session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        var builder = session.getCriteriaBuilder();
        CriteriaQuery<Doctor[]> query = builder.createQuery(Doctor[].class);
        Root<Visit> root = query.from(Visit.class);
        query.multiselect(root.get("doctor"));
        query.groupBy(root.get("doctor"));
        query.having(builder.gt(builder.countDistinct(root.get("patient")), 1));
        var result = session.createQuery(query).getResultList();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public List<Patient[]> GetPatientsWithAtLeastTwoVisits() {
        var session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        var builder = session.getCriteriaBuilder();
        CriteriaQuery<Patient[]> query = builder.createQuery(Patient[].class);
        Root<Visit> root = query.from(Visit.class);
        query.multiselect(root.get("patient"));
        query.groupBy(root.get("patient"));
        query.having(builder.gt(builder.count(root.get("patient")), 1));
        var result = session.createQuery(query).getResultList();
        session.getTransaction().commit();
        session.close();
        return result;
    }
}
