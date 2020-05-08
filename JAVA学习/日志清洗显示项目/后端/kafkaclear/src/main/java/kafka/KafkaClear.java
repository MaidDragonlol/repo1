package kafka;


import kafka.kafkaclear.KStreamClear;

public class KafkaClear {
    public static void main(String[] args) throws Exception {
        /*将外部数据输入到kafka*/
        /*Data2kafka.dataInput("D:\\test.log");*/
        /*用kafka streams清理数据*/
         KStreamClear.runClear();

    }
}
