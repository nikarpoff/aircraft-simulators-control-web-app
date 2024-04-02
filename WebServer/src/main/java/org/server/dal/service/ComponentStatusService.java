package org.server.dal.service;

import org.server.dal.model.ComponentStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class ComponentStatusService extends AbstractCRUDService<ComponentStatus, Integer> {
    public ComponentStatusService(CrudRepository<ComponentStatus, Integer> repository) {
        super(repository);
    }
}
