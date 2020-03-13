package kafkastreamlogs;

public class Test2 {
    public static void main(String[] args) {
        String getValue = "1-2";
        String[] kvStorValuePart = getValue.split("-");
        String first = kvStorValuePart[0];
        String second = kvStorValuePart[1];
        System.out.println(first);
        System.out.println(second);
        Integer routeTotalTimes = Integer.parseInt(kvStorValuePart[0]);
        Integer routeErrorTimes = Integer.parseInt(kvStorValuePart[1]);
        System.out.println(routeTotalTimes);
        System.out.println(routeErrorTimes);
    }


}
