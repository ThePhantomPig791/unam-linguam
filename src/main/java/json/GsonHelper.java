package json;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

public class GsonHelper {
    public static String[] getStringArray(JsonElement json) {
        if (json.isJsonPrimitive()) return new String[] {json.getAsString()};
        else if (json.isJsonArray()) {
            return json.getAsJsonArray().asList().stream().map(JsonElement::getAsString).toList().toArray(new String[0]);
        } else {
            throw new JsonSyntaxException("Expected an array or primitive");
        }
    }

    public static String getAsString(JsonObject json, String key, String fallback) {
        if (json.has(key)) return json.get(key).getAsString();
        return fallback;
    }
}
