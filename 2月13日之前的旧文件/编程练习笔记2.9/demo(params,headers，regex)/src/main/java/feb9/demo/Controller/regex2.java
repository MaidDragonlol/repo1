package feb9.demo.Controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*replaceFirst 和 replaceAll 方法用来替换匹配正则表达式的文本。不同的是，replaceFirst 替换首次匹配，replaceAll 替换所有匹配。*/
public class regex2 {
    private static String REGEX = "abc";
    private static String INPUT = "/test/abc/policy";
    private static String REPLACE = "123";

    public static void main(String[] args) {
        Pattern p = Pattern.compile(REGEX);
        // get a matcher object
        Matcher m = p.matcher(INPUT);
        INPUT = m.replaceAll(REPLACE);
        System.out.println(INPUT);
    }
}
