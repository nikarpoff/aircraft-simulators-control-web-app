package org.server.dal.service;

import org.server.api.dto.report.last.ComponentReport;
import org.server.api.dto.report.last.LastReport;
import org.server.api.dto.report.last.SimulatorReport;
import org.server.api.dto.report.period.PeriodReport;
import org.server.dal.exception.DatabaseException;
import org.server.dal.model.*;
import org.server.dal.repository.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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

//    public PeriodReport getPeriodReport(Date startDate, Date endDate) {
//        List<Report> reports = repository.findByReportDateTimeBetween(startDate, endDate);
//
//        List<SimulatorStatus> simulatorStatuses = reports
//    }

}
