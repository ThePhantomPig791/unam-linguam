package wordentry.english;

import com.google.gson.JsonObject;

public class EnglishNounEntry extends EnglishWordEntry {
    public final String singular, plural;

    public EnglishNounEntry(String singular, String plural) {
        this.singular = singular;
        this.plural = plural;
    }

    public static EnglishNounEntry fromJson(JsonObject json) {
        return new EnglishNounEntry(
                json.get("singular").getAsString(),
                json.get("plural").getAsString()
        );
    }
}
