package wordentry.latin;

import com.google.gson.JsonObject;
import components.Declension;

public class LatinAdjectiveEntry extends LatinWordEntry {
    public final String masculine, feminine, neutered;
    public final Declension declension; // first or third

    public LatinAdjectiveEntry(String masculine, String feminine, String neutered, Declension declension) {
        this.masculine = masculine;
        this.feminine = feminine;
        this.neutered = neutered;
        this.declension = declension;
    }

    public static LatinAdjectiveEntry fromJson(JsonObject json) {
        Declension declension = Declension.valueOf(json.get("declension").getAsString().toUpperCase());
        if (!(declension == Declension.FIRST || declension == Declension.THIRD)) {
            throw new RuntimeException("Adjective must be in the first or third declension, got " + declension);
        }
        return new LatinAdjectiveEntry(
                json.get("masculine").getAsString(),
                json.get("feminine").getAsString(),
                json.get("neutered").getAsString(),
                declension
        );
    }
}
