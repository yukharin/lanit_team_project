package com.lanit.satonin18.model.utils;

import com.lanit.satonin18.model.entity.Organization;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    public HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Organization.class);
                //StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                        //.applySettings(configuration.getProperties());

                //sessionFactory = configuration.buildSessionFactory(builder.build());
                sessionFactory = configuration.buildSessionFactory();

            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }
}