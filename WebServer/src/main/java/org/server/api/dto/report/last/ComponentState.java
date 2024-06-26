package org.server.api.dto.report.last;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComponentState {

    @JsonProperty
    String id;

    @JsonProperty
    int responseTime;

    @JsonProperty
    int temperature;

    @JsonProperty
    int power;

    @JsonProperty
    int voltage;

}
