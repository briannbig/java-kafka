package github.briannbig;

import java.io.InputStream;
import java.util.Properties;

public final class Config {

    public static final String CONFIG_FILE = "config.properties";

    public static final String BOOTSTRAP_SERVERS = "bootstrap.servers";
    public static final String DEFAULT_TOPIC = "default.topic";
    public static final String TOPIC = "app.topic";

    public static Properties getProperties() throws Exception {

        Properties props;

        InputStream input = Config.class.getClassLoader().getResourceAsStream(CONFIG_FILE);

        props = new Properties();

        if (input == null) {
            throw new Exception("Unable to find " + CONFIG_FILE);

        }
        props.load(input);

        return props;

    }

    public static String getTopic() throws Exception {
        String topic = getProperties().getProperty(TOPIC, "topic-transaction");
        if (topic == null) {
            return getDefaultTopic();
        }
        return topic;
    }

    public static String getDefaultTopic() throws Exception {
        return getProperties().getProperty(DEFAULT_TOPIC, "topic-transaction");
    }

    public static String getBootstrapServers() throws Exception {
        return getProperties().getProperty(BOOTSTRAP_SERVERS, "localhost:9092");
    }



}
