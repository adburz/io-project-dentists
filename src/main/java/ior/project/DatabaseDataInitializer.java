package ior.project;

import ior.project.entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DatabaseDataInitializer {
    public void setDatabaseConfiguration(Configuration configuration) {
        configuration.addAnnotatedClass(Address.class);
        configuration.addAnnotatedClass(Bill.class);
        configuration.addAnnotatedClass(Doctor.class);
        configuration.addAnnotatedClass(MedicalTreatment.class);
        configuration.addAnnotatedClass(Patient.class);
        configuration.addAnnotatedClass(Person.class);
        configuration.addAnnotatedClass(Visit.class);
    }

    public void initialize(SessionFactory sessionFactory) throws ParseException {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        fillDatabaseWithData(session);
        session.getTransaction().commit();
        session.close();
    }

    private void fillDatabaseWithData(Session session) throws ParseException {
        // Create a string list of 10 Polish cities without Polish characters
        String[] cities = {"Warszawa", "Krakow", "Lodz", "Wroclaw", "Poznan", "Gdansk", "Szczecin", "Bydgoszcz", "Lublin", "Katowice"};

        // Create a string list of 10 random streets
        String[] streets = {"Koszykowa", "Zlocista", "Polska", "Anielska", "Wloska", "Krolewska", "Wielka", "Sucha", "Dluga", "Krotka"};

        // Create a string list of 10 postal codes
        String[] postalCodes = {"40-000", "40-001", "40-002", "40-003", "40-004", "40-005", "40-006", "40-007", "40-008", "40-009"};

        // Create a string list of 10 random Polish names
        String[] names = {"Adam", "Jan", "Piotr", "Marek", "Krzysztof", "Tomasz", "Andrzej", "Pawel", "Maciej", "Grzegorz"};

        //Create a string list of 10 random Polish surnames without Polish characters
        String[] surnames = {"Nowak", "Kowalski", "Kowalczyk", "Wojcik", "Kaminski", "Lewandowski", "Zielinski", "Szymanski", "Wozniak", "Dabrowski"};

        //Create a string list of 10 SSN numbers
        String[] SSNs = {"SSN1", "SSN2", "SSN3", "SSN4", "SSN5", "SSN6", "SSN7", "SSN8", "SSN9", "SSN10"};

        // Create a string list of 4 random academic titles
        String[] academicTitles = {"dr", "dr hab", "dr hab n. med", "dr n. med"};

        int numberOfRecords = 10;
        for (int i = 0; i < numberOfRecords; i++) {
            var bill = new Bill();
            bill.setAmount(20 * i);
            bill.setDate(new Date());
            bill.setDiscount(i);
            bill.setAmtToPay(20 + i);
            session.persist(bill);

            var address = new Address();
            address.setCity(cities[i]);
            address.setCountry("Poland");
            address.setStreet(streets[i]);
            address.setPostalCode(postalCodes[i]);
            session.persist(address);

            var doctor = new Doctor();
            doctor.setAcademicTitle(academicTitles[i % 4]);
            doctor.setSpecialization("Stomatolog");
            doctor.setfName(names[i]);
            doctor.setsName(surnames[i]);
            doctor.setSSN(SSNs[i]);
            doctor.setAddress(address);
            session.persist(doctor);

            var patient = new Patient();
            patient.setfName(names[numberOfRecords - i - 1]);
            patient.setsName(surnames[numberOfRecords - i - 1]);
            patient.setSSN(SSNs[numberOfRecords - i - 1]);
            patient.setAddress(address);
            patient.setBirthDate((new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH)).parse("11-January-" + (1980 + i)));
            session.persist(patient);

            var medicalTreatment = new MedicalTreatment();
            medicalTreatment.setType(MedicalTreatmentType.values()[i % 3]);
            medicalTreatment.setDescription(MedicalTreatmentType.values()[i % 3].toString() + " medical treatment");
            medicalTreatment.setToothNo(i);
            session.persist(medicalTreatment);
        }
    }
}
