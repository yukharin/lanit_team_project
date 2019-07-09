import com.lanit.lkz_project.dao.NotificationDAO;
import com.lanit.lkz_project.entities.Notification;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

public class MainHibernate {

    @Autowired
    static NotificationDAO notificationDAO;

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

        Notification notification = session.get(Notification.class, 1);
        System.err.println(notification);
    }
}