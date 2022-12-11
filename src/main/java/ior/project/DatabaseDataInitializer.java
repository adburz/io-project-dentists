package ior.project;
import ior.project.entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

public class DatabaseDataInitializer {
    public void initialize () {
        // Configure the Hibernate Configuration object
        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(Address.class);
        configuration.addAnnotatedClass(Bill.class);
        configuration.addAnnotatedClass(Doctor.class);
        configuration.addAnnotatedClass(MedicalTreatment.class);
        configuration.addAnnotatedClass(Patient.class);
        configuration.addAnnotatedClass(Person.class);
        configuration.addAnnotatedClass(Visit.class);

        // Create a SessionFactory
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        // Create a new Session and begin a transaction
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Visit visit = new Visit();
        visit.setDescription("Description");
        visit.setDate(LocalDate.now());

        Bill bill = new Bill();
        bill.setAmount(420.69);
        bill.setDate(LocalDate.now());
        bill.setDiscount(0);
        bill.setAmtToPay(250.25);

        Address address = new Address();
        address.setCity("Kato");
        address.setCountry("Poland");
        address.setStreet("Katowicka");
        address.setPostalCode("44-400");

        Doctor doctor = new Doctor();
        doctor.setAcademicTitle("dr");
        doctor.setSpecialization("Stomatolog");
        doctor.setfName("Jan");
        doctor.setsName("Kowalski");
        doctor.setSSN("SSN");
        doctor.setAddress(address);

        Address address2 = new Address();
        address2.setCity("Kato");
        address2.setCountry("Poland");
        address2.setStreet("Katowicka");
        address2.setPostalCode("44-400");

        Patient patient = new Patient();
        patient.setfName("Jan");
        patient.setsName("Kowalski");
        patient.setSSN("SSN");
        patient.setAddress(address2);
        patient.setBirthDate(LocalDate.now());

        MedicalTreatment medicalTreatment = new MedicalTreatment();
        medicalTreatment.setType(MedicalTreatmentType.FILLING);
        medicalTreatment.setDescription("medical treatment");
        medicalTreatment.setToothNo(1);

        visit.setBill(bill);
        visit.setDoctor(doctor);
        visit.setPatient(patient);
        visit.addMedicalTreatment(medicalTreatment);

        // Save the entity instances to the database
        session.save(visit);

        // Commit the transaction
        session.getTransaction().commit();

        // Close the Session
        session.close();
    }
}
