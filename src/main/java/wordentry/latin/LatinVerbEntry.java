package wordentry.latin;

import com.google.gson.JsonObject;
import components.Conjugation;

public class LatinVerbEntry extends LatinWordEntry {
    public final String present, infinitive, perfect, pastParticiple; // shorted 1st and 3rd pp names
    public final Conjugation conjugation;

    public LatinVerbEntry(String present, String infinitive, String perfect, String pastParticiple, Conjugation conjugation) {
        this.present = present;
        this.infinitive = infinitive;
        this.perfect = perfect;
        this.pastParticiple = pastParticiple;
        this.conjugation = conjugation;
    }

    public static LatinVerbEntry fromJson(JsonObject json) {
        return new LatinVerbEntry(
                json.get("present").getAsString(),
                json.get("infinitive").getAsString(),
                json.get("perfect").getAsString(),
                json.get("past_participle").getAsString(),
                Conjugation.valueOf(json.get("conjugation").getAsString().toUpperCase())
        );
    }
}
