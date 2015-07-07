package com.eldotk.core;

import com.eldotk.exceptions.*;

import java.util.*;

public class EntityManager {

    protected List<UUID> entities;
    protected HashMap<Class, HashMap<UUID, ? extends IComponent>> components;


    public EntityManager() {

        entities = new LinkedList<>();
        components = new HashMap<>();

    }

    public List<UUID> getEntities() {
        return entities;
    }

    public void setEntities(List<UUID> entities) {
        this.entities = entities;
    }

    public HashMap<Class, HashMap<UUID, ? extends IComponent>> getComponents() {
        return components;
    }

    public void setComponents(HashMap<Class, HashMap<UUID, ? extends IComponent>> components) {
        this.components = components;
    }

    public UUID createEntity() {

        final UUID uuid = UUID.randomUUID();
        entities.add(uuid);

        return uuid;
    }

    public void destroyEntity(UUID entity) throws EntityException {

        if (!entities.contains(entity)) {
            throw new EntityException("There no entity with UUID: " + entity.toString());
        } else {
            entities.remove(entity);
        }

    }

    public void clear() {
        components = new HashMap<>();
        entities = new LinkedList<>();
    }

    public void addEntity(UUID entity) throws EntityException {
        if (entities.contains(entity)) {
            throw new EntityException("There already an entity with UUID: " + entity.toString());
        } else {
            entities.add(entity);
        }
    }

}
