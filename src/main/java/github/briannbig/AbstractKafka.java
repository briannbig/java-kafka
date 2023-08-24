package github.briannbig;

public abstract class AbstractKafka {

    public static final String TAG = AbstractKafka.class.getCanonicalName();

    public AbstractKafka() {

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {

            try {
                shutdown();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }));
    }

    protected abstract void shutdown() throws Exception;

    protected abstract void runAlways(String topicName, KafkaMessageHandler messageHandler) throws Exception;
}
