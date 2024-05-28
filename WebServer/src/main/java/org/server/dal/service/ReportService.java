package org.server.dal.service;

import org.server.api.dto.report.last.ComponentReport;
import org.server.api.dto.report.last.LastReport;
import org.server.api.dto.report.last.SimulatorReport;
import org.server.api.dto.report.period.ComponentStatistics;
import org.server.api.dto.report.period.PeriodReport;
import org.server.api.dto.report.period.SimulatorStatistics;
import org.server.dal.exception.DatabaseException;
import org.server.dal.model.*;
import org.server.dal.repository.ReportRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class ReportService extends AbstractCRUDService<Report, Integer> {

    private final ReportRepository repository;
    private final SimulatorService simulatorService;


    public ReportService(ReportRepository repository, SimulatorService simulatorService) {
        super(repository);
        this.repository = repository;
        this.simulatorService = simulatorService;
    }

    public LastReport getLastReport() throws DatabaseException {
        Report report = repository.findFirstByOrderByReportDateTimeDesc();

        if (report == null) {
            throw new DatabaseException("В базе данных нет ни одного отчета!");
        }

        LastReport lastReport = new LastReport();

        lastReport.setReportDate(report.getReportDateTime());

        List<SimulatorReport> lastReportSimulators = new ArrayList<>();
        List<SimulatorStatus> simulators = report.getSimulatorStatuses();

        for (SimulatorStatus simulator : simulators) {

            SimulatorReport simulatorReport = new SimulatorReport();

            simulatorReport.setActive(simulator.isActive());
            simulatorReport.setOccupied(simulator.isOccupied());
            simulatorReport.setId(Integer.toString(simulator.getId()) + " " +  simulator.getSimulator().getSimulatorName());

            List<ComponentReport> lastReportComponents = new ArrayList<>();
            List<ComponentStatus> components = simulator.getComponentsStatuses();

            for (ComponentStatus component : components) {
                ComponentReport componentReport = new ComponentReport(
                        Integer.toString(component.getId()) + " " + component.getComponent().getName(),
                        component.getResponseTime(),
                        component.getTemperature(),
                        component.getPower(),
                        component.getVoltage()
                );

                lastReportComponents.add(componentReport);
            }

            simulatorReport.setComponents(lastReportComponents);
            lastReportSimulators.add(simulatorReport);

        }

        lastReport.setSimulators(lastReportSimulators);

        return lastReport;
    }

    public PeriodReport getPeriodReport(LocalDateTime startDate, LocalDateTime endDate) throws DatabaseException {
        List<Report> reports = repository.findReportsByReportDateTimeBetweenOrderByReportDateTime(startDate, endDate);
        List<Simulator> simulators = simulatorService.getAll();

        Map<String, ComponentStatistics> componentStatisticsMap = new HashMap<>();

        for (Report report : reports) {
            for (SimulatorStatus simulatorStatus : report.getSimulatorStatuses()) {

                System.out.println("sts: " + simulatorStatus.getComponentsStatuses());

                for (ComponentStatus componentStatus : simulatorStatus.getComponentsStatuses()) {
                    String key = String.valueOf(componentStatus.getComponent().getId());
                    ComponentStatistics value = componentStatisticsMap.get(key);

                    System.out.println("value: " + value);

                    if (value == null) {
                        value = new ComponentStatistics(key, componentStatus.getResponseTime(),
                                componentStatus.getTemperature(),
                                componentStatus.getPower(),
                                componentStatus.getVoltage());

                        componentStatisticsMap.put(key, value);
                    } else {
                        value.getResponsesTime().add(componentStatus.getResponseTime());
                        value.getTemperatures().add(componentStatus.getTemperature());
                        value.getPowers().add(componentStatus.getPower());
                        value.getVoltages().add(componentStatus.getVoltage());

                        System.out.println(value);
                    }
                }
            }
        }

        List<SimulatorStatistics> statistics = new ArrayList<>();

        for (Simulator simulator : simulators) {
            SimulatorStatistics simulatorStatistics = new SimulatorStatistics();
            simulatorStatistics.setId(String.valueOf(simulator.getId()));

            List<ComponentStatistics> componentsStatistics = new ArrayList<>();
            for (Component component : simulator.getComponents()) {
                ComponentStatistics componentStatistics = componentStatisticsMap.get(String.valueOf(component.getId()));

                if (componentStatistics == null) {
                    componentStatistics = new ComponentStatistics(String.valueOf(component.getId()));
                }

                System.out.println(componentStatistics);

                componentsStatistics.add(componentStatistics);
            }

            simulatorStatistics.setComponents(componentsStatistics);
            statistics.add(simulatorStatistics);
        }

        final int MAX_POINTS_NUMBER = 15;
        final int statisticsSize = statistics.size();

        if (statisticsSize > MAX_POINTS_NUMBER) {
            // Создаем объект Random для генерации случайных чисел
            Random random = new Random();

            int min = 0;

            // Создаем массив для хранения сгенерированных чисел
            List<Integer> randomNumbers = new ArrayList<>(MAX_POINTS_NUMBER);
            List<SimulatorStatistics> shortStatistics = new ArrayList<>(MAX_POINTS_NUMBER);

            // Генерируем 15 равномерно распределенных случайных чисел
            for (int i = 0; i < MAX_POINTS_NUMBER; i++) {
                // Генерируем случайное число в заданном диапазоне
                int randomNumber = random.nextInt(statisticsSize - min) + min;
                randomNumbers.add(randomNumber);
            }

            Collections.sort(randomNumbers);

            for (int randomNumber : randomNumbers) {
                shortStatistics.add(statistics.get(randomNumber));
            }

            return new PeriodReport(shortStatistics);
        }

        return new PeriodReport(statistics);
    }

}
