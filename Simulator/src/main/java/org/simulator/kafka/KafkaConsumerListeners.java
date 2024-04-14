package org.simulator.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.simulator.dto.ComponentState;
import org.simulator.dto.SimulatorState;
import org.simulator.generator.StateGenerator;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class KafkaConsumerListeners {

    final StateGenerator generator;
    final KafkaTemplate<String, SimulatorState> kafkaTemplate;

    public KafkaConsumerListeners(StateGenerator generator, KafkaTemplate<String, SimulatorState> kafkaTemplate) {
        this.generator = generator;
        this.kafkaTemplate = kafkaTemplate;
    }

    /** Consumer is generator of random values about simulator */
    @KafkaListener(topics = "${kafka.simulator_topic}", groupId = "${kafka.groupId}")
    @SendTo({"states_reply"})
    public SimulatorState listener(ConsumerRecord<String, GenericRecord> record) {

        log.info(String.format("Consumer generator got request! Record: %s", record.value()));

        if (record.value() == null) {
            log.info("Generating aborted: kafka message content is empty");
            return new SimulatorState();
        }


        // parse generic record to the dto
        GenericRecord value = record.value();
        SimulatorState simulatorState = new SimulatorState();

        simulatorState.setId((String) value.get("id"));
        simulatorState.setIsActive((Boolean) value.get("isActive"));
        simulatorState.setIsOccupied((Boolean) value.get("isOccupied"));

        // Handle components list
        List<ComponentState> components = new ArrayList<>();
        GenericData.Array<?> avroComponents = (GenericData.Array<?>) value.get("components");

        for (Object component : avroComponents) {
            components.add(getComponentState((GenericRecord) component));
        }

        simulatorState.setComponents(components);

        // generating new state values
        if (simulatorState.getId().isEmpty()) {
            log.info("Generating aborted: simulator id is empty");
            return new SimulatorState();
        }

        SimulatorState generatedSimulatorState = generator.generateState(simulatorState);
        log.info("Generated state is " + generatedSimulatorState.toString());

        return generatedSimulatorState;
    }

    private static ComponentState getComponentState(GenericRecord component) {
        ComponentState componentState = new ComponentState();
        componentState.setId((String) component.get("id"));
        componentState.setResponseTime((Integer) component.get("responseTime"));
        componentState.setTemperature((Integer) component.get("temperature"));
        componentState.setPower((Integer) component.get("power"));
        componentState.setVoltage((Integer) component.get("voltage"));
        return componentState;
    }

}
