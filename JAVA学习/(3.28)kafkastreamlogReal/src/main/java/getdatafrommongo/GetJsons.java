package getdatafrommongo;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
/*这个用于获取mongo中的清理好的数据*/
public class GetJsons {
    public static String getJsons() {
        MongoCollection<Document> collection = MongoDBUtil.getConnect().getCollection("fish");
        String result = String.valueOf(collection);
        return result;
    }
}
