package com.lanit.lkz_project.dao.entities_dao;

//public class UserDAO {
//
//    @Autowired
//    private SessionFactory sessionFactory;
//
//
//    public void addUser(@NonNull User user) {
//        Session session = sessionFactory.getCurrentSession();
//        session.persist(user);
//    }
//
//
//    public void updateUser(@NonNull User user) {
//        Session session = sessionFactory.getCurrentSession();
//        session.merge(user);
//    }
//
//    public void removeUser(@NonNull User user) {
//        Session session = sessionFactory.getCurrentSession();
//        session.delete(user);
//    }
//
//    public void removeUser(long id) {
//        Session session = sessionFactory.getCurrentSession();
//        User user = session.load(User.class, id);
//        if (user != null) {
//            session.delete(user);
//        }
//    }
//
//
//    public User getUserById(long id) {
//        Session session = sessionFactory.getCurrentSession();
//        return session.get(User.class, id);
//    }
//
//
//    public List<User> users() {
//        Session session = sessionFactory.getCurrentSession();
//        return session.createQuery("FROM User ORDER BY id", User.class).list();
//    }
//}
