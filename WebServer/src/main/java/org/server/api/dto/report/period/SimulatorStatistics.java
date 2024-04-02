package org.server.api.dto.report.period;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimulatorStatistics {

    @JsonProperty
    private List<ComponentStatistics> components;

}
