package io.ari.repositories.entities;

import io.ari.repositories.exceptions.EntityNotFound;

import java.util.HashMap;
import java.util.Map;

public class EntitiesRepository<T extends Entity> {

    public T save(T entity) {
        entities.put(entity.getId(), entity);
        return entity;
    }

    public T update(String id, T entity) {
        return entities.put(id, entity);
    }

    public T findById(String entityId) throws EntityNotFound {
        if (!entities.containsKey(entityId)) {
            throw new EntityNotFound();
        }

        return entities.get(entityId);
    }

    public boolean exists(String id) {
        try {
            findById(id);
            return true;
        } catch (EntityNotFound entityNotFound) {
            return false;
        }
    }

    public void deleteAll() {
        entities.clear();
    }

    public void delete(String entityId) {
        entities.remove(entityId);
    }

    public Object findOne(String entityId) throws EntityNotFound {
        if (!entities.containsKey(entityId)) {
            throw new EntityNotFound();
        }

        return entities.get(entityId);
    }

    private Map<String, T> entities = new HashMap<>();
}