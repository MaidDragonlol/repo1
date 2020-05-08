package datasearch;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/*工具类：对map中的元素的值进行从大到小的排序*/
public class MapSortUtil {
    public static List<String> sortMapByValue(Map<String, Integer> map) {
        int size = map.size();
        //通过map.entrySet()将map转换为"1.B.1.e=78"形式的list集合
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(size);
        list.addAll(map.entrySet());
        List<String> keys = list.stream()
                .sorted(Comparator.comparing(Map.Entry<String, Integer>::getValue).reversed())
                .map(Map.Entry<String, Integer>::getKey)
                .collect(Collectors.toList());
        return keys;
    }
    /*测试*/
    public static void main(String[] args) throws ParseException {
        //这里自定义一个需要排序的map集合
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("1.B.1.a", 45);
        map.put("1.B.1.e", 65);
        map.put("1.B.1.c", 12);
        map.put("1.B.1.b", 15);
        map.put("1.B.1.d", 78);
        List<String> keys = sortMapByValue(map);
        Integer c = map.get("1.B.1.a");
        System.out.println(c);
        keys.forEach(System.out::println);
        /*String dt="Thu May 28 18:23:17 CST 2015";

        SimpleDateFormat sdf1= new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);

        SimpleDateFormat sdf2= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

       String a = sdf2.format(sdf1.parse(dt));*/

    }

}
