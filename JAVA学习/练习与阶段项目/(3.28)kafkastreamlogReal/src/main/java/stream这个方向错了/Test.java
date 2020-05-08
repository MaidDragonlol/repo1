package stream这个方向错了;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<String> test = new ArrayList<>();
        List<String> a = new ArrayList<>();
        a.add("abc");
        a.add("bddd");
        a.add("\n");
        a.add("ddf");
        String str1 = StringUtils.join(a, ",");
        System.out.println(str1);
        String[] e = str1.split(",");

        for (String data : e
             ) {
            test.add(data);
            if(data.equals("\n"))test.clear();
            for (String v : test
                 ) {
                System.out.println(v);

            }


        }




       /* JSONObject b = new JSONObject();
        b.put("f1", "xA");
        b.put("f2", "xxx");
        String c = b.get("f1").toString();
        System.out.println(c);
        System.out.println(b);*/
    }

}
