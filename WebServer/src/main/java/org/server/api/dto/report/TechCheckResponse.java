package org.server.api.dto.report;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TechCheckResponse {

    @JsonProperty
    List<TechCheck> techCheck;

}
