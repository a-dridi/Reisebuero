package at.adridi.reisebuero.db;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Hibernate Utility class um das Session-Factory-Objekt zubekommen
 *
 */
public class HibernateUtil {

    private static final SessionFactory SESSION_FACTORY;
    private static final ServiceRegistry SERVER_REGISTRY;

    static {
        try {
            // Konfigurationen laden und mappen
            Configuration configuration = new Configuration().configure();
            SERVER_REGISTRY
                    = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            //Session-Factory von der Service-Registry erstellen 
            SESSION_FACTORY = configuration.buildSessionFactory(SERVER_REGISTRY);
        } catch (HibernateException ex) {
            // Exceptions loggen
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }

    public static void close() {
        if (SERVER_REGISTRY != null) {
            StandardServiceRegistryBuilder.destroy(SERVER_REGISTRY);
        }
    }
}
