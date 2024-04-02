package org.server.dal.service;

import org.server.dal.model.Component;
import org.server.dal.repository.ComponentRepository;
import org.springframework.stereotype.Service;

@Service
public class ComponentService extends AbstractCRUDService<Component, Integer> {

    public ComponentService(ComponentRepository repository) {
        super(repository);
    }

}
