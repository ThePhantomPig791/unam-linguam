package wordentry.latin;

import com.google.gson.JsonObject;

public class LatinAdverbEntry extends LatinWordEntry {
    public final String adverb;

    public LatinAdverbEntry(String adverb) {
        this.adverb = adverb;
    }

    public static LatinAdverbEntry fromJson(JsonObject json) {
        return new LatinAdverbEntry(json.get("adverb").getAsString());
    }
}
