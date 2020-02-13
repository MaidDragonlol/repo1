package feb9.demo.Controller;


import java.util.regex.Pattern;

public class regex {
    public static void main(String args[]) {
        String content = "I ride fisrt " +
                "i run first.";

        String pattern = ".*run.*";

        boolean isMatch = Pattern.matches(pattern, content);
        System.out.println("字符串中是否包含了 'run' 子字符串? " + isMatch);
    }

}
