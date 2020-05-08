package com.example.kafkamessage;

import com.example.kafkamessage.kafkaclear.kafka.MongoDBSimpleConsumer3;
import com.example.kafkamessage.kafkaclear.kafkaclear.ClearStreamsUtil;
import com.example.kafkamessage.kafkaclear.kafkaclear.Data2kafka;
import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkamessageApplication {

    @SneakyThrows
    public static void main(String[] args) {
        /*将外部数据输入到kafka*/
        /*Data2kafka.dataInput("D:\\gateway.log");*/
        /*用kafka streams清理数据*/
        /*ClearStreamsUtil.kafkaStreamsClear();*/
        /*将kafka清理好的数据输出到mongo*/
        /*MongoDBSimpleConsumer3.kafka2Mongo();*/
        /*开启输出端口，输出数据*/
        SpringApplication.run(KafkamessageApplication.class, args);
    }

}
