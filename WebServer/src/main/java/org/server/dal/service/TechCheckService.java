package org.server.dal.service;

import lombok.AllArgsConstructor;
import org.server.api.dto.report.TechCheck;
import org.server.api.dto.report.TechCheckResponse;
import org.server.dal.exception.DatabaseException;
import org.server.dal.model.Simulator;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TechCheckService {

    SimulatorService simulatorService;

    public TechCheckResponse getTechCheckInfo() throws DatabaseException {

        List<TechCheck> response = new ArrayList<>();

        List<Simulator> simulators = simulatorService.getAll();

        if (simulators.isEmpty()) {
            throw new DatabaseException("Empty Database!");
        }

        for (Simulator simulator : simulators) {
            TechCheck techCheck = new TechCheck();
            techCheck.setId(simulator.getId() + simulator.getSimulatorName());

            LocalDate today = LocalDate.now();
            LocalDate lastTechCheck = simulator.getLastTechCheckDate();

            // Вычисление разницы между датами в периоде
            Period period = Period.between(lastTechCheck, today);

            // Получение количества месяцев из периода
            int monthsDifference = period.getYears() * 12 + period.getMonths();

            boolean isTechCheckRequired = false;
            String description = "Нет необходимости в техническом осмотре!";

            if (monthsDifference > simulator.getTechCheckFrequency()) {
                isTechCheckRequired = true;
                description = "Последний технический осмотр проводился " + monthsDifference + " месяцев назад, что превышает " +
                        "заявленную переодичность осмотра в " + simulator.getTechCheckFrequency() + " месяцев";
            }

            techCheck.setTechCheckRequired(isTechCheckRequired);
            techCheck.setDescription(description);
            techCheck.setLastTechCheckDate(simulator.getLastTechCheckDate());

            response.add(techCheck);
        }

        return new TechCheckResponse(response);
    }

}
