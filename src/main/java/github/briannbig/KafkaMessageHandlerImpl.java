package github.briannbig;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public class KafkaMessageHandlerImpl implements KafkaMessageHandler {
    @Override
    public void processMessage(String topicName, ConsumerRecord<String, Transaction> transaction) throws Exception {

        String source = KafkaMessageHandlerImpl.class.getName();
        Transaction trx = transaction.value();


        System.out.println("*** " + source + " ********>>>>> " + trx );



    }
}
