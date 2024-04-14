package org.server.configuration;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicsConfig {

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Value(value = "${kafka.reply_topic}")
    private String replyTopic;

    @Value(value = "${kafka.simulator_topic}")
    private String requestTopic;

    @Value(value = "${kafka.partition_number}")
    private int topicsPartitionsNumber;

    private final short REPLICATION_FACTOR = 1;

    @Bean
    public KafkaAdmin admin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic ReplyTopic() {
        return TopicBuilder.name(replyTopic)
                .partitions(topicsPartitionsNumber)
                .replicas(REPLICATION_FACTOR)
                .compact()
                .build();
    }

    @Bean
    public NewTopic RequestTopic() {
        return TopicBuilder.name(requestTopic)
                .partitions(topicsPartitionsNumber)
                .replicas(REPLICATION_FACTOR)
                .compact()
                .build();
    }
}
