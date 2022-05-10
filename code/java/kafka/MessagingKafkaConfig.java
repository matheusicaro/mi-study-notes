package java.kafka;

import static org.apache.kafka.clients.CommonClientConfigs.GROUP_ID_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.AUTO_OFFSET_RESET_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.MAX_POLL_RECORDS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG;

// private packages
import arch.context.annotation.Bean;
import arch.context.annotation.Factory;
import arch.messaging.provider.kafka.subscription.KafkaSubscription;
import arch.messaging.provider.subscriber.SubscriberMessage;
import arch.pattern.workflow2.flow.FlowProcessor;

import java.kafka.config.CustomAdvisorConfig;
import java.kafka.deserializer.KafkaAvroGenericDeserializer;
import inter.model.avro.KafkaEventPayloadClass;
import inter.workflow.context.AppContext;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import java.util.Properties;
import javax.inject.Named;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Factory
@RequiredArgsConstructor
public class MessagingKafkaConfig {

    private final ServiceClass service;

    @Bean
    @Singleton
    @Named("subs-config")
    public KafkaSubscription<KafkaEventPayloadClass> defaultSubscriptionConfigurer() {
        return KafkaSubscription.builder() // @formatter:off
            .pool(KafkaEventPayloadClass.class)
            .from("KAFKA_TOPIC_NAME")
            .single(this::processMessage)
            .configurer(this::buildKafkaConfig)
            .advisor(new CustomAdvisorConfig(true)) // should it keep kafka pooling?
            .build();  // @formatter:on
    }

    private void buildKafkaConfig(Properties properties) {
        properties.put(MAX_POLL_RECORDS_CONFIG, 10); // kafka max poll records
        properties.put(GROUP_ID_CONFIG, "KAFKA_GROUP_ID");
        properties.put(AUTO_OFFSET_RESET_CONFIG, "latest"); // kafka auto offset rest config
        properties.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, false);
        properties.put(VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroGenericDeserializer.class.getCanonicalName());
    }

    private void processMessage(SubscriberMessage<KafkaEventPayloadClass> message) throws Exception {

        log.info("Message <{}> received", message.id());

        var event = message.body();

        if (event.isEmpty()) {
            log.info("Message <{}> received is empty", message.id());
        } else {
            var payload = event.get();
            var ctx = AppContext.builder().event(payload).build();

            service.execute(payload, ctx);
        }
    }
}