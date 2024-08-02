package word.latin;

import components.*;
import components.Number;
import wordentry.latin.LatinAdjectiveEntry;

public class LatinAdjective extends LatinWord {
    public final LatinAdjectiveEntry entry;

    public final Gender gender;
    public final NounCase nounCase;
    public final Number number;

    public LatinAdjective(LatinAdjectiveEntry entry, Gender gender, NounCase nounCase, Number number) {
        this.entry = entry;
        this.gender = gender;
        this.nounCase = nounCase;
        this.number = number;
    }

    public String get() {
        String ending = AdjectiveEndings.ENDINGS[entry.declension.ordinal()][gender.ordinal()][number.ordinal()][nounCase.ordinal()];
        if (ending == null) return entry.getNominative(gender);

        String stem;
        if (entry.declension == Declension.THIRD) {
            stem = entry.feminine.substring(0, entry.feminine.lastIndexOf(AdjectiveEndings.ENDINGS[entry.declension.ordinal()][0][0][1]));
        } else {
            stem = entry.getNominative(gender).substring(0, entry.getNominative(gender).lastIndexOf(AdjectiveEndings.ENDINGS[entry.declension.ordinal()][gender.ordinal()][0][0]));
        }

        return stem + ending;
    }
}
