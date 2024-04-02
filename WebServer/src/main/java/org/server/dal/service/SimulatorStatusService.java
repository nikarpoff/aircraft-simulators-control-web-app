package org.server.dal.service;

import org.server.dal.model.SimulatorStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class SimulatorStatusService extends AbstractCRUDService<SimulatorStatus, Integer> {


    public SimulatorStatusService(CrudRepository<SimulatorStatus, Integer> repository) {
        super(repository);
    }

}
