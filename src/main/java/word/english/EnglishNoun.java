package word.english;

import components.Number;
import wordentry.english.EnglishNounEntry;

public class EnglishNoun extends EnglishWord {
    public final EnglishNounEntry wordEntry;

    public final Number number;

    public EnglishNoun(EnglishNounEntry entry, Number number) {
        this.wordEntry = entry;
        this.number = number;
    }
    public EnglishNoun(EnglishNounEntry entry) {
        this(entry, Number.random());
    }

    public String get() {
        if (number == Number.SINGULAR) return wordEntry.singular;
        else return wordEntry.plural;
    }
}
