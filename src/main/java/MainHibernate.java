import com.lanit.lkz_project.entities.jpa_entities.Notification;
import com.lanit.lkz_project.entities.jpa_entities.NotificationStatus;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


public class MainHibernate {


    public static void main(String[] args) {
        File file = new File("classpath: hibernate.cfg.xml");
        System.out.println(file.canRead());
        @Cleanup SessionFactory factory = new Configuration().configure().buildSessionFactory();
        @Cleanup Session session = factory.getCurrentSession();
        session.beginTransaction();
        session.getTransaction().commit();
    }



    private static void code(Session session) {
        Date date = new GregorianCalendar(2019, Calendar.SEPTEMBER, 16).getTime();
        System.err.println(date);
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Notification> query = builder.createQuery(Notification.class);
        Root<Notification> root = query.from(Notification.class);
        Predicate predicate =
                builder.and(builder.or(
                        builder.equal(root.get("status"), NotificationStatus.NEW),
                        builder.equal(root.get("status"), NotificationStatus.APPROVED),
                        builder.equal(root.get("status"), NotificationStatus.REJECTED)),
                        builder.greaterThan(root.get("dateResponse"), date));

        query.select(root).where(predicate);

        TypedQuery<Notification> finalQuery = session.createQuery(query);
        List<Notification> result = finalQuery.getResultList();
        for (Notification notification : result) {
            System.err.println(notification);
        }
    }
}