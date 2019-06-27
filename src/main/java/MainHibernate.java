import com.google.gson.Gson;
import com.lanit.lkz_project.entities.User;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.util.List;

public class MainHibernate {

    public static void main(String[] args) {
        File file = new File("classpath: hibernate.cfg.xml");
        System.out.println(file.canRead());
        @Cleanup SessionFactory factory = new Configuration().configure().buildSessionFactory();
        @Cleanup Session session = factory.getCurrentSession();
        session.beginTransaction();
        code(session);
        session.getTransaction().commit();
    }

    private static void code(Session session) {
        List<User> users = session.createQuery("FROM User ").list();
        Gson gson = new Gson();
        String str = gson.toJson(users);
        System.out.println(str);
    }
}