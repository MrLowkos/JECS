package com.eldotk;

import com.eldotk.core.EntityManager;
import com.eldotk.exceptions.*;

import java.util.UUID;

/**
 * Created by Elka on 06/07/2015.
 */
public class Main {

    public static void main(String... args) {

        EntityManager entityManager = new EntityManager();

        UUID entity1 = entityManager.createEntity();
        UUID entity2 = entityManager.createEntity();
        UUID entity3 = entityManager.createEntity();

        UUID fakeUUID = UUID.randomUUID();

        entityManager.getEntities().forEach((entity) -> System.out.println("UUID: " + entity));

        try {
            entityManager.destroyEntity(entity1);
        } catch (EntityException e) {
            e.printStackTrace();
        }

        System.out.println("****************************************************");
        entityManager.getEntities().forEach((entity) -> System.out.println("UUID: " + entity));


        System.out.println("****************************************************");
        System.out.println(fakeUUID);

        try {
            entityManager.destroyEntity(fakeUUID);
        } catch (EntityException e) {
            e.printStackTrace();
        }
    }
}
