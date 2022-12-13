package ior.project;

import com.google.gson.GsonBuilder;
import com.google.gson.graph.GraphAdapterBuilder;
import ior.project.entities.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import com.google.gson.Gson;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        try {
            var configuration = new Configuration().configure();
            var databaseDataInitializer = new DatabaseDataInitializer();

            // Configure the Hibernate Configuration object
            databaseDataInitializer.setDatabaseConfiguration(configuration);
            var serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

            // Create a SessionFactory
            var sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            // Fill database with seed data
            databaseDataInitializer.initialize(sessionFactory);

            // Initialize Gson
            GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();
            new GraphAdapterBuilder()
                    .addType(Bill.class)
                    .addType(Doctor.class)
                    .addType(MedicalTreatment.class)
                    .addType(Patient.class)
                    .addType(Person.class)
                    .addType(Visit.class)
                    .registerOn(gsonBuilder);
            Gson gson = gsonBuilder.create();

            //JPQL Queries
            var jpqlQueryManager = new JPQLQueryManager(sessionFactory);
            System.out.println("JPQL Queries:");
            System.out.println("1.");
            System.out.println("Get All Visits:");
            var visits = jpqlQueryManager.GetAllVisits();
            PrintCollectionWithGson(visits, gson);
            System.out.println("========================================");

            System.out.println("Get All Visits Before Date (05-05-2022):");
            var visitsBeforeDate = jpqlQueryManager.GetVisitsBeforeDate(new Date(2022, 5, 5));
            PrintCollectionWithGson(visitsBeforeDate, gson);
            System.out.println("========================================");

            System.out.println("2.");
            System.out.println("Get Patients and their Visits:");
            var patientsAndTheirVisits = jpqlQueryManager.GetPatientsAndTheirVisits();
            PrintCollectionWithGson(patientsAndTheirVisits, gson);
            System.out.println("========================================");

            System.out.println("Get Patients and their Visits with Bills:");
            var patientsAndTheirVisitsWithBills = jpqlQueryManager.GetPatientsAndTheirVisitsWithBillsAmountLessThan(50);
            PrintCollectionWithGson(patientsAndTheirVisitsWithBills, gson);
            System.out.println("========================================");

            System.out.println("3.");
            System.out.println("Get Doctors with at least two Patients:");
            var doctorsWithAtLeastTwoPatientsx = jpqlQueryManager.GetDoctorsWithAtLeastTwoDifferentPatients();
            PrintCollectionWithGson(doctorsWithAtLeastTwoPatientsx, gson);
            System.out.println("========================================");

            System.out.println("Get Patients with at least two Visits:");
            var patientsWithAtLeastTwoVisits = jpqlQueryManager.GetPatientsWithAtLeastTwoVisits();
            PrintCollectionWithGson(patientsWithAtLeastTwoVisits, gson);
            System.out.println("========================================");

            // Initialize CriteriaAPI
            var criteriaAPIManager = new CriteriaAPIManager(sessionFactory);

            System.out.println("Criteria API Queries:");
            System.out.println("1.");
            System.out.println("Get All Visits:");
            var _visits = criteriaAPIManager.GetAllVisits();
            PrintCollectionWithGson(_visits, gson);
            System.out.println("========================================");

            System.out.println("Get All Visits Before Date (05-05-2022):");
            var _visitsBeforeDate = criteriaAPIManager.GetVisitsBeforeDate(new Date(2022, 5, 5));
            PrintCollectionWithGson(_visitsBeforeDate, gson);
            System.out.println("========================================");

            System.out.println("2.");
            System.out.println("Get Patients and their Visits:");
            var _patientsAndTheirVisits = criteriaAPIManager.GetPatientsAndTheirVisits();
            PrintCollectionWithGson(_patientsAndTheirVisits, gson);
            System.out.println("========================================");

            System.out.println("Get Patients and their Visits with Bills:");
            var _patientsAndTheirVisitsWithBills = criteriaAPIManager.GetPatientsAndTheirVisitsWithBillsAmountLessThan(50);
            PrintCollectionWithGson(_patientsAndTheirVisitsWithBills, gson);
            System.out.println("========================================");

            System.out.println("3.");
            System.out.println("Get Doctors with at least two Patients:");
            var _doctorsWithAtLeastTwoPatients = criteriaAPIManager.GetDoctorsWithAtLeastTwoDifferentPatients();
            PrintCollectionWithGson(_doctorsWithAtLeastTwoPatients, gson);
            System.out.println("========================================");

            System.out.println("Get Patients with at least two Visits:");
            var _patientsWithAtLeastTwoVisits = criteriaAPIManager.GetPatientsWithAtLeastTwoVisits();
            PrintCollectionWithGson(_patientsWithAtLeastTwoVisits, gson);
            System.out.println("========================================");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void PrintCollectionWithGson(Iterable collection, Gson gson) {
        for (var item : collection) {
            System.out.println(gson.toJson(item));
        }
    }
}