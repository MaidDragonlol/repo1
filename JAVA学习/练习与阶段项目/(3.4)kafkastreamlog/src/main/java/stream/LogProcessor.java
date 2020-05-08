package stream;



import com.alibaba.fastjson.JSONArray;
import kafkastreamlogs.JsonPaser;
import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;



public class LogProcessor implements Processor<byte[], byte[]> {
    private ProcessorContext context;

    @Override
    public void init(ProcessorContext context) {
        this.context = context;
    }

    @Override
    public void process(byte[] key, byte[] value) {
        String jsonContent = JSONArray.toJSONString(value);
        /*目的是输入value抽取结果并输出*/
        try {
            JsonPaser.insertConent(jsonContent);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void punctuate(long timestamp) {

    }
   /* close()方法结束程序。*/
    @Override
    public void close() {

    }

}

