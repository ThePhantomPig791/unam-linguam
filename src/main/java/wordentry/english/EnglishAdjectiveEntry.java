package wordentry.english;

import com.google.gson.JsonObject;

public class EnglishAdjectiveEntry extends EnglishWordEntry {
    public final String adjective;
    public final byte priority;

    public EnglishAdjectiveEntry(String adjective, byte priority) {
        this.adjective = adjective;
        this.priority = priority;
    }

    public static EnglishAdjectiveEntry fromJson(JsonObject json) {
        return new EnglishAdjectiveEntry(
                json.get("adjective").getAsString(),
                json.get("priority").getAsByte()
        );
    }
}
