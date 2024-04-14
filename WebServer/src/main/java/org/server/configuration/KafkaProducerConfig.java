package org.server.configuration;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.server.avro.model.SimulatorState;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;

import java.util.HashMap;
import java.util.Map;

/** Here we realized synchronized messaging between producer and consumer */
@Configuration
public class KafkaProducerConfig {

    // ====================== VALUES =============================
    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Value(value = "${kafka.reply_topic}")
    private String replyTopic;

    @Value(value = "${kafka.groupId}")
    private String groupId;

    @Value(value = "${kafka.schema_registry_url}")
    private String schemaRegistryUrl;

    // ===================== PRODUCER CONFIGURATION (REPLYING) =====================
    // factory for creating default producer with some configuration properties (like bootstrap address)
    @Bean
    public ProducerFactory<String, SimulatorState> producerFactory() {

        System.out.printf("\n\n\nsr url: %s\n\n\n", schemaRegistryUrl);

        Map<String, Object> configProps = new HashMap<>();

        configProps.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress);
        configProps.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        configProps.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                KafkaAvroSerializer.class);
        configProps.put(
                KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG,
                "http://schema-registry:8081");

        return new DefaultKafkaProducerFactory<>(configProps);
    }

    // Standard KafkaTemplate for sending messages
    @Bean
    public KafkaTemplate<String, SimulatorState> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    // ================ CONSUMER (REPLY CONTAINER) =======================
    // default consumer to receive responses from other consumer
    @Bean
    public ConsumerFactory<String, SimulatorState> consumerFactory() {
        Map<String, Object> props = new HashMap<>();

        props.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress);
        props.put(
                ConsumerConfig.GROUP_ID_CONFIG,
                groupId);
        props.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        props.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                KafkaAvroDeserializer.class);
        props.put(
                KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG,
                "http://schema-registry:8081");
        props.put(
                ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,
                "earliest");

        return new DefaultKafkaConsumerFactory<>(props);
    }

    // ReplyingKafkaTemplate bean for synchronized messaging between producer and consumer
    @Bean
    public ReplyingKafkaTemplate<String, SimulatorState, SimulatorState> replyingKafkaTemplate(
            ProducerFactory<String, SimulatorState> producerFactory,                    // for sending request
            KafkaMessageListenerContainer<String, SimulatorState> replyContainer) {     // for receiving response
        return new ReplyingKafkaTemplate<>(producerFactory, replyContainer);
    }

    // Container for receiving messages from consumer
    @Bean
    public KafkaMessageListenerContainer<String, SimulatorState> replyContainer(
            ConsumerFactory<String, SimulatorState> consumerFactory) {
        ContainerProperties containerProperties = new ContainerProperties(replyTopic); // set required reply topic
        return new KafkaMessageListenerContainer<>(consumerFactory, containerProperties);
    }

}
