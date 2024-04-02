package org.server.api.dto.simulator;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ComponentResponse {

    @JsonProperty
    private int id;

    @JsonProperty
    private String name;

}
