package org.server.generator;

import lombok.extern.apachecommons.CommonsLog;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.server.avro.model.SimulatorState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.requestreply.KafkaReplyTimeoutException;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@CommonsLog(topic = "Producer Logger")
public class KafkaService {

    @Value(value = "${kafka.simulator_topic}")
    private String requestTopic;

    @Value(value = "${kafka.reply_topic}")
    private String replyTopic;

    private final ReplyingKafkaTemplate<String, SimulatorState, SimulatorState> replyingKafkaTemplate;

    @Autowired
    public KafkaService(ReplyingKafkaTemplate<String, SimulatorState, SimulatorState> replyingKafkaTemplate) {
        this.replyingKafkaTemplate = replyingKafkaTemplate;
    }


    public SimulatorState sendMessageAndWaitForResponse(String key, SimulatorState simulatorState) throws ExecutionException, InterruptedException, KafkaReplyTimeoutException {
        // TODO async
        log.info(String.format("Send topic is -> %s", requestTopic));

        // post in kafka topic
        ProducerRecord<String, SimulatorState> record = new ProducerRecord<>(requestTopic, simulatorState);
        // set reply topic in header
        record.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, replyTopic.getBytes()));
//        record.headers().add(new RecordHeader(KafkaHeaders.PARTITION_ID, key.getBytes()));

        log.info(String.format("Record is -> %s\n", record.value().toString()));

        RequestReplyFuture<String, SimulatorState, SimulatorState> future = replyingKafkaTemplate.sendAndReceive(record);

        // confirm if producer produced successfully
        SendResult<String, SimulatorState> sendResult = future.getSendFuture().get();
        //print all headers
        sendResult.getProducerRecord().headers().forEach(header -> System.out.println(header.key() + ":" + header.value().toString()));

        // get consumer record
        ConsumerRecord<String, SimulatorState> consumerRecord = future.get();

        log.info(String.format("responseRecord is -> %s\n", consumerRecord));

        // Ожидание ответа от consumer
        return consumerRecord.value();
    }

}
