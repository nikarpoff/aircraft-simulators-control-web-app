package org.server.api.controller;

import lombok.AllArgsConstructor;
import org.server.api.dto.report.last.LastReport;
import org.server.api.dto.report.period.PeriodReport;
import org.server.api.exception.ForbiddenException;
import org.server.dal.exception.DatabaseException;
import org.server.dal.service.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/report")
@AllArgsConstructor
public class ReportController {


    ReportService reportService;

    @RequestMapping(value = "/last",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<LastReport> getLastReport(Principal principal) {
        if (principal == null) {
            throw new ForbiddenException();
        }

        try {
            return new ResponseEntity<LastReport>(reportService.getLastReport(), HttpStatus.OK);
        } catch (DatabaseException e) {
            return new ResponseEntity<LastReport>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @RequestMapping(value = "/period",
//            produces = {"application/json"},
//            method = RequestMethod.GET)
//    public ResponseEntity<PeriodReport> getPeriodReport(Principal principal, @RequestParam("startDate")Date startDate, @RequestParam("endDate")Date endDate) {
//        if (principal == null) {
//            throw new ForbiddenException();
//        }
//
//        try {
//            return new ResponseEntity<PeriodReport>(reportService.getPeriodReport(startDate, endDate), HttpStatus.OK);
//        } catch (DatabaseException e) {
//            return new ResponseEntity<PeriodReport>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @RequestMapping(value = "/statistics",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<PeriodReport> getSimulatorsStatistics(Principal principal, @RequestParam String startDate, @RequestParam String endDate) {
        if (principal == null) {
            throw new ForbiddenException();
        }

        System.out.println(startDate); // 2024-04-14
        System.out.println(endDate);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime startDateTime;
        LocalDateTime endDateTime;

        try {
            startDateTime = LocalDateTime.parse(startDate + "T00:00:00", formatter); // exc: Text '2024-04-14T00:00:00' could not be parsed, unparsed text found at index 10
            endDateTime = LocalDateTime.parse(endDate + "T00:00:00", formatter);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        System.out.println(startDateTime);
        System.out.println(endDateTime);

        try {
            return new ResponseEntity<>(reportService.getPeriodReport(startDateTime, endDateTime), HttpStatus.OK);
        } catch (DatabaseException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
