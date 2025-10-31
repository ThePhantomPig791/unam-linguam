package wordentry.english;

import com.google.gson.JsonObject;

public class EnglishAdverbEntry extends EnglishWordEntry {
    public final String adverb;

    public EnglishAdverbEntry(String adverb) {
        this.adverb = adverb;
    }

    public static EnglishAdverbEntry fromJson(JsonObject json) {
        return new EnglishAdverbEntry(json.get("adverb").getAsString());
    }
}
