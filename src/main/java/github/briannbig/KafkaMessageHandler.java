package github.briannbig;

import org.apache.kafka.clients.consumer.ConsumerRecord;

@FunctionalInterface
public interface KafkaMessageHandler {

    void processMessage(String topicName, ConsumerRecord<String, Transaction> transaction) throws Exception;

}
