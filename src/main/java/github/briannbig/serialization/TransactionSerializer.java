package github.briannbig.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import github.briannbig.Transaction;
import org.apache.kafka.common.serialization.Serializer;

public class TransactionSerializer implements Serializer<Transaction> {

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public byte[] serialize(String topic, Transaction transaction) {

        try {
            return objectMapper.writeValueAsBytes(transaction);
        } catch (Exception e) {
            throw new RuntimeException("Error serializing transaction object: " + e.getMessage(), e);
        }

    }
}
