package org.server.api.dto.report.period;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
public class ComponentStatistics {

    @JsonProperty
    String id;

    @JsonProperty
    List<Integer> responsesTime = new ArrayList<>();

    @JsonProperty
    List<Integer> temperatures = new ArrayList<>();

    @JsonProperty
    List<Integer> powers = new ArrayList<>();

    @JsonProperty
    List<Integer> voltages = new ArrayList<>();

    public ComponentStatistics(String id, int responseTime, int temperature, int power, int voltage) {
        this.id = id;

        this.responsesTime.add(responseTime);
        this.temperatures.add(temperature);
        this.powers.add(power);
        this.voltages.add(voltage);
    }

    public ComponentStatistics(String id) {
        this.id = id;
    }
}
