package github.briannbig;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Application Starting.....");
        System.out.println("Starting producer....");
        new TrxProducer().runAlways("", (topicName, transaction) -> {

            String source = KafkaMessageHandlerImpl.class.getName();
            Transaction trx = transaction.value();

            System.out.println("*** " + source + " ********>>>>> " + trx);
        });
    }
}