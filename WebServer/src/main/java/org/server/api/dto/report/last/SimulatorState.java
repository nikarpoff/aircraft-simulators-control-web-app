package org.server.api.dto.report.last;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class SimulatorState {

    @JsonProperty
    private String id;

    @JsonProperty()
    private boolean isActive;

    @JsonProperty()
    private boolean isOccupied;

    @JsonProperty
    private List<ComponentState> components;

}
