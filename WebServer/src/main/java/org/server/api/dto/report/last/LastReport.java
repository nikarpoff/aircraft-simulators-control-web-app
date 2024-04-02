package org.server.api.dto.report.last;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LastReport {

    @JsonProperty
    private List<SimulatorReport> simulators;

}
