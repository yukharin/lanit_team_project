//package com.lanit.satonin18.app.dao;
//
//
//import com.lanit.satonin18.app.entity.NotificationStatus;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.hibernate.query.Query;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Repository("notificationStatusDAO")
////@Transactional
//public class NotificationStatusDAOImp implements NotificationStatusDAO {
//
//   @Autowired
//   private SessionFactory sessionFactory;
//
//   @Override
//   public List<NotificationStatus> filterIds(String[] ids) {
//      //Session session = sessionFactory.getCurrentSession();
//      try(final Session session = sessionFactory.openSession();){
////         Transaction tx1 = session.beginTransaction();
////           //ONLY NEED JOIN(or use List) AND HQL-request
////            StringBuilder sql = new StringBuilder();
////            for (int i=0; i<ids.length; i++) {
////                if(i==0){
////                    sql.append("SELECT * FROM notifications WHERE id_notification_status = :theNotificationStatusId#"+i);
////                }
////                sql.append("OR id_notification_status = :theNotificationStatusId#"+i);
////            }
////            Query theQuery = null;
////            theQuery = session.createNativeQuery(sql.toString());
////            for (int i=0; i<ids.length; i++) {
////                theQuery.setParameter(
////                        "theNotificationStatusId#"+i,
////                        Integer.parseInt(ids[i])
////                );
////            }
//
////            List<NotificationStatus> listNotificStatus = new ArrayList<>();
////            for (int i=0; i<ids.length; i++) {
//////                ids[i];
////            }
////            Query theQuery = null;
////            theQuery = session.createQuery(
////                    "FROM Notification "+
//////                            Notification.class.getSimpleName()+", "+
//////                            NotificationStatus.class.getSimpleName()+" "+
////                            "WHERE Notification.notificationStatus IN (:listNotificStatus)",
////                    Notification.class);
////            theQuery.setParameterList("listNotificStatus", listNotificStatus);
//
//         //--------------------------------------------
////            StringBuilder sql = new StringBuilder();
////            for (int i=0; i<listNotificStatus.size(); i++) {
////                if(i==0){
////                    sql.append("FROM Notification WHERE Notification.notificationStatus = :theNotificationStatus"+i);
////                }
////                sql.append(" OR Notification.notificationStatus = :theNotificationStatus"+i);
////            }
//
//         List<Integer> listIds = new ArrayList();
//         for (String id : ids) {
//            listIds.add(Integer.parseInt(id));
//         }
//
//         Query theQuery = null;
//         theQuery = session.createQuery(
//                 "FROM NotificationStatus n WHERE n.id IN (:listIds)",
//                 NotificationStatus.class);
//         theQuery.setParameterList("listIds", listIds);
//         //theQuery.setParameterList("listNotificStatus", listNotificStatus);
////            for (int i=0; i<listNotificStatus.size(); i++) {
////                theQuery.setParameter(
////                        "theNotificationStatus"+i,
////                        listNotificStatus.get(i)
////                );
////            }
//
//         List<NotificationStatus> list = theQuery.getResultList();
////         tx1.commit();
//         return list;
//      }
//   }
//
//   @Override
//   public void saveOrUpdate(NotificationStatus notificationStatus) {  //TODO need saveOrUpdate @NotNull final IN ARG //throws Exc
//      //Session session = sessionFactory.getCurrentSession();
//      try(final Session session = sessionFactory.openSession();){
//         Transaction tx1 = session.beginTransaction();
//
//         session.saveOrUpdate(notificationStatus);
//
//         tx1.commit();
//      }
//   }
//
//   @Override
//   public void update(NotificationStatus notificationStatus) {
//      //Session session = sessionFactory.getCurrentSession();
//      try(final Session session = sessionFactory.openSession();){
//         Transaction tx1 = session.beginTransaction();
//
//         session.update(notificationStatus);
//
//         tx1.commit();
//      }
//   }
//
//   @Override
//   public void delete(int id) {
//      //Session session = sessionFactory.getCurrentSession();
//      try(final Session session = sessionFactory.openSession();){
//         Transaction tx1 = session.beginTransaction();
//
//         NotificationStatus notificationStatus = session.load(NotificationStatus.class, id);
//
//         if(notificationStatus != null)
//            session.delete(notificationStatus);
//
//         tx1.commit();
//      }
//   }
//
//   @Override
//   public NotificationStatus getById(int id) {
//      //Session session = sessionFactory.getCurrentSession();
//      try(final Session session = sessionFactory.openSession();){
////         Transaction tx1 = session.beginTransaction();
//
//         NotificationStatus notificationStatus = session.get(NotificationStatus.class, id);
//
////         tx1.commit();
//         return notificationStatus;
//      }
//   }
//
//   @Override
//   public List<NotificationStatus> list() {
//      //Session session = sessionFactory.getCurrentSession();
//      try(final Session session = sessionFactory.openSession();){
////         Transaction tx1 = session.beginTransaction();
//
//         List<NotificationStatus> notificationStatuss = session.createQuery("from NotificationStatus", NotificationStatus.class).list();
//
////         tx1.commit();
//         return notificationStatuss;
//      }
//   }
//
//   public List<NotificationStatus> listByIds(List<Integer> ids) {
//      //Session session = sessionFactory.getCurrentSession();
//      try (final Session session = sessionFactory.openSession();) {
////         Transaction tx1 = session.beginTransaction();
//
//         Query theQuery = null;
//         theQuery = session.createQuery(
//                 "FROM NotificationStatus n WHERE n.id IN (:ids)",
//                 NotificationStatus.class);
//         theQuery.setParameterList("ids", ids);
//         //the
//
//         List<NotificationStatus> list = theQuery.getResultList();
////         tx1.commit();
//         return list;
//      }
//   }
//}