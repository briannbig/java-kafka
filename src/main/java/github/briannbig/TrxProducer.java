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
        closed.set(false);
        getKafkaProducer().close();
    }

    @Override
    protected void runAlways(String topicName, KafkaMessageHandler messageHandler) throws Exception {
        int i = 1;
        while (true) {
            i += 1;
            System.out.println("sending message....");
            sendMessage(i);
            Thread.sleep(1000);

        }

    }

    public void run(int numberOfMessages) throws Exception {
        int i = 1;
        while (i <= numberOfMessages) {
            sendMessage(i);
            i++;
        }

    }

    private void sendMessage(int amount) throws Exception {
        Transaction trx = new Transaction(getRandomString(), getRandomString(), amount * 100.21);
        ProducerRecord<String, Transaction> producerRecord = new ProducerRecord<>(Config.getTopic(), "trx [" + trx.getId() + "]", trx);

        getKafkaProducer().send(producerRecord);
        System.out.println(producerRecord);
    }

    private KafkaProducer<String, Transaction> getKafkaProducer() throws Exception {
        if (producer == null) {
            Properties props = Config.getProperties();
            producer = new KafkaProducer<>(props);
        }

        return producer;
    }
}
