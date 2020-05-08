package stream这个方向错了.producer;

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
        String jsonC ="{\"time\":\"2020-02-28 04:05:14\", \"thread\":\"http-nio-8000-exec-18\",\"class\":\"com.yyc.gateway.configuration.aspect.ControllerLogAspect\",\"categoryId\":\"gateway-service\",\"level\":\"WARN\",\"message\":{\"method\":\"TuniuController.query(..)\",\"startTime\":\"2020-02-28T04:05:09.432\",\"startMills\":1582833909432,\"elapseMills\":5105,\"args\":[{\"cid\":\"cxtuniu\",\"tripType\":1,\"fromCity\":\"ORL\",\"toCity\":\"MEX\",\"fromDate\":\"20200918\",\"retDate\":\"\",\"cabinClass\":1,\"adultNumber\":1,\"childNumber\":0}],\"result\":{\"status\":999,\"msg\":\"search_lack_of_data_error\",\"routings\":null},\"error\":false,\"exMsg\":null,\"ota\":\"TUNIU\",\"stage\":\"QUERY\",\"path\":\"/tuniu/flight/v1/query\",\"remoteAddr\":\"180.97.2.17\"}}\n" +
                "{\"time\":\"2020-02-28 04:05:18\", \"thread\":\"http-nio-8000-exec-3\",\"class\":\"com.yyc.gateway.configuration.aspect.ControllerLogAspect\",\"categoryId\":\"gateway-service\",\"level\":\"WARN\",\"message\":{\"method\":\"TuniuController.query(..)\",\"startTime\":\"2020-02-28T04:05:17.247\",\"startMills\":1582833917247,\"elapseMills\":1123,\"args\":[{\"cid\":\"cxtuniu\",\"tripType\":1,\"fromCity\":\"SAO\",\"toCity\":\"MIA\",\"fromDate\":\"20200304\",\"retDate\":\"\",\"cabinClass\":1,\"adultNumber\":1,\"childNumber\":0}],\"result\":{\"status\":999,\"msg\":\"search_lack_of_data_error\",\"routings\":null},\"error\":false,\"exMsg\":null,\"ota\":\"TUNIU\",\"stage\":\"QUERY\",\"path\":\"/tuniu/flight/v1/query\",\"remoteAddr\":\"180.97.2.17\"}}\n" +
                "{\"time\":\"2020-02-28 04:05:25\", \"thread\":\"http-nio-8000-exec-19\",\"class\":\"com.yyc.gateway.configuration.aspect.ControllerLogAspect\",\"categoryId\":\"gateway-service\",\"level\":\"WARN\",\"message\":{\"method\":\"TuniuController.query(..)\",\"startTime\":\"2020-02-28T04:05:19.818\",\"startMills\":1582833919818,\"elapseMills\":5340,\"args\":[{\"cid\":\"cxtuniu\",\"tripType\":2,\"fromCity\":\"SAO\",\"toCity\":\"SSA\",\"fromDate\":\"20200306\",\"retDate\":\"20200317\",\"cabinClass\":1,\"adultNumber\":1,\"childNumber\":0}],\"result\":{\"status\":999,\"msg\":\"search_lack_of_data_error\",\"routings\":null},\"error\":false,\"exMsg\":null,\"ota\":\"TUNIU\",\"stage\":\"QUERY\",\"path\":\"/tuniu/flight/v1/query\",\"remoteAddr\":\"180.97.2.17\"}}\n" +
                "{\"time\":\"2020-02-28 04:10:50\", \"thread\":\"http-nio-8000-exec-18\",\"class\":\"com.yyc.gateway.configuration.aspect.ControllerLogAspect\",\"categoryId\":\"gateway-service\",\"level\":\"WARN\",\"message\":{\"method\":\"TuniuController.query(..)\",\"startTime\":\"2020-02-28T04:10:48.695\",\"startMills\":1582834248695,\"elapseMills\":1648,\"args\":[{\"cid\":\"cxtuniu\",\"tripType\":1,\"fromCity\":\"SFO\",\"toCity\":\"ONT\",\"fromDate\":\"20200228\",\"retDate\":\"\",\"cabinClass\":1,\"adultNumber\":1,\"childNumber\":0}],\"result\":{\"status\":999,\"msg\":\"search_lack_of_data_error\",\"routings\":null},\"error\":false,\"exMsg\":null,\"ota\":\"TUNIU\",\"stage\":\"QUERY\",\"path\":\"/tuniu/flight/v1/query\",\"remoteAddr\":\"180.97.2.17\"}}\n" +
                "{\"time\":\"2020-02-28 04:10:53\", \"thread\":\"http-nio-8000-exec-27\",\"class\":\"com.yyc.gateway.configuration.aspect.ControllerLogAspect\",\"categoryId\":\"gateway-service\",\"level\":\"WARN\",\"message\":{\"method\":\"TuniuController.query(..)\",\"startTime\":\"2020-02-28T04:10:51.172\",\"startMills\":1582834251172,\"elapseMills\":2435,\"args\":[{\"cid\":\"cxtuniu\",\"tripType\":1,\"fromCity\":\"MEX\",\"toCity\":\"CPE\",\"fromDate\":\"20200411\",\"retDate\":\"\",\"cabinClass\":1,\"adultNumber\":1,\"childNumber\":0}],\"result\":{\"status\":0,\"msg\":\"success\",\"routings\":[{\"data\":\"0225225b5d464f67b3cb4bcbe33bc6420005\",\"currency\":\"CNY\",\"adultPrice\":1098,\"adultTax\":0,\"childPrice\":1098,\"childTax\":0,\"infantPrice\":null,\"infantTax\":null,\"nationalityType\":0,\"nationality\":\"\",\"suitAge\":\"2~99\",\"priceType\":0,\"applyType\":0,\"adultTaxType\":1,\"childTaxType\":1,\"infantTaxType\":null,\"latestTicketTime\":\"202002290410\",\"ticketingCarrier\":\"AM\",\"splitTripFlag\":false,\"splitTripBetweenThem\":false,\"splitTripWithinThem\":null,\"splitTripResult\":null,\"rule\":{\"refund\":\"退票规定：不退不改\",\"endorse\":\"改签规定：不退不改\",\"baggage\":\"V1.2成人0千克\",\"other\":\"其他说明：不退不改\"},\"fromSegments\":[{\"flightOption\":1,\"carrier\":\"AM\",\"flightNumber\":\"AM2440\",\"depAirport\":\"MEX\",\"depTime\":\"202004110840\",\"arrAirport\":\"CPE\",\"arrTime\":\"202004111039\",\"stopCities\":null,\"codeShare\":false,\"shareFlightNumber\":\"\",\"departureTerminal\":null,\"arrivingTerminal\":null,\"cabinClass\":1,\"cabinCode\":\"Q\",\"aircraftCode\":\"E70\",\"seatCount\":1}],\"retSegments\":[],\"ticketInvoiceType\":0}]},\"error\":false,\"exMsg\":null,\"ota\":\"TUNIU\",\"stage\":\"QUERY\",\"path\":\"/tuniu/flight/v1/query\",\"remoteAddr\":\"180.97.2.17\"}}\n" +
                "{\"time\":\"2020-02-28 04:10:56\", \"thread\":\"http-nio-8000-exec-30\",\"class\":\"com.yyc.gateway.configuration.aspect.ControllerLogAspect\",\"categoryId\":\"gateway-service\",\"level\":\"WARN\",\"message\":{\"method\":\"TuniuController.query(..)\",\"startTime\":\"2020-02-28T04:10:51.172\",\"startMills\":1582834251172,\"elapseMills\":4969,\"args\":[{\"cid\":\"cxtuniu\",\"tripType\":2,\"fromCity\":\"MSP\",\"toCity\":\"PHL\",\"fromDate\":\"20200318\",\"retDate\":\"20200322\",\"cabinClass\":1,\"adultNumber\":1,\"childNumber\":0}],\"result\":{\"status\":999,\"msg\":\"search_lack_of_data_error\",\"routings\":null},\"error\":false,\"exMsg\":null,\"ota\":\"TUNIU\",\"stage\":\"QUERY\",\"path\":\"/tuniu/flight/v1/query\",\"remoteAddr\":\"180.97.2.17\"}}\n";
              /*String[] jsonContents = jsonC.split("\n");
        String b = null;
        for (String a : jsonContents
        ) {
            b = b + "--" + a;
        }*/
        JSONObject obj = new JSONObject(String.valueOf(jsonC));
        String logs = obj.toString();
        ProducerRecord<String, String> producerRecord =
                new ProducerRecord<String, String>("streams-source-input", "logs", jsonC);
        producer.send(producerRecord);
        producer.flush();
        producer.close();


    }
}