package org.server.api.controller;

import lombok.AllArgsConstructor;
import org.server.api.dto.report.last.LastReport;
import org.server.api.dto.report.period.PeriodReport;
import org.server.api.dto.simulator.SimulatorRequest;
import org.server.api.dto.simulator.SimulatorResponse;
import org.server.api.exception.ForbiddenException;
import org.server.dal.exception.DatabaseException;
import org.server.dal.model.Simulator;
import org.server.dal.service.DataSeederService;
import org.server.dal.service.ReportService;
import org.server.dal.service.SimulatorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/simulator")
@AllArgsConstructor
public class SimulatorController {

    SimulatorService simulatorService;

    @RequestMapping(value = "/",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<List<SimulatorResponse>> getSimulators(Principal principal) {
        if (principal == null) {
            throw new ForbiddenException();
        }

        try {
            return new ResponseEntity<>(simulatorService.getAllAndParse(), HttpStatus.OK);
        } catch (DatabaseException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/add",
            consumes = {"application/json"},
            produces = {"application/json"},
            method = RequestMethod.POST)
    public ResponseEntity<SimulatorResponse> addSimulator(Principal principal, @RequestBody() SimulatorRequest simulatorRequest) {
        if (principal == null) {
            throw new ForbiddenException();
        }

        return new ResponseEntity<>(simulatorService.parseAndAdd(simulatorRequest), HttpStatus.OK);
    }

    @RequestMapping(value = "/statistics",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<PeriodReport> getSimulatorsStatistics(Principal principal) {
        if (principal == null) {
            throw new ForbiddenException();
        }

        try {
            return new ResponseEntity<>(simulatorService.getStatistics(), HttpStatus.OK);
        } catch (DatabaseException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}