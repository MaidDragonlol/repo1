package stream这个方向错了.TopologyProcessor;

import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.KeyValueStore;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/*目的：从格式为“时间，航线，次数，错误次数，\n”统计出数据格式为“时间，航线，错误率”*/
public class RouteErrorFrequncy implements Processor<String, String> {
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
        String valueLogs = value;   //源数据,格式：String value = "时间，航线，次数，错误次数，\n"
        List<String> store = new ArrayList<>();
        List<String> newOne = new ArrayList<>();
        String[] contents = valueLogs.split(",");
        for (String data : contents
        ) {
            store.add(data);
            if (data.equals("\n")) {
                String time = store.get(0);//时间段
                String route = store.get(1);
                Integer times = Integer.valueOf(store.get(2));
                Integer errorTimes = Integer.valueOf(store.get(3));
                Double errorFrequncy = Double.valueOf(times)/Double.valueOf(errorTimes);//计算航线错误率
                newOne.add(time);
                newOne.add(route);
                newOne.add(String.valueOf(errorFrequncy));
                String newOneString = StringUtils.join(newOne, ",");
                this.kvStore.put(UUID.randomUUID().toString(),newOneString);
                store.clear();
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
