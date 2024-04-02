package org.server.generator;

import lombok.AllArgsConstructor;
import org.server.dal.exception.DatabaseException;
import org.server.dal.model.*;
import org.server.dal.service.ComponentStatusService;
import org.server.dal.service.ReportService;
import org.server.dal.service.SimulatorService;
import org.server.dal.service.SimulatorStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@org.springframework.stereotype.Component
public class ReportGeneratorService {

    SimulatorService simulatorService;
    SimulatorStatusService simulatorStatusService;
    ComponentStatusService componentStatusService;
    ReportService reportService;

    Random random;

    @Autowired
    public ReportGeneratorService(SimulatorService simulatorService, SimulatorStatusService simulatorStatusService, ComponentStatusService componentStatusService, ReportService reportService) {
        this.simulatorService = simulatorService;
        this.simulatorStatusService = simulatorStatusService;
        this.componentStatusService = componentStatusService;
        this.reportService = reportService;

        this.random = new Random();
    }

    @Scheduled(fixedRate = 10000) // 10 секунд
    @Transactional
    public void generateReport() throws DatabaseException {
        System.out.println("Генерация отчета!");

        List<Simulator> simulators = simulatorService.getAll();

        if (simulators.isEmpty()) {
            System.out.println("Генерация отменена!");
            return;
        }

        System.out.println("Генерация отчета началась!");

        Report report = new Report();
        report.setReportDateTime(LocalDateTime.now());

        report = reportService.save(report);

        List<SimulatorStatus> simulatorStatuses = new ArrayList<>();
        for (Simulator simulator : simulators) {
            SimulatorStatus simulatorStatus = new SimulatorStatus();
            simulatorStatus.setSimulator(simulator);
            simulatorStatus.setReport(report);

            boolean isActive = random.nextBoolean();
            simulatorStatus.setActive(isActive);

            System.out.println("is acitve: " + isActive);

            if (isActive) {
                simulatorStatus.setOccupied(random.nextBoolean());
                System.out.println("is occupied: " + simulatorStatus.isOccupied());

                simulatorStatus = simulatorStatusService.save(simulatorStatus);

                List<ComponentStatus> componentStatuses = new ArrayList<>();

                for (Component component : simulator.getComponents()) {
                    ComponentStatus componentStatus = new ComponentStatus();

                    componentStatus.setComponent(component);
                    componentStatus.setPower(random.nextInt(400) + 800);
                    componentStatus.setVoltage(random.nextInt(31) + 200);
                    componentStatus.setResponseTime(random.nextInt(10) + 5);
                    componentStatus.setTemperature(random.nextInt(80) + 20);
                    componentStatus.setSimulatorStatus(simulatorStatus);

                    componentStatuses.add(componentStatusService.save(componentStatus));
                }

                simulatorStatus.setComponentsStatuses(componentStatuses);
            }

            simulatorStatuses.add(simulatorStatusService.save(simulatorStatus));
        }

        report.setSimulatorStatuses(simulatorStatuses);
        reportService.save(report);
    }

}
