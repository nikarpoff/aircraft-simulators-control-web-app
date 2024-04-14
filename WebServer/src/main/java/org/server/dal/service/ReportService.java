package org.server.dal.service;

import org.server.api.dto.report.last.ComponentState;
import org.server.api.dto.report.last.LastReport;
import org.server.api.dto.report.last.SimulatorState;
import org.server.dal.exception.DatabaseException;
import org.server.dal.model.*;
import org.server.dal.repository.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService extends AbstractCRUDService<Report, Integer> {

    ReportRepository repository;

    public ReportService(ReportRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public LastReport getLastReport() throws DatabaseException {
        Report report = repository.findFirstByOrderByReportDateTimeDesc();

        if (report == null) {
            throw new DatabaseException("В базе данных нет ни одного отчета!");
        }

        LastReport lastReport = new LastReport();

        lastReport.setReportDate(report.getReportDateTime());

        List<SimulatorState> lastReportSimulators = new ArrayList<>();
        List<SimulatorStatus> simulators = report.getSimulatorStatuses();

        for (SimulatorStatus simulator : simulators) {

            SimulatorState simulatorState = new SimulatorState();

            simulatorState.setActive(simulator.isActive());
            simulatorState.setOccupied(simulator.isOccupied());
            simulatorState.setId(Integer.toString(simulator.getId()) + " " +  simulator.getSimulator().getSimulatorName());

            List<ComponentState> lastReportComponents = new ArrayList<>();
            List<ComponentStatus> components = simulator.getComponentsStatuses();

            for (ComponentStatus component : components) {
                ComponentState componentState = new ComponentState(
                        Integer.toString(component.getId()) + " " + component.getComponent().getName(),
                        component.getResponseTime(),
                        component.getTemperature(),
                        component.getPower(),
                        component.getVoltage()
                );

                lastReportComponents.add(componentState);
            }

            simulatorState.setComponents(lastReportComponents);
            lastReportSimulators.add(simulatorState);

        }

        lastReport.setSimulators(lastReportSimulators);

        return lastReport;
    }

//    public PeriodReport getPeriodReport(Date startDate, Date endDate) {
//        List<Report> reports = repository.findByReportDateTimeBetween(startDate, endDate);
//
//        List<SimulatorStatus> simulatorStatuses = reports
//    }

}
