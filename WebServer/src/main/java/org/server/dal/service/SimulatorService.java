package org.server.dal.service;

import org.server.api.dto.simulator.ComponentRequest;
import org.server.api.dto.simulator.ComponentResponse;
import org.server.api.dto.simulator.SimulatorRequest;
import org.server.api.dto.simulator.SimulatorResponse;
import org.server.dal.exception.DatabaseException;
import org.server.dal.model.Component;
import org.server.dal.model.Simulator;
import org.server.dal.repository.ComponentRepository;
import org.server.dal.repository.SimulatorRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class SimulatorService extends AbstractCRUDService<Simulator, Integer> {

    SimulatorRepository repository;
    ComponentRepository componentRepository;

    public SimulatorService(SimulatorRepository repository, ComponentRepository componentRepository) {
        super(repository);
        this.repository = repository;
        this.componentRepository = componentRepository;
    }

    public List<SimulatorResponse> getAllAndParse() throws DatabaseException {
        List<SimulatorResponse> response = new ArrayList<>();

        List<Simulator> simulators = (List<Simulator>) repository.findAll();

        System.out.println(simulators);

        if (simulators.isEmpty()) {
            throw new DatabaseException("Empty Database!");
        }

        for (Simulator simulator : simulators) {
            List<ComponentResponse> componentResponse = new ArrayList<>();

            // TODO CHECK EMPTY COMPONENTS
            for (Component component : simulator.getComponents()) {
                componentResponse.add(new ComponentResponse(component.getId(),
                        component.getName()));
            }

            response.add(new SimulatorResponse(
                    simulator.getId(),
                    simulator.getModel(),
                    simulator.getType(),
                    simulator.getSimulatorName(),
                    simulator.getProductionDate(),
                    simulator.getCommissioningDate(),
                    simulator.getLastTechCheckDate(),
                    simulator.getTechCheckFrequency(),
                    componentResponse
            ));
        }



        return response;
    }

    public SimulatorResponse parseAndAdd(SimulatorRequest simulatorRequest) {

        Simulator simulator = new Simulator();

        simulator.setSimulatorName(simulatorRequest.getSimulatorName());
        simulator.setModel(simulatorRequest.getModel());
        simulator.setType(simulatorRequest.getType());
        simulator.setProductionDate(simulatorRequest.getProductionDate());
        simulator.setCommissioningDate(simulatorRequest.getCommissioningDate());
        simulator.setTechCheckFrequency(simulatorRequest.getTechCheckFrequency());
        simulator.setLastTechCheckDate(LocalDate.now());

        simulator = repository.save(simulator);

        List<Component> components = new ArrayList<>();

        for (ComponentRequest componentRequest : simulatorRequest.getComponents()) {
            Component component = new Component();

            component.setName(componentRequest.getName());
            component.setSimulator(simulator);

            components.add(componentRepository.save(component));
        }

        simulator.setComponents(components);
        repository.save(simulator);

        List<ComponentResponse> componentsResponse = new ArrayList<>();

        for (Component component : components) {
            componentsResponse.add(new ComponentResponse(component.getId(),
                    component.getName()));
        }

        return new SimulatorResponse(simulator.getId(),
                simulator.getModel(),
                simulator.getType(),
                simulator.getSimulatorName(),
                simulator.getProductionDate(),
                simulator.getCommissioningDate(),
                simulator.getLastTechCheckDate(),
                simulator.getTechCheckFrequency(),
                componentsResponse);
    }
}
