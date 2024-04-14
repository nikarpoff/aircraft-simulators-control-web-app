package org.server.generator;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.server.avro.model.SimulatorState;
import org.server.avro.model.ComponentState;
import org.server.dal.exception.DatabaseException;
import org.server.dal.service.ComponentStatusService;
import org.server.dal.service.ReportService;
import org.server.dal.service.SimulatorService;
import org.server.dal.service.SimulatorStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

@org.springframework.stereotype.Component
@Slf4j
public class ReportGeneratorService {

    SimulatorService simulatorService;
    SimulatorStatusService simulatorStatusService;
    ComponentStatusService componentStatusService;
    ReportService reportService;

    final KafkaService kafkaService;

    Random random;

    @Autowired
    public ReportGeneratorService(SimulatorService simulatorService, SimulatorStatusService simulatorStatusService, ComponentStatusService componentStatusService, ReportService reportService, KafkaService kafkaService) {
        this.simulatorService = simulatorService;
        this.simulatorStatusService = simulatorStatusService;
        this.componentStatusService = componentStatusService;
        this.reportService = reportService;

        this.random = new Random();
        this.kafkaService = kafkaService;
    }

    @Scheduled(fixedRate = 10000) // 10 секунд
    @Transactional
    public void generateReport() throws DatabaseException {



        System.out.println("Генерация отчета!");
//
//        List<Simulator> simulators = simulatorService.getAll();
//
//        if (simulators.isEmpty()) {
//            System.out.println("Генерация отменена!");
//            return;
//        }
//
//        System.out.println("Генерация отчета началась!");
//
//        Report report = new Report();
//        report.setReportDateTime(LocalDateTime.now());
//
//        report = reportService.save(report);
//
//        List<SimulatorStatus> simulatorStatuses = new ArrayList<>();
//        for (Simulator simulator : simulators) {
//            SimulatorStatus simulatorStatus = new SimulatorStatus();
//            simulatorStatus.setSimulator(simulator);
//            simulatorStatus.setReport(report);
//
//            boolean isActive = random.nextBoolean();
//            simulatorStatus.setActive(isActive);
//
//            System.out.println("is acitve: " + isActive);
//
//            if (isActive) {
//                simulatorStatus.setOccupied(random.nextBoolean());
//                System.out.println("is occupied: " + simulatorStatus.isOccupied());
//
//                simulatorStatus = simulatorStatusService.save(simulatorStatus);
//
//                List<ComponentStatus> componentStatuses = new ArrayList<>();
//
//                for (Component component : simulator.getComponents()) {
//                    ComponentStatus componentStatus = new ComponentStatus();
//
//                    componentStatus.setComponent(component);
//                    componentStatus.setPower(random.nextInt(400) + 800);
//                    componentStatus.setVoltage(random.nextInt(31) + 200);
//                    componentStatus.setResponseTime(random.nextInt(10) + 5);
//                    componentStatus.setTemperature(random.nextInt(80) + 20);
//                    componentStatus.setSimulatorStatus(simulatorStatus);
//
//                    componentStatuses.add(componentStatusService.save(componentStatus));
//                }
//
//                simulatorStatus.setComponentsStatuses(componentStatuses);
//            }
//
//            simulatorStatuses.add(simulatorStatusService.save(simulatorStatus));
//        }
//
//        report.setSimulatorStatuses(simulatorStatuses);
//        reportService.save(report);

        SimulatorState response;

        SimulatorState simulatorState = new SimulatorState();
        List<ComponentState> componentsReport = new ArrayList<>();

        componentsReport.add(new ComponentState("1", 0, 0, 0, 0));
        componentsReport.add(new ComponentState("2", 0, 0, 0, 0));

        simulatorState.setComponents(componentsReport);
        simulatorState.setId("123");

        simulatorState.setId("123");

        try {
            response = kafkaService.sendMessageAndWaitForResponse(simulatorState.getId(), simulatorState);

            // Удалим все кавычки из ответа
            String responseValue = response.toString();

            log.info("response gotten: " + responseValue);

        }
        catch (ExecutionException | InterruptedException | IllegalArgumentException e) {
            log.info(String.format("error while waiting response %s", e.getMessage()));
        }
    }

}
