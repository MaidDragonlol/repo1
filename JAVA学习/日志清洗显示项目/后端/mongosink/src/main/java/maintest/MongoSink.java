package maintest;


import kafka.MongoDBConsumer;
/*程序入口*/
public class MongoSink {
    public static void main(String[] args) {
        MongoDBConsumer.kafka2Mongo();
    }
}
