package aplicationmain;

import clearstreams.ClearStreamsUtil;
import clearstreams.KafkaProducerLog;

public class run {
    public static void main(String[] args) throws Exception {
        /*生产者加入数据*/
        KafkaProducerLog.producer();
        /*kafka streams清洗数据*/
        ClearStreamsUtil.Clear();
        /*实现功能*/
    }
}
