package org.server.api.controller;

import lombok.AllArgsConstructor;
import org.server.api.dto.report.tech.TechCheckResponse;
import org.server.api.exception.ForbiddenException;
import org.server.dal.exception.DatabaseException;
import org.server.dal.service.TechCheckService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/")
public class TechCheckController {

    TechCheckService techCheckService;

    @RequestMapping(value = "/tech",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<TechCheckResponse> getTechCheckInfo(Principal principal) {
        if (principal == null) {
            throw new ForbiddenException();
        }

        try {
            return new ResponseEntity<>(techCheckService.getTechCheckInfo(), HttpStatus.OK);
        } catch (DatabaseException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
