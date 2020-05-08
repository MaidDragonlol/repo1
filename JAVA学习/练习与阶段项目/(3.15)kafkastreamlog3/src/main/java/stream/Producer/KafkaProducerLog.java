package stream.Producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.json.JSONObject;

import java.util.*;

public class KafkaProducerLog {
    /*这个作为输入*/
    public static void main(String[] args) throws Exception {
        Properties kafkaProps = new Properties();
        kafkaProps.put("bootstrap.servers", "localhost:9092");
        kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<String, String> producer = new KafkaProducer<>(kafkaProps);
        String jsonC = "{\"time\":\"2020-02-28 00:00:04\", \"thread\":\"http-nio-8000-exec-12\",\"class\":\"com.yyc.gateway.configuration.aspect.ControllerLogAspect\",\"categoryId\":\"gateway-service\",\"level\":\"WARN\",\"message\":{\"method\":\"TuniuController.query(..)\",\"startTime\":\"2020-02-28T00:00:00.103\",\"startMills\":1582819200103,\"elapseMills\":4751,\"args\":[{\"cid\":\"cxtuniu\",\"tripType\":1,\"fromCity\":\"TPA\",\"toCity\":\"BUF\",\"fromDate\":\"20201224\",\"retDate\":\"\",\"cabinClass\":1,\"adultNumber\":2,\"childNumber\":0}],\"result\":{\"status\":999,\"msg\":\"search_lack_of_data_error\",\"routings\":null},\"error\":false,\"exMsg\":null,\"ota\":\"TUNIU\",\"stage\":\"QUERY\",\"path\":\"/tuniu/flight/v1/query\",\"remoteAddr\":\"180.97.2.17\"}}\n" +
                "{\"time\":\"2020-02-28 00:00:05\", \"thread\":\"http-nio-8000-exec-19\",\"class\":\"com.yyc.gateway.configuration.aspect.ControllerLogAspect\",\"categoryId\":\"gateway-service\",\"level\":\"WARN\",\"message\":{\"method\":\"TuniuController.query(..)\",\"startTime\":\"2020-02-28T00:00:05.616\",\"startMills\":1582819205616,\"elapseMills\":7,\"args\":[{\"cid\":\"cxtuniu\",\"tripType\":1,\"fromCity\":\"SSA\",\"toCity\":\"SAO\",\"fromDate\":\"20200301\",\"retDate\":\"\",\"cabinClass\":1,\"adultNumber\":1,\"childNumber\":0}],\"result\":{\"status\":999,\"msg\":\"http error\",\"routings\":null},\"error\":false,\"exMsg\":null,\"ota\":\"TUNIU\",\"stage\":\"QUERY\",\"path\":\"/tuniu/flight/v1/query\",\"remoteAddr\":\"180.97.2.17\"}}\n" +
                "{\"time\":\"2020-02-28 00:00:19\", \"thread\":\"http-nio-8000-exec-7\",\"class\":\"com.yyc.gateway.configuration.aspect.ControllerLogAspect\",\"categoryId\":\"gateway-service\",\"level\":\"WARN\",\"message\":{\"method\":\"TuniuController.query(..)\",\"startTime\":\"2020-02-28T00:00:18.292\",\"startMills\":1582819218292,\"elapseMills\":1243,\"args\":[{\"cid\":\"cxtuniu\",\"tripType\":2,\"fromCity\":\"RIO\",\"toCity\":\"CWB\",\"fromDate\":\"20200622\",\"retDate\":\"20200720\",\"cabinClass\":1,\"adultNumber\":1,\"childNumber\":0}],\"result\":{\"status\":999,\"msg\":\"search_second_time_out_error\",\"routings\":null},\"error\":false,\"exMsg\":null,\"ota\":\"TUNIU\",\"stage\":\"QUERY\",\"path\":\"/tuniu/flight/v1/query\",\"remoteAddr\":\"180.97.2.17\"}}\n" +
                "{\"time\":\"2020-02-28 00:01:02\", \"thread\":\"http-nio-8000-exec-24\",\"class\":\"com.yyc.gateway.configuration.aspect.ControllerLogAspect\",\"categoryId\":\"gateway-service\",\"level\":\"WARN\",\"message\":{\"method\":\"TuniuController.query(..)\",\"startTime\":\"2020-02-28T00:01:00.887\",\"startMills\":1582819260887,\"elapseMills\":1535,\"args\":[{\"cid\":\"cxtuniu\",\"tripType\":2,\"fromCity\":\"LAX\",\"toCity\":\"NYC\",\"fromDate\":\"20200301\",\"retDate\":\"20200315\",\"cabinClass\":1,\"adultNumber\":1,\"childNumber\":0}],\"result\":{\"status\":999,\"msg\":\"success\",\"routings\":null},\"error\":false,\"exMsg\":null,\"ota\":\"TUNIU\",\"stage\":\"QUERY\",\"path\":\"/tuniu/flight/v1/query\",\"remoteAddr\":\"180.97.2.17\"}}\n";
        /*String[] jsonContents = jsonC.split("\n");
        String b = null;
        for (String a : jsonContents
        ) {
            b = b + "--" + a;
        }*/
      /*  JSONObject obj = new JSONObject(String.valueOf(jsonC));
        String logs = obj.toString();*/
        ProducerRecord<String, String> producerRecord =
                new ProducerRecord<String, String>("streams-source-input", "logs", dataSource.getData());
        producer.send(producerRecord);
        producer.flush();
        producer.close();


    }
}