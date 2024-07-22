package wordentry.english;

import com.google.gson.JsonObject;

public class EnglishVerbEntry extends EnglishWordEntry {
    public final String infinitive, presentParticiple, past, pastParticiple;

    public EnglishVerbEntry(String infinitive, String presentParticiple, String past, String pastParticiple) {
        this.infinitive = infinitive;
        this.presentParticiple = presentParticiple;
        this.past = past;
        this.pastParticiple = pastParticiple;
    }

    public static EnglishVerbEntry fromJson(JsonObject json) {
        return new EnglishVerbEntry(
                json.get("infinitive").getAsString(),
                json.get("present_participle").getAsString(),
                json.get("past").getAsString(),
                json.get("past_participle").getAsString()
        );
    }
}
