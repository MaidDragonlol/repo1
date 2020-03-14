package stream.TopologyProcessor;

import logclearutil.InsertConentUtil;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.KeyValueStore;
import org.json.JSONException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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
        String valueLogs = value.trim();   //源数据
        /*分割、遍历，并且存储格式为<编号，[msg,fromctiy,tocity]>*/
        String[] jsonContents = valueLogs.split("--");

        for (Integer i = 1; i < jsonContents.length; i++
        ) {
            /*封装类实例化*/
            InsertConentUtil insertConentUtil = InsertConentUtil.getInstance();
            /*调用封装类方法来清洗*/
            try {
                List<String> stringArryValue = insertConentUtil.run(jsonContents[i]);
                String msg = stringArryValue.get(0);
                String fromCity = stringArryValue.get(1);
                String toCity = stringArryValue.get(2);
                String route = fromCity + "-" + toCity;
                if (msg.equals("success")) {
                    erroTimes = 0;
                }
                String judge = kvStore.putIfAbsent(route, "1" + "-" + erroTimes.toString());
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
                                String valuePartString = (++routeTotalTimes).toString() + "-" + (++routeErrorTimes).toString();
                                this.kvStore.put(route, valuePartString);//返回格式：<route,航线次数-错误次数>
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
				/*String[] timesAndError = entry.value.split("-");
				Integer times = Integer.getInteger(timesAndError[0]);
				Integer errors = Integer.getInteger(timesAndError[1]);
				Double frequncy = Double.valueOf(times)/Double.valueOf(errors);*/
                context.forward(entry.key, entry.value);
                /*this.kvStore.delete(entry.key);*/
            });
        }
        context.commit();
    }

    @Override
    public void close() {
        this.kvStore.close();
    }

}
