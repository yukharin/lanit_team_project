import com.lanit.lkz_project.entities.Organization;
import com.lanit.lkz_project.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class MainHibernate {
//    private static final SessionFactory ourSessionFactory;
//
//    static {
//        try {
//            Configuration configuration = new Configuration();
//            configuration.configure();
//
//            ourSessionFactory = configuration.buildSessionFactory();
//        } catch (Throwable ex) {
//            throw new ExceptionInInitializerError(ex);
//        }
//    }
//
//    public static Session getSession() throws HibernateException {
//        return ourSessionFactory.openSession();
//    }
//
//    public static void main(final String[] args) throws Exception {
//        final Session session = getSession();
//        try {
//            System.out.println("querying all the managed entities...");
//            final Metamodel metamodel = session.getSessionFactory().getMetamodel();
//            for (EntityType<?> entityType : metamodel.getEntities()) {
//                final String entityName = entityType.getName();
//                final Query query = session.createQuery("from " + entityName);
//                System.out.println("executing: " + query.getQueryString());
//                for (Object o : query.list()) {
//                    System.out.println("  " + o);
//                }
//            }
//        } finally {
//            session.close();
//        }
//    }

    private static final String usr = "vlad_yukharin";
    private static final String url = "jdbc:mysql://127.0.0.1:3306/lanit?useSSL=false&useLegacyDatetimeCode=false&amp&serverTimezone=UTC";
    private static final String passwrd = "password7788";

    public static void main(String[] args) {
        File file = new File("C:\\Users\\user1\\IdeaProjects\\lkz_project\\src\\main\\resources\\hibernate.cfg.xml");
        System.out.println(file.canRead());
        SessionFactory factory = new Configuration().configure().addAnnotatedClass(User.class).buildSessionFactory();
        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            User user = new User(new Organization(), "Vlad", "Yukharin");
            session.save(user);
            session.getTransaction().commit();
        } finally {
            factory.close();
        }

    }
}