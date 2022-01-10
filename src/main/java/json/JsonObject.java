package json;

import java.util.*;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {
    private HashSet<JsonPair> jsonSet;

    public JsonObject(JsonPair... jsonPairs) {
        this.jsonSet = new HashSet<>(Arrays.asList(jsonPairs));
    }

    @Override
    public String toJson() {
        String strJson = "{";
        Iterator<JsonPair> iterator = jsonSet.iterator();
        while (iterator.hasNext()) {
            JsonPair pair = iterator.next();
            strJson += new JsonString(pair.key).toJson();
            strJson += ": ";
            strJson += pair.value.toJson();
            if (iterator.hasNext()) {
                strJson += ", ";
            }
        }
        strJson += "}";
        return strJson;
    }

    public void add(JsonPair jsonPair) {
        jsonSet.add(jsonPair);
    }

    public Json find(String name) {
        Iterator<JsonPair> iterator = jsonSet.iterator();
        while (iterator.hasNext()) {
            JsonPair pair = iterator.next();
            if (pair.key.equals(name)) {
                return pair.value;
            }
        }
        return null;
    }

    public JsonObject projection(String... names) {
        List<String> listNames = Arrays.asList(names);
        Iterator<JsonPair> iterator = jsonSet.iterator();
        JsonObject pairs = new JsonObject();
        while (iterator.hasNext()) {
            JsonPair pair = iterator.next();
            if (listNames.contains(pair.key)) {
                pairs.add(pair);
            }
        }
        return pairs;
    }
}
