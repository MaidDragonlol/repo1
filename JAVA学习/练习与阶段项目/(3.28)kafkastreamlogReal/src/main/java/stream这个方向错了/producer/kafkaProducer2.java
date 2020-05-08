package stream这个方向错了.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class kafkaProducer2 {
    /*这个作为输入*/
    public static void main(String[] args) throws Exception {
        Properties kafkaProps = new Properties();
        kafkaProps.put("bootstrap.servers", "localhost:9092");
        kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<String, String> producer = new KafkaProducer<>(kafkaProps);
        String jsonC = "2020-02-28 04:10:56,search_lack_of_data_error,MSP,PHL,TuniuController.query(..),TUNIU\n" +
                "2020-02-28 04:10:53,success,MEX,CPE,TuniuController.query(..),TUNIU\n" +
                "2020-02-28 04:05:25,search_lack_of_data_error,SAO,SSA,TuniuController.query(..),TUNIU\n" +
                "2020-02-28 04:05:14,search_lack_of_data_error,ORL,MEX,TuniuController.query(..),TUNIU\n" +
                "2020-02-28 04:05:18,search_lack_of_data_error,SAO,MIA,TuniuController.query(..),TUNIU\n" +
                "2020-02-28 04:10:50,search_lack_of_data_error,SFO,ONT,TuniuController.query(..),TUNIU";
        ProducerRecord<String, String> producerRecord =
                new ProducerRecord<String, String>("streams-clear-output", "logs", jsonC);
        producer.send(producerRecord);
        producer.flush();
        producer.close();


    }
}
