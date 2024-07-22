package wordentry.english;

import com.google.gson.JsonObject;

public class EnglishAdjectiveEntry extends EnglishWordEntry {
    public final String adjective;

    public EnglishAdjectiveEntry(String adjective) {
        this.adjective = adjective;
    }

    public static EnglishAdjectiveEntry fromJson(JsonObject json) {
        return new EnglishAdjectiveEntry(
                json.get("adjective").getAsString()
        );
    }
}
