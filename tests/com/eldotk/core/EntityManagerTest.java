package com.eldotk.core;

import com.eldotk.exceptions.*;
import org.junit.*;
import org.junit.rules.*;

import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class EntityManagerTest {

    EntityManager entityManager = new EntityManager();
    UUID entity1;
    UUID entity2;
    UUID entity3;

    @Rule
    public ExpectedException expectedException;

    @Before
    public void setUp() {
        expectedException = ExpectedException.none();

        entity1 = entityManager.createEntity();
        entity2 = entityManager.createEntity();
        entity3 = entityManager.createEntity();
    }

    @After
    public void tearDown() {
        entityManager.clear();
    }

    @Test
    public void testCreateEntity() {
        System.out.println("[EntityManager] In testCreateEntity");

        UUID entity = entityManager.createEntity();

        assertThat(entityManager.entities, hasItem(entity));
        assertThat(entityManager.entities, hasItems(entity, entity1, entity2, entity3));
        assertEquals(entityManager.entities.size(), 4);
    }

    @Test
    public void testDestroyPresentEntity() {
        System.out.println("[EntityManager] In testDestroyPresentEntity");

        try {
            entityManager.destroyEntity(entity1);
        } catch (EntityException e) {
            e.printStackTrace();
        }

        assertThat(entityManager.entities, not(hasItem(entity1)));
        assertEquals(entityManager.entities.size(), 2);
    }

    @Test(expected = EntityException.class)
    public void testDestroyAbsentEntity() throws EntityException {
        System.out.println("[EntityManager] In testDestroyAbsentEntity");

        UUID fakeUuid = UUID.randomUUID();
        entityManager.destroyEntity(fakeUuid);
    }

    @Test
    public void testClear() {
        System.out.println("[EntityManager] In testClear");

        entityManager.clear();

        assertEquals(entityManager.entities.size(), 0);
        assertEquals(entityManager.entities.isEmpty(), true);
        assertEquals(entityManager.components.size(), 0);
        assertEquals(entityManager.components.isEmpty(), true);
    }

    @Test
    public void testAddNewEntity() {
        System.out.println("[EntityManager] In testAddNewEntity");

        UUID entity = UUID.randomUUID();
        try {
            entityManager.addEntity(entity);
        } catch (EntityException e) {
            e.printStackTrace();
        }

        assertThat(entityManager.entities, hasItem(entity));
        assertThat(entityManager.entities, hasItems(entity, entity1, entity2, entity3));
        assertEquals(entityManager.entities.size(), 4);
    }

    @Test(expected = EntityException.class)
    public void testAddAlreadyPresentEntity() throws EntityException {
        System.out.println("[EntityManager] In testAddAlreadyPresentEntity");

        entityManager.addEntity(entity1);
    }
}