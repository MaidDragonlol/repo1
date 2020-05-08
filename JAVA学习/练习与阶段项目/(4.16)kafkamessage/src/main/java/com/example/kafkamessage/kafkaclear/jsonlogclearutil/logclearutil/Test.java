package com.example.kafkamessage.kafkaclear.jsonlogclearutil.logclearutil;


import java.io.*;
import java.nio.charset.StandardCharsets;

/*这个测试通过了，结果：全部数据清理出来了*/
public class Test {
    public static void main(String[] args) throws Exception {
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File("D:\\gateway.log")));
            BufferedReader in = new BufferedReader(new InputStreamReader(bis, StandardCharsets.UTF_8), 10 * 1024 * 1024);//10M缓存
            FileWriter fw = new FileWriter("D:\\dataget.txt");
            /*KafkaProducerLog kafkaProducerLog = new KafkaProducerLog();*/
            int i = 0;

            while (in.ready()) {
                /* String line = in.readLine() + "\n";*/
                String line = in.readLine();

                InsertConentUtil insertConentUtil = InsertConentUtil.getInstance();
                String b =  insertConentUtil.run(line);
                System.out.println(i++);
                System.out.println(b);
                fw.append(b + "\n");
            }
            in.close();
            fw.flush();
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();

        }

    }
}
