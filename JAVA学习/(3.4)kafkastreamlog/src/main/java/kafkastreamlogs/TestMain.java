package kafkastreamlogs;

import java.util.concurrent.TimeUnit;

public class TestMain {
    public static void main(String[] args) throws Exception {
        /*统计这个程序完成时间*/
        long start = System.nanoTime();//获取系统排序的时间点
        /*这里选一个变量输入观察结果*/
        String jsonContent2 = "{\"time\":\"2020-02-28 10:18:58\", \"thread\":\"http-nio-8000-exec-24\",\"class\":\"com.yyc.gateway.configuration.aspect.ControllerLogAspect\",\"categoryId\":\"gateway-service\",\"level\":\"WARN\",\"message\":{\"method\":\"TuniuController.query(..)\",\"startTime\":\"2020-02-28T10:18:56.753\",\"startMills\":1582856336753,\"elapseMills\":2243,\"Args\":[{\"cid\":\"cxtuniu\",\"tripType\":1,\"fromCity\":\"DFW\",\"toCity\":\"CHI\",\"fromDate\":\"20200228\",\"retDate\":\"\",\"cabinClass\":1,\"adultNumber\":1,\"childNumber\":0}],\"result\":{\"status\":999,\"msg\":\"search_lack_of_data_error\",\"routings\":null},\"error\":false,\"exMsg\":null,\"ota\":\"TUNIU\",\"stage\":\"QUERY\",\"path\":\"/tuniu/flight/v1/query\",\"remoteAddr\":\"180.97.2.17\"}}";
        String jsonContent = "{\"time\":\"2020-02-28 10:23:51\", \"thread\":\"http-nio-8000-exec-18\",\"class\":\"com.yyc.gateway.configuration.aspect.ControllerLogAspect\",\"categoryId\":\"gateway-service\",\"level\":\"WARN\",\"message\":{\"method\":\"JintongController.recommend(..)\",\"startTime\":\"2020-02-28T10:23:48.839\",\"startMills\":1582856628839,\"elapseMills\":2397,\"args\":[{\"cid\":\"cxjinri\",\"tripType\":1,\"adultNum\":1,\"childrenNum\":0,\"routing\":{\"adultPrice\":0,\"adultTax\":0,\"adultTaxType\":0,\"applyType\":0,\"priceType\":0,\"autoTicket\":0,\"childPrice\":0,\"childTax\":0,\"ticketTimeLimit\":0,\"childTaxType\":0,\"infantPrice\":0,\"infantTax\":0,\"data\":\"123456\",\"fareType\":null,\"lastTicketDate\":null,\"nationality\":null,\"needChangePNR\":null,\"speFlag\":0,\"reservationType\":null,\"ticketType\":null,\"validatingCarrier\":null,\"ticketInvoiceType\":0,\"ticketInfo\":null,\"rule\":null,\"ruleEx\":null,\"fromSegments\":[{\"aircraftCode\":\"\",\"arrAirport\":\"PVG\",\"arrTime\":\"202003030945\",\"arrivingTerminal\":null,\"baggageAllowance\":0,\"baggagePieces\":0,\"cabin\":\"L\",\"cabinClass\":1,\"carrier\":\"SU\",\"codeShare\":false,\"depAirport\":\"SVO\",\"depTime\":\"202003021950\",\"duration\":0,\"flightNumber\":\"SU208\",\"seatCount\":0,\"stopCities\":\"\",\"sharingFlightNumber\":null,\"departureTerminal\":null}],\"retSegments\":[],\"fromLuggages\":null,\"retLuggages\":null},\"passengers\":[{\"name\":\"BILA/NATALIIA\",\"ageType\":0,\"birthday\":\"19890808\",\"gender\":\"F\",\"cardType\":\"PP\",\"cardNum\":\"E12369898\",\"cardExpired\":\"20280320\",\"cardIssuePlace\":\"UA\",\"nationality\":\"UA\"}]}],\"result\":{\"status\":0,\"msg\":\"无运价\",\"sessionId\":null,\"routing\":null,\"maxSeats\":0,\"rule\":null,\"routings\":null},\"error\":false,\"exMsg\":null,\"ota\":\"JINTONG\",\"stage\":\"RECOMMEND\",\"path\":\"/jinri/flight/v1/recommend\",\"remoteAddr\":\"61.151.247.215\"}}";
        JsonPaser.insertConent(jsonContent);
        long end = System.nanoTime();//获取系统结束的时间点
        long ms = TimeUnit.NANOSECONDS.toMillis(end-start);//得到所用的时间
        System.out.println(ms+"ms");  //31ms
    }
}
