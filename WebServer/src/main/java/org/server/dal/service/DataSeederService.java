package org.server.dal.service;

import lombok.AllArgsConstructor;
import org.server.dal.exception.DatabaseException;
import org.server.dal.model.Component;
import org.server.dal.model.Simulator;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@AllArgsConstructor
public class DataSeederService {

    ComponentService componentService;
    SimulatorService simulatorService;

    public void initData() throws DatabaseException {

        if (!simulatorService.getAll().isEmpty()) {
            return;
        }


        Simulator simulator = new Simulator();
        simulator.setSimulatorName("WSIM-32F6");
        simulator.setModel("Симулятор военной авиации");
        simulator.setType("V-76 simulator");
        simulator.setProductionDate(LocalDate.of(2003, 7, 27));
        simulator.setCommissioningDate(LocalDate.of(2004, 2, 22));
        simulator.setLastTechCheckDate(LocalDate.of(2023, 12, 11));
        simulator.setTechCheckFrequency(12);


        Simulator simulator2 = new Simulator();
        simulator2.setSimulatorName("Boing 78 simulator");
        simulator2.setModel("EMU-CIVIL-B78");
        simulator2.setType("Симулятор гражданской авиации");
        simulator2.setProductionDate(LocalDate.of(2001, 11, 9));
        simulator2.setCommissioningDate(LocalDate.of(2002, 1, 21));
        simulator2.setLastTechCheckDate(LocalDate.of(2018, 5, 1));
        simulator2.setTechCheckFrequency(36);


        Simulator simulator3 = new Simulator();
        simulator3.setSimulatorName("Hork-22");
        simulator3.setModel("ENG0004");
        simulator3.setType("Инженерный симулятор");
        simulator3.setProductionDate(LocalDate.of(2006, 10, 1));
        simulator3.setCommissioningDate(LocalDate.of(2006, 03, 21));
        simulator3.setLastTechCheckDate(LocalDate.of(2020, 07, 14));
        simulator3.setTechCheckFrequency(24);

        try {
            simulator = simulatorService.save(simulator);
            simulator2 = simulatorService.save(simulator2);
            simulator3 = simulatorService.save(simulator3);
        } catch (DatabaseException e) {
            throw e;
        }

        Component component = new Component();
        component.setName("MUTH0");
        component.setSimulator(simulator);

        Component component2 = new Component();
        component2.setName("MUTL2");
        component2.setSimulator(simulator);

        Component component3 = new Component();
        component3.setName("YUA21");
        component3.setSimulator(simulator2);

        Component component4 = new Component();
        component4.setName("LMO00");
        component4.setSimulator(simulator3);

        Component component5 = new Component();
        component5.setName("ORH12");
        component5.setSimulator(simulator3);

        Component component6 = new Component();
        component6.setName("PLL01");
        component6.setSimulator(simulator3);

        try {
            component = componentService.save(component);
            component2 = componentService.save(component2);
            component3 = componentService.save(component3);
            component4 = componentService.save(component4);
            component5 = componentService.save(component5);
            component6 = componentService.save(component6);
        } catch (DatabaseException e) {
            throw e;
        }

        simulator.setComponents(new ArrayList<>(Arrays.asList(new Component[]{component, component2})));
        simulator2.setComponents(new ArrayList<>(Arrays.asList(new Component[]{component3})));
        simulator3.setComponents(new ArrayList<>(Arrays.asList(new Component[]{component4, component5, component6})));

        simulatorService.save(simulator);
        simulatorService.save(simulator2);
        simulatorService.save(simulator3);
    }

}
