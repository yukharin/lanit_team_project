package com.lanit.lkz_project.dao.entities_dao;

//public class NotificationDAO {
//
//    @Autowired
//    private SessionFactory sessionFactory;
//
//
//    public void addNotification(@NonNull Notification notification) {
//        Session session = sessionFactory.getCurrentSession();
//        session.persist(notification);
//    }
//
//
//    public void updateNotification(@NonNull Notification notification) {
//        Session session = sessionFactory.getCurrentSession();
//        session.merge(notification);
//    }
//
//    public void removeNotification(@NonNull Notification notification) {
//        Session session = sessionFactory.getCurrentSession();
//        session.delete(notification);
//    }
//
//    public void removeNotification(long id) {
//        Session session = sessionFactory.getCurrentSession();
//        Notification notification = session.load(Notification.class, id);
//        if (notification != null) {
//            session.delete(notification);
//        }
//    }
//
//    public Notification getNotification(long id) {
//        Session session = sessionFactory.getCurrentSession();
//        Notification notification = session.get(Notification.class, id);
//        return notification;
//    }
//
//    public List<Notification> notifications() {
//        Session session = sessionFactory.getCurrentSession();
//        return session.createQuery("FROM  Notification ORDER BY id", Notification.class).list();
//    }
//
////    public List<Notification> testCriteria() {
////        Session session = sessionFactory.getCurrentSession();
////        CriteriaBuilder builder = session.getCriteriaBuilder();
////        CriteriaQuery<Notification> query = builder.createQuery(Notification.class);
////        Root<Notification> root = query.from( Notification.class );
////        query.select(root);
////        TypedQuery<Notification> finalQuery = session.createQuery(query);
////        return finalQuery.getResultList();
////    }
//}
