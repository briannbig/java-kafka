package github.briannbig;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;

import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

public class TrxConsumer extends AbstractKafka {

    private final AtomicBoolean closed = new AtomicBoolean(false);

    private KafkaConsumer<String, Transaction> consumer = null;

    @Override
    protected void shutdown() throws Exception {
        closed.set(true);
        getConsumer().close();

    }

    @Override
    protected void runAlways(String topicName, KafkaMessageHandler messageHandler) throws Exception {


        try {
            getConsumer().subscribe(List.of(topicName));


            while (!closed.get()) {
                ConsumerRecords<String, Transaction> records = getConsumer().poll(Duration.ofMillis(5000));
                if (records.count() == 0) {
                    System.out.println(":::::::Consumer::::::: NO RECORDS FOUND! ");
                }
                for (ConsumerRecord<String, Transaction> record :
                        records) {
                    messageHandler.processMessage("", record);
                }


            }
        } catch (WakeupException e) {
            if (!closed.get()) {
                throw e;
            }
        }
    }


    private KafkaConsumer<String, Transaction> getConsumer() throws Exception {
        if (consumer == null) {
            Properties props = Config.getProperties();
            consumer = new KafkaConsumer<>(props);
        }

        return consumer;
    }

    public static void main(String[] args) throws Exception {
        String topicName = Config.getTopic();
        System.out.println("Starting consumer....");
        new TrxConsumer().runAlways(topicName, new KafkaMessageHandlerImpl());
    }
}
