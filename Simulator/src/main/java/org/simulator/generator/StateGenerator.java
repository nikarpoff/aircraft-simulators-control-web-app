package org.simulator.generator;
import lombok.extern.slf4j.Slf4j;
import org.simulator.dto.ComponentState;
import org.simulator.dto.SimulatorState;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class StateGenerator {

    private final Random random;

    public StateGenerator() {
        this.random = new Random();
    }

    public SimulatorState generateState(SimulatorState simulatorState) {

        log.info("Consumer generate values!");

        boolean isActive = random.nextBoolean();
        simulatorState.setIsActive(isActive);

        if (isActive) {
            simulatorState.setIsOccupied(random.nextBoolean());

            for (ComponentState component : simulatorState.getComponents()) {
                component.setPower(random.nextInt(400) + 800);
                component.setVoltage(random.nextInt(31) + 200);
                component.setResponseTime(random.nextInt(10) + 5);
                component.setTemperature(random.nextInt(80) + 20);
            }
        }

        log.info("Values were generated!");

        return simulatorState;
    }

}
