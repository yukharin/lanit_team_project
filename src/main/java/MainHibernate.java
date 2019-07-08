import com.lanit.lkz_project.entities.Notification;
import com.lanit.lkz_project.entities.Organization;
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
        List<Organization> organizations = session.createQuery("FROM Organization ", Organization.class).list();
        for (Organization organization : organizations) {
            System.err.println(organization);
            for (Notification notification : organization.getNotifications()) {
                System.err.println(notification);
            }
        }
    }
}