package wordentry.latin;

import com.google.gson.JsonObject;
import components.Declension;
import components.Gender;

public class LatinNounEntry extends LatinWordEntry {
    public final String nominative, genitive;
    public final Declension declension;
    public final Gender gender;

    public LatinNounEntry(String nominative, String genitive, Declension declension, Gender gender) {
        this.nominative = nominative;
        this.genitive = genitive;
        this.declension = declension;
        this.gender = gender;
    }

    public static LatinNounEntry fromJson(JsonObject json) {
        return new LatinNounEntry(
                json.get("nominative").getAsString(),
                json.get("genitive").getAsString(),
                Declension.valueOf(json.get("declension").getAsString().toUpperCase()),
                Gender.valueOf(json.get("gender").getAsString().toUpperCase())
        );
    }
}
