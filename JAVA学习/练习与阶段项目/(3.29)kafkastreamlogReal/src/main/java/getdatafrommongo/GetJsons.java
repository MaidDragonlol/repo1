package getdatafrommongo;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

/*这个用于获取mongo中的清理好的数据*/
public class GetJsons {
    public static String getJsons() {
        MongoCollection<Document> collection = MongoDBUtil.getConnect().getCollection("fish");
        /*------------------------------------------------*/
        String result = new String();
        FindIterable findIterable = collection.find();
        MongoCursor cursor = findIterable.iterator();
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
            result = result+"\n"+cursor.next();
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(getJsons());
    }
}
