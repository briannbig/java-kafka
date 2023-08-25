package github.briannbig.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import github.briannbig.Transaction;
import org.apache.kafka.common.serialization.Deserializer;

public class TransactionDeserializer implements Deserializer<Transaction> {

    private final ObjectMapper objectMapper =new ObjectMapper();

    @Override
    public Transaction deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(data, Transaction.class);
        } catch (Exception e) {
            throw new RuntimeException("Error deserializing Transaction object: " + e.getMessage(), e);
        }
    }
}
