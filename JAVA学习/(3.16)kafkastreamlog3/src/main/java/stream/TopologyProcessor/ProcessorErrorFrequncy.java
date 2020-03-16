package stream.TopologyProcessor;

import com.alibaba.fastjson.JSONObject;
import logclearutil.InsertConentUtil;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.KeyValueStore;
import org.json.JSONException;

import java.util.List;

public class ProcessorErrorFrequncy implements Processor<String, String> {

    private ProcessorContext context;
    private KeyValueStore<String, String> kvStore;

    @SuppressWarnings("unchecked")
    @Override
    public void init(ProcessorContext context) {
        this.context = context;
        this.context.schedule(1000);
        this.kvStore = (KeyValueStore<String, String>) context.getStateStore("Counts");
    }

    @Override
    public void process(String key, String value) {
        Integer erroTimes = 1;
        String valueLogs = value;   //源数据
        /*分割、遍历，并且存储格式为<编号，[msg,fromctiy,tocity]>*/
        String[] jsonContents = valueLogs.split("\n");
        for (String jsonContent : jsonContents
             ) {
            /*封装类实例化*/
            InsertConentUtil insertConentUtil = InsertConentUtil.getInstance();
            /*调用封装类方法来清洗*/
            try {
                List<String> stringArryValue = insertConentUtil.run(jsonContent);
                String msg = stringArryValue.get(0);
                String fromCity = stringArryValue.get(1);
                String toCity = stringArryValue.get(2);
                String route = fromCity + "-" + toCity;
                if (msg.equals("success")) {
                    erroTimes = 0;
                }
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("route",route);
                jsonObject1.put("routeTotalTimes",1);
                jsonObject1.put("routeErrorTimes",erroTimes);
                String valuePartString1 = jsonObject1.toString();
                String judge = kvStore.putIfAbsent(route, valuePartString1);
                if (judge != null) {
                    /*迭代kvstore缓存，取出每个键值对与当前的数据进行比较*/
                    try (KeyValueIterator<String, String> iter = kvStore.all()) {
                        while (iter.hasNext()) {
                            KeyValue<String, String> entry = iter.next();
                            String getKey = entry.key;
                            String getValue = entry.value;
                            if (getKey.equals(route)) {
                                String[] kvStorValuePart = getValue.split("-");
                                Integer routeTotalTimes = Integer.parseInt(kvStorValuePart[0]);
                                Integer routeErrorTimes = Integer.parseInt(kvStorValuePart[1]);
                                JSONObject jsonObject = new JSONObject();
                                jsonObject.put("route",route);
                                jsonObject.put("routeTotalTimes",++routeTotalTimes);
                                jsonObject.put("routeErrorTimes",routeErrorTimes+erroTimes);
                                String valuePartString = jsonObject.toString();
                                this.kvStore.put(route, valuePartString);
                                break;
                            }
                        }
                    }
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void punctuate(long timestamp) {
        try (KeyValueIterator<String, String> iterator = this.kvStore.all()) {
            iterator.forEachRemaining(entry -> {
                context.forward(entry.key, entry.value);
                this.kvStore.delete(entry.key);
            });
        }
        context.commit();
    }

    @Override
    public void close() {
        this.kvStore.close();
    }

}
