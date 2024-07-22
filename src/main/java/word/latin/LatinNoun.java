package word.latin;

import components.NounCase;
import components.NounEndings;
import components.Number;
import wordentry.latin.LatinNounEntry;

public class LatinNoun extends LatinWord {
    public final LatinNounEntry wordEntry;

    public final NounCase nounCase; // "case" is a java keyword so i had to use nounCase lol
    public final Number number;

    public LatinNoun(LatinNounEntry entry, NounCase nounCase, Number number) {
        this.wordEntry = entry;
        this.nounCase = nounCase;
        this.number = number;
    }
    public LatinNoun(LatinNounEntry entry) {
        this(entry, NounCase.random(), Number.random());
    }

    public String get() {
        if (NounEndings.ENDINGS[wordEntry.declension.ordinal()][wordEntry.gender.ordinal()] == null) {
            throw new RuntimeException("Noun " + wordEntry.nominative + ", " + wordEntry.genitive + " cannot be declined; no endings exist for " + wordEntry.declension + " declension " + wordEntry.gender + " nouns!");
        }

        // remove the genitive ending from the genitive to get the stem
        String stem = wordEntry.genitive;
        stem = stem.substring(0, stem.lastIndexOf(NounEndings.ENDINGS[wordEntry.declension.ordinal()][wordEntry.gender.ordinal()][0][1]));

        String ending = NounEndings.ENDINGS[wordEntry.declension.ordinal()][wordEntry.gender.ordinal()][number.ordinal()][nounCase.ordinal()];
        if (ending == null) return wordEntry.nominative;
        return stem + ending;
    }
}
