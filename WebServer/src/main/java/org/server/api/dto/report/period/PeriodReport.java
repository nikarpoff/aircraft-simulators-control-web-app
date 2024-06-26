package org.server.api.dto.report.period;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class PeriodReport {

    @JsonProperty
    private List<SimulatorStatistics> simulators;

}
