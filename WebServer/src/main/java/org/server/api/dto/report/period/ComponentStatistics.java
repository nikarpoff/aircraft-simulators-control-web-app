package org.server.api.dto.report.period;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComponentStatistics {

    @JsonProperty
    int id;

    @JsonProperty
    int[] responsesTime;

    @JsonProperty
    int[] temperatures;

    @JsonProperty
    int[] powers;

    @JsonProperty
    int[] voltages;

}
