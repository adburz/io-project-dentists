package ior.project;

import com.google.gson.GsonBuilder;
import com.google.gson.graph.GraphAdapterBuilder;
import ior.project.entities.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import com.google.gson.Gson;

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
            GsonBuilder gsonBuilder = new GsonBuilder();
            new GraphAdapterBuilder()
                    .addType(Bill.class)
                    .addType(Doctor.class)
                    .addType(MedicalTreatment.class)
                    .addType(Patient.class)
                    .addType(Person.class)
                    .addType(Visit.class)
                    .registerOn(gsonBuilder);
            Gson gson = gsonBuilder.create();

            // get and print all visits from session
            var session = sessionFactory.getCurrentSession();
            var t = session.beginTransaction();
            var visits = session.createQuery("from Visit", Visit.class).list();

            System.out.println(gson.toJson(visits));
            t.commit();
            session.close();

            // Initialize CriteriaAPI
            //CriteriaAPI criteriaAPI = new CriteriaAPI(gson, sessionFactory);

            // Initialize JPQL
            //JPQL jpql = new JPQL(gson, sessionFactory);

            // get visits using criteria api and print them
            //System.out.println((criteriaAPI.GetVisitsBeforeDate(new Date()).size()));

            // get visits using jpql and print them using gson
            //System.out.println((jpql.GetVisitsBeforeDate(new Date()).size()));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}