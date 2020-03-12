package kafkastreamlogs;


import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {

        List<String> listdata = new ArrayList<>();//创建一个发型集合
        String a = "firest";
        listdata.add(a);
        String b = "second";
        listdata.add(b);
        System.out.println(listdata);
        listdata.set(1,null);
        System.out.println(listdata);
        listdata.clear();
        listdata.remove(0);
        listdata.add("the");
        System.out.println(listdata);
    }

}
