package org.simulator.kafka;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.generic.GenericData.Record;
import org.apache.avro.util.Utf8;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class KafkaConsumerListeners {


    /** Consumer for topic about lab (add/delete lab from table from target db) */
    @KafkaListener(topics = "${kafka.topic_lab}", groupId = "${kafka.groupId}")
    public void listener(ConsumerRecord<Record, Record> record) {

        log.info("Consumer listener lab work got request!");

        if (record.value() == null) {
            return;
        }


    }

}
