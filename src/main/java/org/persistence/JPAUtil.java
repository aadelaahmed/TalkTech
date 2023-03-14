package org.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.util.Constants;

public class JPAUtil {
    private static EntityManagerFactory entityManagerFactory;

    public static EntityManager getEntityManager() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory(Constants.PERSISTENCE_UNIT_NAME);
        }
        return entityManagerFactory.createEntityManager();
    }
}
