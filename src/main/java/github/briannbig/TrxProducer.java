package github.briannbig;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

import static github.briannbig.Util.getRandomString;

public class TrxProducer extends AbstractKafka {

    KafkaProducer<String, Transaction> producer;

    private final AtomicBoolean closed = new AtomicBoolean(false);

    @Override
    protected void shutdown() throws Exception {
        closed.set(true);
        getKafkaProducer().close();
    }

    @Override
    protected void runAlways(String topicName, KafkaMessageHandler messageHandler) throws Exception {
        int i = 1;
        while (true) {
            i += 1;

            Transaction trx = new Transaction(getRandomString(), getRandomString(), i * 100.21);
            ProducerRecord<String, Transaction> producerRecord = new ProducerRecord<>(topicName, "trx [" + trx.getId() + "]", trx);

            getKafkaProducer().send(producerRecord);
            System.out.println(producerRecord);
            Thread.sleep(1000);

        }

    }

    private KafkaProducer<String, Transaction> getKafkaProducer() throws Exception {
        if (producer == null) {
            Properties props = Config.getProperties();
            producer = new KafkaProducer<>(props);
        }

        return producer;
    }

    public static void main(String[] args) throws Exception {
        String topicName = Config.getTopic().toString();
        System.out.println("Starting producer....");
        new TrxProducer().runAlways(topicName, (topic, transaction) -> {
        });
    }
}
