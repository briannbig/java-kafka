package github.briannbig;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public class KafkaMessageHandlerImpl implements KafkaMessageHandler {
    @Override
    public void processMessage(String topicName, ConsumerRecord<String, Transaction> transaction) throws Exception {

        String source = KafkaMessageHandlerImpl.class.getName();
        Transaction trx = transaction.value();

        trx.setSourceAcc("cons"+trx.getSourceAcc());
        trx.setDestAcc("cons"+trx.getDestAcc());
        trx.setAmount(trx.getAmount()/2);

        System.out.println("*** " + source + " ********>>>>> " +
                        "Transaction{" +
                        "id='" + trx.getId() + '\'' +
                        "sourceAcc='" + trx.getSourceAcc() + '\'' +
                        ", destAcc='" + trx.getDestAcc() + '\'' +
                        ", amount=" + trx.getAmount() +
                        '}'
                );



    }
}
