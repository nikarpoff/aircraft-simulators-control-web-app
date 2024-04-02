package org.server.api.dto.simulator;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimulatorRequest {

    @JsonProperty
    private String model;

    @JsonProperty
    private String type;

    @JsonProperty
    private String simulatorName;

    @JsonProperty
    private LocalDate productionDate;

    @JsonProperty
    private LocalDate commissioningDate;

    @JsonProperty
    private LocalDate lastTechCheckDate;

    @JsonProperty
    private int techCheckFrequency;

    @JsonProperty
    private List<ComponentRequest> components;

}
