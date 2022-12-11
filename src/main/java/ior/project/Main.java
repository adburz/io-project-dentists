package ior.project;
import ior.project.entities.Visit;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) {
        try {
            DatabaseDataInitializer databaseDataInitializer = new DatabaseDataInitializer();
            databaseDataInitializer.initialize();

            Configuration configuration = new Configuration().configure();
            SessionFactory sessionFactory = configuration.buildSessionFactory();
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