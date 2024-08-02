package wordentry.latin;

import com.google.gson.JsonObject;
import components.AdjectiveTermination;
import components.Declension;
import components.Gender;
import json.GsonHelper;

public class LatinAdjectiveEntry extends LatinWordEntry {
    // if of one termination, masculine = nominative and feminine = genitive (neutered can go cry)
    public final String masculine, feminine, neutered;
    public final Declension declension; // first or third
    public final AdjectiveTermination termination;

    public LatinAdjectiveEntry(String masculine, String feminine, String neutered, Declension declension, AdjectiveTermination termination) {
        this.masculine = masculine;
        this.feminine = feminine;
        this.neutered = neutered;
        this.declension = declension;
        this.termination = termination;
    }

    public String getNominative(Gender gender) {
        if (termination == AdjectiveTermination.ONE) {
            return masculine;
        }
        return switch (gender) {
            case MASCULINE -> masculine;
            case FEMININE -> feminine;
            case NEUTERED -> neutered;
        };
    }

    public static LatinAdjectiveEntry fromJson(JsonObject json) {
        Declension declension = Declension.valueOf(json.get("declension").getAsString().toUpperCase());
        if (declension == Declension.SECOND) declension = Declension.FIRST;
        else if (declension == Declension.FOURTH) declension = Declension.THIRD;
        else if (declension == Declension.FIFTH) declension = Declension.THIRD;

        AdjectiveTermination termination = AdjectiveTermination.valueOf(GsonHelper.getAsString(json, "termination", "three").toUpperCase());
        if (termination == AdjectiveTermination.ONE) {
            return new LatinAdjectiveEntry(
                    json.get("nominative").getAsString(),
                    json.get("genitive").getAsString(),
                    null,
                    declension,
                    AdjectiveTermination.ONE
            );
        } else {
            return new LatinAdjectiveEntry(
                    json.get("masculine").getAsString(),
                    json.get("feminine").getAsString(),
                    json.get("neutered").getAsString(),
                    declension,
                    AdjectiveTermination.valueOf(GsonHelper.getAsString(json, "termination", "three").toUpperCase())
            );
        }
    }
}
