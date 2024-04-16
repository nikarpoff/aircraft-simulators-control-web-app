package org.server.dal.service;

import org.server.api.dto.report.period.ComponentStatistics;
import org.server.api.dto.report.period.PeriodReport;
import org.server.api.dto.report.period.SimulatorStatistics;
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
import java.util.*;

@Service
public class SimulatorService extends AbstractCRUDService<Simulator, Integer> {

    SimulatorRepository repository;
    ComponentRepository componentRepository;

    Random random;

    public SimulatorService(SimulatorRepository repository, ComponentRepository componentRepository) {
        super(repository);
        this.repository = repository;
        this.componentRepository = componentRepository;

        random = new Random();
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

//    public PeriodReport getStatistics() throws DatabaseException {
//        List<Simulator> simulators = (List<Simulator>) repository.findAll();
//        List<SimulatorStatistics> simulatorsStatistics = new ArrayList<>();
//
//        if (simulators.isEmpty()) {
//            throw new DatabaseException("Empty Database!");
//        }
//
//        for (Simulator simulator : simulators) {
//            List<ComponentStatistics> componentsStatistics = new ArrayList<>();
//
//            SimulatorStatistics simulatorStatistics = new SimulatorStatistics();
//            simulatorStatistics.setId(simulator.getId() + " " + simulator.getSimulatorName());
//
//            // TODO CHECK EMPTY COMPONENTS
//            for (Component component : simulator.getComponents()) {
//                int size = random.nextInt(6) + 6;
//
//                int[] power = new int[size];
//                int[] temperature = new int[size];
//                int[] voltage = new int[size];
//                int[] time = new int[size];
//
//                for (int i = 0; i < size; i++) {
//                    power[i] = random.nextInt(400) + 800;
//                    voltage[i] = random.nextInt(31) + 200;
//                    time[i] = random.nextInt(10) + 5;
//                    temperature[i] = random.nextInt(80) + 20;
//                }
//
//                componentsStatistics.add(new ComponentStatistics(
//                    component.getId() + component.getName(), time, temperature, power, voltage)
//                );
//            }
//
//            simulatorStatistics.setComponents(componentsStatistics);
//
//            simulatorsStatistics.add(simulatorStatistics);
//        }
//
//
//        return new PeriodReport(simulatorsStatistics);
//    }

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

    public void deleteSimulatorById(String id) throws DatabaseException {
        int parsedId;

        try {
            parsedId = Integer.parseInt(id);
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }

        repository.deleteById(parsedId);
    }
}
