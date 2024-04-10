package org.simulator.kafka;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@Slf4j
public class KafkaConsumerListeners {


    /** Consumer for topic about lab (add/delete lab from table from target db) */
    @KafkaListener(topics = "${kafka.simulator_topic}", groupId = "${kafka.groupId}")
    public void listener(ConsumerRecord<String, String> record) {

        log.info("Consumer generator got request!");

        if (record.value() == null) {
            return;
        }


    }

}
