package org.server.api.dto.report.tech;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TechCheck {

    @JsonProperty
    private String id;

    @JsonProperty
    private LocalDate lastTechCheckDate;

    @JsonProperty
    private String description;

    @JsonProperty
    private boolean isTechCheckRequired;

}
