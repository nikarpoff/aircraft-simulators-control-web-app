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
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/simulator")
@AllArgsConstructor
public class SimulatorController {

    private final SimulatorService simulatorService;

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

    @RequestMapping(value = "/",
            method = RequestMethod.DELETE)
    public ResponseEntity deleteSimulator(Principal principal, @RequestParam String id) {
        if (principal == null) {
            throw new ForbiddenException();
        }

        try {
            simulatorService.deleteSimulatorById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DatabaseException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}