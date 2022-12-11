package ior.project;
import ior.project.entities.Visit;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import com.google.gson.Gson;
import org.hibernate.service.ServiceRegistry;

public class Main {
    public static void main(String[] args) {
        try {
            Configuration configuration = new Configuration().configure();
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

            DatabaseDataInitializer databaseDataInitializer = new DatabaseDataInitializer();
            databaseDataInitializer.initialize(configuration, serviceRegistry);

            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            Session session = sessionFactory.openSession();

            Visit visit = session.get(Visit.class, 1);

            Gson gson = new Gson();
            System.out.println(gson.toJson(visit));

            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}