import com.lanit.lkz_project.dao.NotificationDAO;
import com.lanit.lkz_project.entities.Notification;
import com.lanit.lkz_project.entities.NotificationStatus;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.File;
import java.util.List;


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
//        Notification notification = session.get(Notification.class, 1);
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Notification> query = builder.createQuery(Notification.class);
        Root<Notification> root = query.from(Notification.class);
        Predicate predicate = builder.or(builder.equal(root.get("status"), NotificationStatus.NEW), builder.equal(root.get("status"), NotificationStatus.APPROVED));
        query.select(root).where(predicate);
        TypedQuery<Notification> finalQuery = session.createQuery(query);
        List<Notification> result = finalQuery.getResultList();
        for (Notification notification : result) {
            System.err.println(notification);
        }
    }
}