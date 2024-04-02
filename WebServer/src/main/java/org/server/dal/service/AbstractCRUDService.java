package org.server.dal.service;

import org.server.dal.exception.DatabaseException;
import org.server.dal.model.DatabaseEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

public class AbstractCRUDService<Entity extends DatabaseEntity, ID> {

    protected final CrudRepository<Entity, ID> repository;

    public AbstractCRUDService(CrudRepository<Entity, ID> repository) {
        this.repository = repository;
    }

    public List<Entity> getAll() {
        Iterable<Entity> entities = repository.findAll();
        return StreamSupport.stream(entities.spliterator(), false)
                .toList();
    }

    public Entity getById(ID id) throws DatabaseException {
        Optional<Entity> entity = repository.findById(id);

        if (entity.isEmpty()) {
            throw new DatabaseException("no entity with such id");
        }

        return entity.get();
    }

    public Entity save(Entity entity) throws DatabaseException {
        try {
            return (Entity) repository.save(entity);
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Entity update(Entity entity) throws DatabaseException {
        try {
            return (Entity) repository.save(entity);
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public void delete(ID id) throws DatabaseException {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

}
