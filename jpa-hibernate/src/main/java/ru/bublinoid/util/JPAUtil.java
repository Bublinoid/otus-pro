package ru.bublinoid.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

    private static final String PERSISTENCE_UNIT_NAME = "customer-product-unit";
    private static volatile EntityManagerFactory emf;

    private JPAUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static EntityManager getEntityManager() {
        if (emf == null) {
            synchronized (JPAUtil.class) {
                if (emf == null) {
                    try {
                        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new ExceptionInInitializerError("Initial EntityManagerFactory creation failed: " + e);
                    }
                }
            }
        }
        return emf.createEntityManager();
    }

    public static void close() {
        if (emf != null) {
            emf.close();
        }
    }
}
